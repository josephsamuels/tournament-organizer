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
    private final ArrayList<AbstractParticipant> matchParticipants = 
            new ArrayList<>();
    
    /**
     * List used to contain the results of the match.
     */
    private ArrayList<AbstractParticipant> matchResults;
    
    /**
     * Adds a participant to this match. Additionally add {@code this} match
     * to the participants list of matches they have been involved in.
     * 
     * @param p         the participant to add to this match.
     */
    public final void addParticipant(AbstractParticipant p) {
        matchParticipants.add(p);
        p.addMatch(this);
    }
    
    /**
     * Returns the list of participants involved in this match.
     * 
     * @return          the list of participants involved in this match.
     */
    public final ArrayList<AbstractParticipant> getParticipants() {
        return this.matchParticipants;
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
        return this.matchParticipants.contains(p);
    }
    
    /**
     * Used to set the results of the match. The provided argument should be an
     * ArrayList with the participants listed in the finishing order from the
     * match.
     * 
     * @param results   the list of participants in finishing order.
     */
    final void setResults(ArrayList<AbstractParticipant> results) {
        this.matchResults = results;
    }
    
    /**
     * Gets the results of the provided player if they were a participant in
     * {@code this} match.
     * 
     * @param player    the player to check.
     * @return          the player's finishing position.
     */
    public final int getPlayersResult(AbstractParticipant player) {
        if(matchResults.size() > 0)
            if(matchResults.contains(player))
                return matchResults.indexOf(player);
            else
                throw new NoSuchElementException(
                        "Player did not participate in this match");
        return -1;
    }
    
    /**
     * Clears the results of this match.
     */
    public final void clearResults() {
        this.matchResults = null;
    }
    
    /**
     * Helper method to be called while this match is in the process of being
     * removed from an event.
     * 
     * @return          an ArrayList of the former participants of this match.
     */
    final ArrayList<AbstractParticipant> cleanupMatch() {
        for(AbstractParticipant participant : this.matchParticipants) {
            participant.removeMatch(this);
        }
        
        return matchParticipants;
    }
    
    final boolean matchHasResults() {
        return !(this.matchResults == null);
    }
}
