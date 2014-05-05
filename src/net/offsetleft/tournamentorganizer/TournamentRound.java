package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

/**
 * Composite class that contains an ArrayList of EventMatch objects as its sole
 * property. Includes a number of helper methods to help with the creation, 
 * modification and deletion of a round of an event.
 * 
 * @author Joseph W. Samuels
 */
public final class TournamentRound {
    /**
     * List used to contain the matches of this round.
     */
    private final ArrayList<TournamentMatch> roundMatches;
    
    /**
     * Constructs an object representing a round of an event using the list of
     * matches provided in the constructor.
     * 
     * @param   matches the matches that represent the pairings for the round
     */
    TournamentRound(ArrayList<TournamentMatch> matches) {
        this.roundMatches = matches;
    }
    
    /**
     * Adds a match to the round.
     * 
     * @param   match   the match to add to the round
     */
    final void addMatch(TournamentMatch match) {
        this.roundMatches.add(match);
    }
    
    /**
     * Deletes a match from the round. Calls EventMatch's cleanParticipants() 
     * method which removes the match from the participating player's match
     * log.
     * 
     * @param   match   the match to be removed from the round
     * @return          the list of players that were assigned to this match
     */
    final ArrayList<AbstractParticipant> deleteMatch(
            TournamentMatch match) {
        return match.cleanupMatch();
    }
    
    /**
     * Returns the list of matches for this round.
     * 
     * @return          the list of matches for this round
     */
    final ArrayList<TournamentMatch> getMatches() {
        return this.roundMatches;
    }
    
    /**
     * Cleans up participants in the round. Should be called prior to deleting
     * the round.
     */
    final void cleanupRound() {
        for(TournamentMatch m : this.roundMatches) {
            m.cleanupMatch();
        }
    }
    
    /**
     * Checks to see if this round contains a specific TournamentMatch object.
     * 
     * @param match     the TournamentMatch to check for.
     * @return          boolean value representing if the match was found.
     */
    final boolean containsMatch(TournamentMatch match) {
        return this.roundMatches.contains(match);
    }

    /**
     * Gets the index value for a specific TournamentMatch object.
     * 
     * @param match     the Tournament match to check for.
     * @return          an int value representing the TournamentMatch's index.
     */
    final int getMatchIndex(TournamentMatch match) {
        return this.roundMatches.indexOf(match);
    }
    
    /**
     * Returns a specific TournamentMatch based on the provided index value.
     * 
     * @param matchIndex    the index value to return.
     * @return              the TournamentMatch object at the provided index.
     */
    public final TournamentMatch match(int matchIndex) {
        return this.roundMatches.get(matchIndex);
    }
    
    final boolean roundHasResults() {
        for(TournamentMatch e : this.roundMatches) {
            if(e.matchHasResults())
                return true;
        }
        
        return false;
    }
}
