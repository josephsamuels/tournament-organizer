package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class TournamentEvent<E extends AbstractParticipant> {
    
    private final ArrayList<E> allParticipants = new ArrayList<>();
    private final ArrayList<E> activeParticipants = new ArrayList<>();
    private final ArrayList<E> droppedParticipants = new ArrayList<>();
    
    private final ArrayList<EventRound> rounds = new ArrayList<>();
    
    private final int MIN_PAIRING, MAX_PAIRING;
    
    private final TournamentStyle STYLE;
    
    public TournamentEvent(int minPairingCount, int maxPairingCount, TournamentStyle style) {
        this.MIN_PAIRING = minPairingCount;
        this.MAX_PAIRING = maxPairingCount;
        this.STYLE = style;
    }
    
    public final void addParticipant(E participant) {
        if(!this.allParticipants.contains(participant)) {
            this.allParticipants.add(participant);
            this.activeParticipants.add(participant);
        }
    }
    
    public final void dropParticipant(E participant) {
        this.activeParticipants.remove(participant);
        this.droppedParticipants.add(participant);
    }
    
    public final void reinstateParticipant(E particpant) {
        this.droppedParticipants.remove(particpant);
        this.activeParticipants.add(particpant);
    }
    
    public final void deleteParticipant(E player) throws Exception {
        if(rounds.isEmpty()) {
            this.activeParticipants.remove(player);
            this.allParticipants.remove(player);
        } else {
            throw new IllegalStateException(
                    "Cannot remove player after event has started.");
        }
    }
    
    public final ArrayList<E> getAllParticipantsList() {
        return this.allParticipants;
    }
    
    public final ArrayList<E> getActiveParticipantList() {
        return this.activeParticipants;
    }
    
    public final ArrayList<E> getDroppedParticipantList() {
        return this.droppedParticipants;
    }
    
    public final void createRound() {
        if(STYLE == TournamentStyle.SINGLE || STYLE == TournamentStyle.DOUBLE) {
            dropEliminatedPlayers();
        }
        
        EventRound round = 
                PairingFactory.generateMatches(activeParticipants, 
                                                MIN_PAIRING, 
                                                MAX_PAIRING);
        
        this.rounds.add(round);
    }
    
    public final void cutToTop(int x) {
        ArrayList<E> toDrop = new ArrayList<>();
        
        for(int i=x; i < activeParticipants.size(); i++) {
            toDrop.add(activeParticipants.get(i));
        }
        
        for(E participant : toDrop) {
            this.dropParticipant(participant);
        }
    }
    
    private void dropEliminatedPlayers() {
        ArrayList<E> toDrop = new ArrayList<>();
        
        for(E participant : activeParticipants) {
            if((participant.getLossCount() > 0 && STYLE == TournamentStyle.SINGLE)
                  || (participant.getLossCount() > 1 && STYLE == TournamentStyle.DOUBLE)) {
                toDrop.add(participant);
            }
        }
        
        for(E participant : toDrop) {
            this.dropParticipant(participant);
        }
    }
    
    public final void deleteRound(int index) {
        EventRound r = rounds.remove(index);
        for(EventMatch match : r.getMatches()) {
            match.cleanParticipants();
        }
    }
    
    public final ArrayList<EventRound> getRounds() {
        return this.rounds;
    }
    
    public final TournamentStyle getEventStyle() {
        return this.STYLE;
    }
}
