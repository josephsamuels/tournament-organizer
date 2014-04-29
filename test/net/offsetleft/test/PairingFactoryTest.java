package net.offsetleft.test;

import java.util.ArrayList;
import net.offsetleft.tournamentorganizer.EventMatch;
import net.offsetleft.tournamentorganizer.AbstractParticipant;
import net.offsetleft.tournamentorganizer.EventRound;
import net.offsetleft.tournamentorganizer.TournamentEvent;
import net.offsetleft.tournamentorganizer.TournamentStyle;

/**
 *
 * @author josephsamuels
 */
public class PairingFactoryTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConcreteParticipant p1 = new ConcreteParticipant("Galka", "Leah");
        ConcreteParticipant p2 = new ConcreteParticipant("Belcher", "Randy");
        ConcreteParticipant p3 = new ConcreteParticipant("Devendorf", "Laurie");
        ConcreteParticipant p4 = new ConcreteParticipant("Dexter", "Alex");
        ConcreteParticipant p5 = new ConcreteParticipant("Kurilovitch", "Steve");
        ConcreteParticipant p6 = new ConcreteParticipant("Wallace", "Bill");
        ConcreteParticipant p7 = new ConcreteParticipant("Callan", "John");
        ConcreteParticipant p8 = new ConcreteParticipant("Samuels", "Joe");
        
        TournamentEvent te = new TournamentEvent(TournamentStyle.SWISS);
        
        te.addParticipant(p1);
        te.addParticipant(p2);
        te.addParticipant(p3);
        te.addParticipant(p4);
        te.addParticipant(p5);
        te.addParticipant(p6);
        te.addParticipant(p7);
        te.addParticipant(p8);
        
        System.out.println("Round 1");
        te.createRound();
        EventRound round1 = te.getRound(0);
        for(int i = 0; i < round1.getMatches().size(); i++) {
            EventMatch m = round1.getMatches().get(i);
            
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
            m.setResults(players);
        }
        
        System.out.println("Round 2");
        te.createRound();
        EventRound round2 = te.getRound(1);
        for(int i = 0; i < round2.getMatches().size(); i++) {
            EventMatch m = round2.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
            m.setResults(players);
        }
        
        System.out.println("Round 3");
        te.createRound();
        EventRound round3 = te.getRound(2);
        for(int i = 0; i < round3.getMatches().size(); i++) {
            EventMatch m = round3.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();
           
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
            m.setResults(players);
        }
        
        System.out.println("Playoff Round");
        te.cutToTop(4);
        te.createPlayoffRound();
        EventRound playoffRound1 = te.getRound(3);
        for(int i = 0; i < playoffRound1.getMatches().size(); i++) {
            EventMatch m = playoffRound1.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();            
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
    }
    
}
