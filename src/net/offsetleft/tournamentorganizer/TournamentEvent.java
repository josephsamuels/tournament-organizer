package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;
import java.util.Collections;

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
        if(this.roundCount() > 0) {
            throw new IllegalStateException(
                    "You cannot delete a particpant after the event has started.");
        } else {
            this.allParticipants.remove(participant);
            this.activeParticipants.remove(participant);
            this.droppedPartcipants.remove(participant);
        }
    }
    
    public final void dropParticipant(E participant) {
        if(this.activeParticipants.contains(participant) 
                && !droppedPartcipants.contains(participant)) {
            this.activeParticipants.remove(participant);
            this.droppedPartcipants.add(participant);
        }
    }
    
    public final void reinstateParticipant(E participant) {
        if(!this.activeParticipants.contains(participant) 
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
        
        for(E participant : this.activeParticipants) {
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
        
        if(this.eventRounds.size() <= 0) {
            Collections.shuffle(activeParticipants);
        }
        
        if(style == TournamentEliminationStyle.SINGLE || style == TournamentEliminationStyle.DOUBLE) {
            this.dropEliminatedPlayers(style);
        }
        
        TournamentRound round = 
                PairingFactory.generateMatches(
                        activeParticipants, 
                        minPlayers, 
                        maxPlayers, 
                        system);
        
        this.eventRounds.add(round);
    }
    
    public final ArrayList<TournamentMatch> getRoundMatches(int roundIndex) {
        return this.round(roundIndex).getMatches();
    }
    
    public final void deleteRound(int roundIndex) {
        TournamentRound round = this.round(roundIndex);
        
        if(roundIndex == this.roundCount()-1) {
            if(!round.roundHasResults()) {
                eventRounds.remove(round);
                round.cleanupRound();
            } else {
                throw new IllegalStateException(
                        "Cannot delete round that contains results.");
            }
        } else {
            throw new IllegalArgumentException(
                    "Cannot delete farther back than the previous round.");
        }
    }
    
    /*
    *   Match relations.
    */
    
    
    public final void submitMatchResults(
            int roundIndex, 
            int matchIndex,
            ArrayList<AbstractParticipant> resultSet) {
        this.round(roundIndex).match(matchIndex).setResults(resultSet);
    }
    
    public final void deleteMatchResults(
            int roundIndex,
            int matchIndex) {
        this.round(roundIndex).match(matchIndex).clearResults();
    }
    
    public final int getMatchRound(TournamentMatch match) {
        for(int i = 0; i < this.roundCount(); i++) {
            if(this.round(i).containsMatch(match)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public final int getMatchIndex(TournamentMatch match) {
        int roundIndex = this.getMatchRound(match);
        
        if(roundIndex != -1) {
            return this.round(roundIndex).getMatchIndex(match);
        }
        
        return -1;
    }
    
    public final ArrayList<AbstractParticipant> getMatchParticipants(
            int roundIndex, int matchIndex) {
        return this.round(roundIndex).match(matchIndex).getParticipants();
    }
    
    public final ArrayList<AbstractParticipant> deleteMatch(TournamentMatch match) {
        int roundIndex = this.getMatchRound(match);
        
        match.cleanupMatch();
        
        return this.round(roundIndex).deleteMatch(match);
    }
    
    public final void createMatchInRound(
            ArrayList<AbstractParticipant> participants,
            int roundIndex) {
        TournamentMatch m = new TournamentMatch();
        
        for(AbstractParticipant p : participants) {
            m.addParticipant(p);
        }
        
        this.round(roundIndex).addMatch(m);
    }
    
    public final TournamentRound round(int roundIndex) {
        return this.eventRounds.get(roundIndex);
    }
    
    public final int roundCount() {
        return this.eventRounds.size();
    }
}