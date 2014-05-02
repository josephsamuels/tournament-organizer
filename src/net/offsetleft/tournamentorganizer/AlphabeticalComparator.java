package net.offsetleft.tournamentorganizer;

import java.util.Comparator;

public class AlphabeticalComparator implements Comparator<AbstractParticipant> {

    @Override
    public int compare(AbstractParticipant o1, AbstractParticipant o2) {
        if(o1.surname.compareTo(o2.surname) != 0) {
            return o1.surname.compareTo(o2.surname);
        } else if(o1.givenName.compareTo(o2.givenName) != 0) {
            return o1.givenName.compareTo(o2.givenName);
        }

        return 0;
    }

}
