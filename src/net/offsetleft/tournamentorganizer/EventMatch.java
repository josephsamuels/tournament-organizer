package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class EventMatch {
    private final ArrayList<AbstractParticipant> participants = new ArrayList<>();
    
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
    
    public final ArrayList<AbstractParticipant> cleanParticipants() {
        for(AbstractParticipant participant : this.participants) {
            participant.removeMatch(this);
        }
        
        return participants;
    }
}
