package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public abstract class AbstractParticipant implements Comparable<AbstractParticipant> {
    protected final ArrayList<EventMatch> matches = new ArrayList<>();
    protected final String surname, givenName;
    
    public AbstractParticipant(String surname, String givenName) {
        this.surname = surname;
        this.givenName = givenName;
    }
    
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
        return givenName + " " + surname;
    }
}
