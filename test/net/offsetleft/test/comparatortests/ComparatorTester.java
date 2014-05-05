package net.offsetleft.test.comparatortests;

import net.offsetleft.test.ConcreteParticipant;
import net.offsetleft.test.PointsComparator;
import java.util.ArrayList;
import java.util.Collections;
import net.offsetleft.tournamentorganizer.AlphabeticalComparator;
import net.offsetleft.tournamentorganizer.TournamentEliminationStyle;
import net.offsetleft.tournamentorganizer.TournamentEvent;
import net.offsetleft.tournamentorganizer.TournamentMatch;
import net.offsetleft.tournamentorganizer.TournamentPairingSystem;

public class ComparatorTester {
    public static void main(String args[]) {
        TournamentEvent te = new TournamentEvent();
        
        ConcreteParticipant p1 = new ConcreteParticipant("Galka", "Leah");
        ConcreteParticipant p2 = new ConcreteParticipant("Belcher", "Randy");
        ConcreteParticipant p3 = new ConcreteParticipant("Devendorf", "Laurie");
        ConcreteParticipant p4 = new ConcreteParticipant("Dexter", "Alex");
        ConcreteParticipant p5 = new ConcreteParticipant("Kurilovitch", "Steve");
        ConcreteParticipant p6 = new ConcreteParticipant("Wallace", "Bill");
        ConcreteParticipant p7 = new ConcreteParticipant("Callan", "John");
        ConcreteParticipant p8 = new ConcreteParticipant("Samuels", "Joe");
        
        te.addParticipant(p1);
        te.addParticipant(p2);
        te.addParticipant(p3);
        te.addParticipant(p4);
        te.addParticipant(p5);
        te.addParticipant(p6);
        te.addParticipant(p7);
        te.addParticipant(p8);
        
        ArrayList<ConcreteParticipant> alphaSorted = new ArrayList(te.getAllParticipants());
        
        System.out.println("Alphabetical Sort Test");
        System.out.println("Pre-sort");
        System.out.println("Origin List: " + te.getActiveParticipants());
        System.out.println("Copied List: " + alphaSorted);
        
        Collections.sort(alphaSorted, new AlphabeticalComparator());
        
        System.out.println();
        System.out.println("Post-sort");
        System.out.println("Origin List: " + te.getActiveParticipants());
        System.out.println("Copied List: " + alphaSorted);
        
        System.out.println();
        System.out.println();
        
        te.createNewTournamentRound(TournamentPairingSystem.SWISS, 
                TournamentEliminationStyle.NONE, 1, 2);
        ArrayList<TournamentMatch> round1 = te.getRoundMatches(0);
        
        for(TournamentMatch m : round1) {
            int roundIndex = te.getMatchRound(m);
            int matchIndex = te.getMatchIndex(m);
            
            ArrayList<ConcreteParticipant> matchParticipants = 
                    te.getMatchParticipants(roundIndex, matchIndex);
            
            te.submitMatchResults(roundIndex, matchIndex, matchParticipants);
        }
        
        ArrayList<ConcreteParticipant> pointsSorted = new ArrayList(te.getAllParticipants());
        
        System.out.println("Points Sort Test");
        System.out.println("Pre-sort");
        System.out.println("Origin List: " + te.getActiveParticipants());
        System.out.println("Copied List: " + pointsSorted);
        
        Collections.sort(pointsSorted, new PointsComparator());
        
        System.out.println();
        System.out.println("Post-sort");
        System.out.println("Origin List: " + te.getActiveParticipants());
        System.out.println("Copied List: " + pointsSorted);
    }
}
