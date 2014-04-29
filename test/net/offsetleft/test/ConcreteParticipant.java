/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.offsetleft.test;

import net.offsetleft.tournamentorganizer.AbstractParticipant;
import net.offsetleft.tournamentorganizer.TournamentMatch;

/**
 *
 * @author josephsamuels
 */
public class ConcreteParticipant extends AbstractParticipant {
    
    public ConcreteParticipant(String surname, String givenName) {
        super(surname, givenName);
    }
    
    private int calcualtePoints() {
        int totalPoints = 0;
        
        for(TournamentMatch m : matches) {
            totalPoints += roundPoints(m);
        }
        
        return totalPoints;
    }
    
    private int roundPoints(TournamentMatch m) {
        int playerCount = m.getParticipants().size();
        int placing = m.getResult(this);
        
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
    public int compareTo(AbstractParticipant o) {
        if(o instanceof ConcreteParticipant) {
            ConcreteParticipant op = (ConcreteParticipant)o;
            
            if(this.calcualtePoints() < op.calcualtePoints()) {
                return 1;
            } else if(this.calcualtePoints() > op.calcualtePoints()) {
                return -1;
            } else {
                return 0;
            }
        }
        
        return alphaCompare(o);
    }
    
    @Override
    public String toString() {
        return super.toString() + "(" + this.calcualtePoints() + ")";
    }
}
