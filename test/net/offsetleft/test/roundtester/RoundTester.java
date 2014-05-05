/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.offsetleft.test.roundtester;

import java.util.ArrayList;
import java.util.Collections;
import net.offsetleft.test.ConcreteParticipant;
import net.offsetleft.test.PointsComparator;
import net.offsetleft.tournamentorganizer.TournamentEliminationStyle;
import net.offsetleft.tournamentorganizer.TournamentEvent;
import net.offsetleft.tournamentorganizer.TournamentMatch;
import net.offsetleft.tournamentorganizer.TournamentPairingSystem;

/**
 *
 * @author josephsamuels
 */
public class RoundTester {
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
        
        System.out.println("Participant List");
        System.out.println(te.getActiveParticipants());
        
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
        
        Collections.sort(te.getActiveParticipants(), new PointsComparator());
        
        System.out.println("After Round 1");
        System.out.println(te.getActiveParticipants());
        
        System.out.println();
        System.out.println("Deleting Round 1");
        te.deleteRound(0);
        System.out.println(te.getActiveParticipants());
    }
}
