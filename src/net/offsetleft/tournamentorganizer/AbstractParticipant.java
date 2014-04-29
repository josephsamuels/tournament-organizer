package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

/**
 * Abstract class that contains three properties. Includes a number of standard
 * methods that are helpful to managing a participant in an event. Must 
 * implement the compareTo method in order for this package to work properly.
 * 
 * @author josephsamuels
 */
public abstract class AbstractParticipant implements Comparable<AbstractParticipant> {
    /**
     * List used to contain the matches the player participated in.
     */
    protected final ArrayList<EventMatch> matches = new ArrayList<>();
    
    /**
     * String that contain the player's first and last names.
     */
    protected final String surname, givenName;
    
    /**
     * Default constructor that must be called using super from the implementing
     * class.
     * 
     * @param   surname     the participant's family name.
     * @param   givenName   the participant's given name.
     */
    public AbstractParticipant(String surname, String givenName) {
        this.surname = surname;
        this.givenName = givenName;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public final String getGivenName() {
        return this.givenName;
    }
    
    public final String getSurname() {
        return this.surname;
    }
    
    public final ArrayList<EventMatch> getMatches() {
        return this.matches;
    }
    
    public final void addMatch(EventMatch e) {
        this.matches.add(e);
    }
    
    public final boolean removeMatch(EventMatch e) {
        return this.matches.remove(e);
    }
    
    public final int getLossCount() {
        int lossCount = 0;
        
        for(EventMatch match : matches) {
            if(match.getResult(this) != 0) {
                lossCount++;
            }
        }
        
        return lossCount;
    }
    
    public final boolean hasHadBye() {
        for(EventMatch match : this.matches) {
            if(match.getParticipants().size() == 1) {
                return true;
            }
        }
        
        return false;
    }
    
    public final boolean hasPlayed(AbstractParticipant player) {
        for(EventMatch match : this.matches) {
            if(match.wasParticipant(player)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int alphaCompare(AbstractParticipant o) {
        if(surname.compareTo(o.surname) != 0) {
            return surname.compareTo(o.surname);
        } else if(givenName.compareTo(o.givenName) != 0) {
            return givenName.compareTo(o.givenName);
        }

        return 0;
    }
    
    @Override
    public String toString() {
        return surname + ", " + surname;
    }
}
