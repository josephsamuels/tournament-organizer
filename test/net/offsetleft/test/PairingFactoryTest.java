package net.offsetleft.test;

import java.util.ArrayList;
import net.offsetleft.tournamentorganizer.PairingFactory;
import net.offsetleft.tournamentorganizer.EventMatch;
import net.offsetleft.tournamentorganizer.AbstractPlayer;
import net.offsetleft.tournamentorganizer.EventRound;
import net.offsetleft.tournamentorganizer.TournamentEvent;

/**
 *
 * @author josephsamuels
 */
public class PairingFactoryTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConcretePlayer p1 = new ConcretePlayer("Leah", "Galka");
        ConcretePlayer p2 = new ConcretePlayer("Randy", "Belcher");
        ConcretePlayer p3 = new ConcretePlayer("Laurie", "Devendorf");
        ConcretePlayer p4 = new ConcretePlayer("Alex", "Dexter");
        ConcretePlayer p5 = new ConcretePlayer("Steve", "Kurilovitch");
        ConcretePlayer p6 = new ConcretePlayer("Bill", "Wallace");
        ConcretePlayer p7 = new ConcretePlayer("John", "Callan");
        ConcretePlayer p8 = new ConcretePlayer("Joe", "Samuels");
        
        TournamentEvent te = new TournamentEvent();
        
        te.addPlayer(p1);
        te.addPlayer(p2);
        te.addPlayer(p3);
        te.addPlayer(p4);
        te.addPlayer(p5);
        te.addPlayer(p6);
        te.addPlayer(p7);
        te.addPlayer(p8);
        
        ArrayList<ConcretePlayer> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        
        System.out.println("Round 1");
        EventRound round1 = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round1.getMatches().size(); i++) {
            EventMatch m = round1.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractPlayer> players = m.getParticipants();
            for(AbstractPlayer player : players) {
                ConcretePlayer p = (ConcretePlayer)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 2");
        EventRound round2 = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round2.getMatches().size(); i++) {
            EventMatch m = round2.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractPlayer> players = m.getParticipants();
            for(AbstractPlayer player : players) {
                ConcretePlayer p = (ConcretePlayer)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 3");
        EventRound round3 = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < round3.getMatches().size(); i++) {
            EventMatch m = round3.getMatches().get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractPlayer> players = m.getParticipants();            
            for(AbstractPlayer player : players) {
                ConcretePlayer p = (ConcretePlayer)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
    }
    
}
