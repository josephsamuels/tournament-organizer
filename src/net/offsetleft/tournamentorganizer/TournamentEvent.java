package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class TournamentEvent<E extends AbstractParticipant> {
    private final ArrayList<E> allParticipants = new ArrayList<>();
    private final ArrayList<E> activeParticipants = new ArrayList<>();
    private final ArrayList<E> droppedPartcipants = new ArrayList<>();
    
    private final ArrayList<TournamentRound> eventRounds = new ArrayList<>();
    
    /*
    *   Participant relations
    */
    
    
    public final void addParticipant(E participant) {
        this.allParticipants.add(participant);
        this.activeParticipants.add(participant);
    }
    
    public final void removeParticipant(E participant) {
        if(eventRounds.size() > 0) {
            throw new IllegalStateException(
                    "You cannot delete a particpant after the event has started.");
        } else {
            this.allParticipants.remove(participant);
            this.activeParticipants.remove(participant);
            this.droppedPartcipants.remove(participant);
        }
    }
    
    public final void dropParticipant(E participant) {
        if(activeParticipants.contains(participant) 
                && !droppedPartcipants.contains(participant)) {
            this.activeParticipants.remove(participant);
            this.droppedPartcipants.add(participant);
        }
    }
    
    public final void reinstateParticipant(E participant) {
        if(!activeParticipants.contains(participant) 
                && droppedPartcipants.contains(participant)) {
            this.activeParticipants.add(participant);
            this.droppedPartcipants.remove(participant);
        }
    }

    public final ArrayList<E> getAllParticipants() {
        return this.allParticipants;
    }
    
    public final ArrayList<E> getActiveParticipants() {
        return this.activeParticipants;
    }
    
    public final ArrayList<E> getDroppedParticipants() {
        return this.droppedPartcipants;
    }

    private void dropEliminatedPlayers(TournamentEliminationStyle style) {
        ArrayList<E> toDrop = new ArrayList<>();
        
        for(E participant : activeParticipants) {
            if((participant.getLossCount() > 0 
                    && style == TournamentEliminationStyle.SINGLE)
                  || (participant.getLossCount() > 1 
                    && style == TournamentEliminationStyle.DOUBLE)) {
                toDrop.add(participant);
            }
        }
        
        for(E participant : toDrop) {
            this.dropParticipant(participant);
        }
    }
    
    /*
    *   Round Relations
    */
    
    
    public final void createNewTournamentRound(
            TournamentPairingSystem system,
            TournamentEliminationStyle style,
            int minPlayers,
            int maxPlayers) {
        
        if(style == TournamentEliminationStyle.SINGLE || style == TournamentEliminationStyle.DOUBLE) {
            dropEliminatedPlayers(style);
        }
        
        TournamentRound round = 
                PairingFactory.generateMatches(
                        allParticipants, 
                        minPlayers, 
                        maxPlayers, 
                        system);
        
        this.eventRounds.add(round);
    }
    
    public ArrayList<TournamentMatch> getRoundMatches(int roundIndex) {
        return eventRounds.get(roundIndex).getMatches();
    }
    
    public void deleteRound(int roundIndex) {
        eventRounds.remove(roundIndex).cleanupRound();
    }
}