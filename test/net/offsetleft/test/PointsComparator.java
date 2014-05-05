package net.offsetleft.test;

import java.util.Comparator;

public class PointsComparator implements Comparator<ConcreteParticipant> {

    @Override
    public int compare(ConcreteParticipant o1, ConcreteParticipant o2) {
        if(o1.getPoints() > o2.getPoints())
            return -1;
        else if(o1.getPoints() < o2.getPoints()) {
            return 1;
        }
        
        return 0;
    }
    
}
