/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.offsetleft.test;

import net.offsetleft.tournamentorganizer.AbstractPlayer;

/**
 *
 * @author josephsamuels
 */
public class ConcretePlayer extends AbstractPlayer {
    
    protected String firstName, lastName;
    
    public ConcretePlayer(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    @Override
    public int compareTo(AbstractPlayer o) {
        ConcretePlayer p = (ConcretePlayer)o;
        return lastName.compareTo(p.lastName);
    }
    
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
