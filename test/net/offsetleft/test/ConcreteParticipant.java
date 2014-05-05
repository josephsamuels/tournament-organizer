package net.offsetleft.test;

import net.offsetleft.tournamentorganizer.AbstractParticipant;
import net.offsetleft.tournamentorganizer.TournamentMatch;

public class ConcreteParticipant extends AbstractParticipant {
    
    public ConcreteParticipant(String surname, String givenName) {
        super(surname, givenName);
    }
    
    public int getPoints() {
        int totalPoints = 0;
        
        for(TournamentMatch m : matches) {
            totalPoints += roundPoints(m);
        }
        
        return totalPoints;
    }
    
    private int roundPoints(TournamentMatch m) {
        int playerCount = m.getParticipants().size();
        int placing = m.getPlayersResult(this);
        
        if(playerCount <= 2) {
            switch(placing) {
                case 0:
                    return 3;
                case 1:
                    return 0;
            }
        } else if(playerCount > 2) {
            switch(placing) {
                case 0:
                    return 6;
                case 1:
                    return 3;
                case 2:
                    return 1;
                case 3:
                    return 0;
            }
        }
        
        return 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + this.getPoints() + ")";
    }
}
