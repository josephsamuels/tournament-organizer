package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

/**
 * Abstract composite class that contains three properties. Includes a number 
 * of standard methods that help to manage a participant in an event. Must 
 * implement the compareTo method in order for this package to work properly.
 * 
 * @author Joseph W. Samuels
 */
public abstract class AbstractParticipant implements 
        Comparable<AbstractParticipant> {
    /**
     * List used to contain the matches the player participated in.
     */
    protected final ArrayList<TournamentMatch> matches = new ArrayList<>();
    
    /**
     * Strings that contain the player's first and last names.
     */
    protected final String surname, givenName;
    
    /**
     * Default constructor that must be called using super from the implementing
     * class.
     * 
     * @param   surname     the participant's family name
     * @param   givenName   the participant's given name
     */
    public AbstractParticipant(String surname, String givenName) {
        this.surname = surname;
        this.givenName = givenName;
    }
    
    /**
     * Getter for the givenName property.
     * 
     * @return              the participant's given name
     */
    public final String getGivenName() {
        return this.givenName;
    }
    
    /**
     * Getter for the surname property.
     * 
     * @return              the participant's family name
     */
    public final String getSurname() {
        return this.surname;
    }
    
    /**
     * Returns the list of matches {@code this} has participated in.
     * 
     * @return              the list of matches for this participant
     */
    public final ArrayList<TournamentMatch> getMatches() {
        return this.matches;
    }
    
    /**
     * Adds a match to the list of matches {@code this} was a participant in.
     * 
     * @param match         the match to add to the list
     */
    public final void addMatch(TournamentMatch match) {
        this.matches.add(match);
    }
    
    /**
     * Removes a match from the list of matches {@code this} was a 
     * participant in.
     * 
     * @param match         the match to remove from the list
     * @return              a boolean representing the result of 
     *                      {@code ArrayList}'s remove action
     */
    public final boolean removeMatch(TournamentMatch match) {
        return this.matches.remove(match);
    }
    
    /**
     * Returns the loss count for {@code this} participant. A loss is 
     * considered to be any match where the participant did not place first.
     * 
     * @return              an int representing the total number of losses
     */
    public final int getLossCount() {
        int lossCount = 0;
        
        for(TournamentMatch match : matches) {
            if(match.getResult(this) != 0) {
                lossCount++;
            }
        }
        
        return lossCount;
    }
    
    /**
     * Returns the number of matches P@code this} has participated in.
     * 
     * @return              an int representing the number of rounds the
     *                      participant was involved in.
     */
    public final int roundsPlayed() {
        return matches.size();
    }
    
    /**
     * Returns if {@code this} participant has had a bye or not. A bye is 
     * considered to be any match where {@code this} was the only participant.
     * 
     * @return              a boolean representing if {@code this} has had a bye
     */
    public final boolean hasHadBye() {
        for(TournamentMatch match : this.matches) {
            if(match.getParticipants().size() == 1) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * A test to see if {@code this} participant has played against 
     * {@code player} in a previous round.
     * 
     * @param player        the participant we wish to check
     * @return              a boolean representing if {@code this} has played
     *                      {@code player}
     */
    public final boolean hasPlayed(AbstractParticipant player) {
        for(TournamentMatch match : this.matches) {
            if(match.wasParticipant(player)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns a String object in last name first, first name last format.
     * 
     * @return              a string representation of this object in last name
     *                      first, first name last format.
     */
    @Override
    public String toString() {
        return surname + ", " + givenName;
    }
}
