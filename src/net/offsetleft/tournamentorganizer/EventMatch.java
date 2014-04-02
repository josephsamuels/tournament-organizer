package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class EventMatch {
    private final ArrayList<AbstractPlayer> participants = new ArrayList<>();
    
    public final void addParticipant(AbstractPlayer player) {
        participants.add(player);
        player.addMatch(this);
    }
    
    public final ArrayList<AbstractPlayer> getParticipants() {
        return this.participants;
    }
    
    public final boolean wasParticipant(AbstractPlayer player) {
        return this.participants.contains(player);
    }
    
    public final void deleteMatch() {
        for(AbstractPlayer participant : this.participants) {
            participant.removeMatch(this);
        }
    }
}
