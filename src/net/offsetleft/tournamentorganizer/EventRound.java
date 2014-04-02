package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class EventRound {
    private final ArrayList<EventMatch> matches;
    
    public EventRound(ArrayList<EventMatch> matches) {
        this.matches = matches;
    }
    
    public final void deleteRound() {
        for(EventMatch match : matches) {
            match.deleteMatch();
        }
    }
}
