package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Composite class that contains two {@code ArrayList}s as its properties. One 
 * {@code ArrayList} represents the participants in a match, while the other
 * contains the results of the match.
 * 
 * @author Joseph W. Samuels
 */
public final class TournamentMatch {
    /**
     * List used to contain the players participating in the match.
     */
    private final ArrayList<AbstractParticipant> participants = 
            new ArrayList<>();
    
    /**
     * List used to contain the results of the match.
     */
    private final ArrayList<AbstractParticipant> results = new ArrayList<>();
    
    /**
     * Adds a participant to this match. Additionally add {@code this} match
     * to the participants list of matches they have been involved in.
     * 
     * @param p         the participant to add to this match.
     */
    public final void addParticipant(AbstractParticipant p) {
        participants.add(p);
        p.addMatch(this);
    }
    
    /**
     * Returns the list of participants involved in this match.
     * 
     * @return          the list of participants involved in this match.
     */
    public final ArrayList<AbstractParticipant> getParticipants() {
        return this.participants;
    }
    
    /**
     * Determines if the participant passed as an argument was involved in 
     * {@code this} match.
     * 
     * @param p         the participant to check.
     * @return          a boolean value representing if this player did or did
     *                  not participate in this match.
     */
    public final boolean wasParticipant(AbstractParticipant p) {
        return this.participants.contains(p);
    }
    
    /**
     * Used to set the results of the match. The provided argument should be an
     * ArrayList with the participants listed in the finishing order from the
     * match.
     * 
     * @param results   the list of participants in finishing order.
     */
    public final void setResults(ArrayList<AbstractParticipant> results) {
        for(AbstractParticipant particpant : results) {
            this.results.add(particpant);
        }
    }
    
    /**
     * Gets the results of the provided player if they were a participant in
     * {@code this} match.
     * 
     * @param player
     * @return 
     */
    public final int getResult(AbstractParticipant player) {
        if(results.size() > 0)
            if(results.contains(player))
                return results.indexOf(player);
            else
                throw new NoSuchElementException(
                        "Player did not participate in this match");
        return -1;
    }
    
    /**
     * Clears the results of this match.
     */
    public final void clearResults() {
        this.results.clear();
    }
    
    /**
     * Helper method to be called while this match is in the process of being
     * removed from an event.
     * 
     * @return          an ArrayList of the former participants of this match.
     */
    public final ArrayList<AbstractParticipant> cleanParticipants() {
        for(AbstractParticipant participant : this.participants) {
            participant.removeMatch(this);
        }
        
        return participants;
    }
}
