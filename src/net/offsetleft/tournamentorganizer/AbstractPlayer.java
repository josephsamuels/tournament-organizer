package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public abstract class AbstractPlayer implements Comparable<AbstractPlayer> {
    protected final ArrayList<EventMatch> matches = new ArrayList<>();
    
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
    
    public final boolean hasPlayed(AbstractPlayer player) {
        for(EventMatch match : this.matches) {
            if(match.wasParticipant(player)) {
                return true;
            }
        }
        
        return false;
    }
}
