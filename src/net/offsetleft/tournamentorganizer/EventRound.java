package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class EventRound {
    private final ArrayList<EventMatch> matches;
    
    public EventRound(ArrayList<EventMatch> matches) {
        this.matches = matches;
    }
    
    public final void addMatch(EventMatch match) {
        this.matches.add(match);
    }
    
    public final ArrayList<EventMatch> getMatches() {
        return this.matches;
    }
    
    public final void deleteRound() {
        for(EventMatch match : matches) {
            match.deleteMatch();
        }
    }
}
