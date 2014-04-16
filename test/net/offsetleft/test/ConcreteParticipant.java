/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.offsetleft.test;

import net.offsetleft.tournamentorganizer.AbstractParticipant;

/**
 *
 * @author josephsamuels
 */
public class ConcreteParticipant extends AbstractParticipant {
    
    public ConcreteParticipant(String surname, String givenName) {
        super(surname, givenName);
    }
    
    @Override
    public int compareTo(AbstractParticipant o) {
        return alphaCompare(o);
    }
    
}
