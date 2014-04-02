/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.offsetleft.test;

import java.util.ArrayList;
import net.offsetleft.tournamentorganizer.PairingFactory;
import net.offsetleft.tournamentorganizer.EventMatch;
import net.offsetleft.tournamentorganizer.AbstractPlayer;

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
        ArrayList<EventMatch> matches = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < matches.size(); i++) {
            EventMatch m = matches.get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractPlayer> players = m.getParticipants();
            for(AbstractPlayer player : players) {
                ConcretePlayer p = (ConcretePlayer)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 2");
        ArrayList<EventMatch> matches2 = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < matches2.size(); i++) {
            EventMatch m = matches2.get(i);
            System.out.println("Match " + i);
            
            ArrayList<AbstractPlayer> players = m.getParticipants();
            for(AbstractPlayer player : players) {
                ConcretePlayer p = (ConcretePlayer)player;
                System.out.println(p);
            }
            
            System.out.println();
        }
        
        System.out.println("Round 3");
        ArrayList<EventMatch> matches3 = PairingFactory.generateMatches(list, 1, 2, PairingFactory.RematchesAllowed.NO);
        for(int i = 0; i < matches3.size(); i++) {
            EventMatch m = matches3.get(i);
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
