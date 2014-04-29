package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class EventMatch {
    private final ArrayList<AbstractParticipant> participants = new ArrayList<>();
    private final ArrayList<AbstractParticipant> results = new ArrayList<>();
    
    public final void addParticipant(AbstractParticipant player) {
        participants.add(player);
        player.addMatch(this);
    }
    
    public final ArrayList<AbstractParticipant> getParticipants() {
        return this.participants;
    }
    
    public final boolean wasParticipant(AbstractParticipant player) {
        return this.participants.contains(player);
    }
    
    public final void setResults(ArrayList<AbstractParticipant> results) {
        for(AbstractParticipant particpant : results) {
            this.results.add(particpant);
        }
    }
    
    public final int getResult(AbstractParticipant player) {
        return results.indexOf(player);
    }
    
    public final void clearResults() {
        this.results.clear();
    }
    
    public final ArrayList<AbstractParticipant> cleanParticipants() {
        for(AbstractParticipant participant : this.participants) {
            participant.removeMatch(this);
        }
        
        return participants;
    }
}
