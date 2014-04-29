package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

/**
 * Composite class that contains an ArrayList of EventMatch objects as its sole
 * property. Includes a number of helper methods to help with the creation, 
 * modification and deletion of a round of an event.
 * 
 * @author Joseph Samuels
 */
public final class EventRound {
    /**
     * List used to contain the matches of this round.
     */
    private final ArrayList<EventMatch> matches;
    
    /**
     * Constructs an object representing a round of an event using the list of
     * matches provided in the constructor.
     * 
     * @param   matches the matches that represent the pairings for the round
     */
    public EventRound(ArrayList<EventMatch> matches) {
        this.matches = matches;
    }
    
    /**
     * Adds a match to the round.
     * 
     * @param   match   the match to add to the round
     */
    public final void addMatch(EventMatch match) {
        this.matches.add(match);
    }
    
    /**
     * Deletes a match from the round. Calls EventMatch's cleanParticipants() 
     * method which removes the match from the participating player's match
     * log.
     * 
     * @param   match   the match to be removed from the round
     * @return          the list of players that were assigned to this match
     */
    public final ArrayList<AbstractParticipant> deleteMatch(EventMatch match) {
        return match.cleanParticipants();
    }
    
    /**
     * Returns the list of matches for this round.
     * 
     * @return          the list of matches for this round
     */
    public final ArrayList<EventMatch> getMatches() {
        return this.matches;
    }
}
