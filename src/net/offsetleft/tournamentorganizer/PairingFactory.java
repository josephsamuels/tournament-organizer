package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;
import java.util.Collections;

public final class PairingFactory {
    public enum RematchesAllowed {
        YES, NO;
    }
    
    private PairingFactory() { }
    
    public static <E extends AbstractPlayer> ArrayList<EventMatch> generateMatches(
            ArrayList<E> players, 
            int minPlayers, 
            int maxPlayers) {
        
        return generateMatches(players, minPlayers, maxPlayers, RematchesAllowed.YES);
    }
    
    public static <E extends AbstractPlayer> ArrayList<EventMatch> generateMatches(
            ArrayList<E> players, 
            int minPlayers, 
            int maxPlayers, 
            RematchesAllowed rematches) {
        
        Collections.sort(players);
        
        PairingList<E> list = new PairingList(players, minPlayers, maxPlayers, rematches);
        
        return list.getPairings();
    }
    
    private static class PairingList <E extends AbstractPlayer> {
        private final PairingNode root;
        private final RematchesAllowed rematches;
        
        public PairingList(
                ArrayList<AbstractPlayer> players, 
                int minNodeSize, 
                int maxNodeSize, 
                RematchesAllowed rematches) {
            
            this.rematches = rematches;
            root = new PairingNode(null, minNodeSize, maxNodeSize);

            for(AbstractPlayer p : players) {
                root.addPlayer(p);
            }

            root.cleanupNodes();
        }

        public ArrayList<EventMatch> getPairings() {
            ArrayList<EventMatch> pairings = new ArrayList<>();
            PairingNode node = root;

            if(node != null) {
                pairings.add(node.getMatch());
                
                while(node.hasNext()) {
                    node = node.getNext();
                    
                    pairings.add(node.getMatch());
                }
            }

            return pairings;
        }
        
        private class PairingNode {
            private final ArrayList<AbstractPlayer> players;
            private final PairingNode previous; 
            private final int minNodeSize, maxNodeSize;
            private PairingNode next;

            public PairingNode(PairingNode previous,
                    int minNodeSize,
                    int maxNodeSize) {
                players = new ArrayList<>();

                this.previous = previous;
                this.minNodeSize = minNodeSize;
                this.maxNodeSize = maxNodeSize;
            }

            public void addPlayer(AbstractPlayer p) {
                if (getSize() < maxNodeSize && rematches == RematchesAllowed.NO) {
                    for (AbstractPlayer opponent : players) {
                        if (p.hasPlayed(opponent)) {
                            passToNext(p);
                            return;
                        }
                    }

                    players.add(p);
                } else if (getSize() < maxNodeSize) {
                    players.add(p);
                } else {
                    passToNext(p);
                }
            }

            private void passToNext(AbstractPlayer p) {
                if (this.hasNext()) {
                    next.addPlayer(p);
                } else {
                    next = new PairingNode(this, minNodeSize, maxNodeSize);

                    next.addPlayer(p);
                }
            }

            public boolean hasNext() {
                return next != null;
            }

            public PairingNode getNext() {
                return next;
            }

            public EventMatch getMatch() {
                EventMatch match = new EventMatch();
                
                for(AbstractPlayer p : players) {
                    match.addParticipant(p);
                }
                
                return match;
            }

            public PairingNode getPrevious() {
                return previous;
            }

            public int getSize() {
                return players.size();
            }

            public void cleanupNodes() {
                if (next != null) {
                    next.cleanupNodes();
                }

                while (previous != null
                        && getSize() < minNodeSize
                        && previous.getSize() > minNodeSize) {
                    players.add(previous.surrenderLast());
                }

                if(getSize() == 1 && players.get(0).hasHadBye() && previous != null) {
                    boolean matchFound = false;
                    AbstractPlayer toPair = players.get(0);
                    AbstractPlayer potentialOp;
                    PairingNode currentNodeToCheck = previous;

                    while(!matchFound && currentNodeToCheck != null) {
                        for(int i = 0; i < currentNodeToCheck.getSize(); i++) {
                            potentialOp = currentNodeToCheck.getMatch().getParticipants().get(i);

                            if(!potentialOp.hasPlayed(toPair)) {
                                players.add(currentNodeToCheck.surrenderPlayer(potentialOp));
                                matchFound = true;
                                break;
                            }
                        }

                        currentNodeToCheck = currentNodeToCheck.getPrevious();
                    }
                }
            }

            public AbstractPlayer surrenderLast() {
                return players.remove(players.size() - 1);
            }

            public AbstractPlayer surrenderPlayer(AbstractPlayer p) {
                return players.remove(players.indexOf(p));
            }
        }
    }
}
