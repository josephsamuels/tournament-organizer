package net.offsetleft.test;

import java.util.ArrayList;
import net.offsetleft.tournamentorganizer.PairingFactory;
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
        
        TournamentEvent te = new TournamentEvent(1, 2, TournamentStyle.SWISS);
        
        te.addParticipant(p1);
        te.addParticipant(p2);
        te.addParticipant(p3);
        te.addParticipant(p4);
        te.addParticipant(p5);
        te.addParticipant(p6);
        te.addParticipant(p7);
        te.addParticipant(p8);
        
        System.out.println("Round 1");
        EventRound round1 = PairingFactory.generateMatches(
                te.getActiveParticipantList(), 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round1.getMatches().size(); i++) {
            EventMatch m = round1.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 2");
        EventRound round2 = PairingFactory.generateMatches(te.getActiveParticipantList(), 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round2.getMatches().size(); i++) {
            EventMatch m = round2.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 3");
        EventRound round3 = PairingFactory.generateMatches(te.getActiveParticipantList(), 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round3.getMatches().size(); i++) {
            EventMatch m = round3.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractParticipant> players = m.getParticipants();            
            for(AbstractParticipant player : players) {
                ConcreteParticipant p = (ConcreteParticipant)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        te.cutToTop(4);
        
        System.out.println(te.getActiveParticipantList());
        
        System.out.println("Playoff Round");
        EventRound playoffRound1 = PairingFactory.generatePlayoffMatches(te.getActiveParticipantList(), 1, 2);
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
