package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

final class PairingFactory {
    enum RematchesAllowed {
        YES, NO;
    }
    
    private PairingFactory() { }
    
    static <E extends AbstractParticipant> TournamentRound generateMatches(
            ArrayList<E> players, 
            int minPlayers, 
            int maxPlayers) {
        
        return generateMatches(
                players, minPlayers, maxPlayers, RematchesAllowed.YES);
    }
    
    static <E extends AbstractParticipant> TournamentRound generateMatches(
            ArrayList<E> players, 
            int minPlayers, 
            int maxPlayers, 
            RematchesAllowed rematches) {
        
        PairingList<E> list = 
                new PairingList(players, minPlayers, maxPlayers, rematches);
        
        return list.getPairings();
    }
    
    private static class PairingList <E extends AbstractParticipant> {
        private final PairingNode root;
        private final RematchesAllowed rematches;
        
        private PairingList(
                ArrayList<AbstractParticipant> players, 
                int minNodeSize, 
                int maxNodeSize, 
                RematchesAllowed rematches) {
            
            this.rematches = rematches;
            root = new PairingNode(null, minNodeSize, maxNodeSize);

            for(AbstractParticipant p : players) {
                root.addPlayer(p);
            }

            root.cleanupNodes();
        }
        
        private TournamentRound getPairings() {
            ArrayList<TournamentMatch> pairings = new ArrayList<>();
            PairingNode node = root;

            if(node != null) {
                pairings.add(node.getMatch());
                
                while(node.hasNext()) {
                    node = node.getNext();
                    
                    pairings.add(node.getMatch());
                }
            }

            TournamentRound round = new TournamentRound(pairings);
            
            return round;
        }
        
        private class PairingNode {
            private final ArrayList<AbstractParticipant> players;
            private final PairingNode previous; 
            private final int minNodeSize, maxNodeSize;
            private PairingNode next;

            private PairingNode(PairingNode previous,
                    int minNodeSize,
                    int maxNodeSize) {
                players = new ArrayList<>();

                this.previous = previous;
                this.minNodeSize = minNodeSize;
                this.maxNodeSize = maxNodeSize;
            }

            private void addPlayer(AbstractParticipant p) {
                if (getSize() < maxNodeSize 
                        && rematches == RematchesAllowed.NO) {
                    for (AbstractParticipant opponent : players) {
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
            
            private void passToNext(AbstractParticipant p) {
                if (!this.hasNext()) {
                    next = new PairingNode(this, minNodeSize, maxNodeSize);
                }
                
                next.addPlayer(p);
            }
            
            private boolean hasNext() {
                return next != null;
            }
            
            private PairingNode getNext() {
                return next;
            }
            
            private TournamentMatch getMatch() {
                TournamentMatch match = new TournamentMatch();
                
                for(AbstractParticipant p : players) {
                    match.addParticipant(p);
                }
                
                return match;
            }
            
            private PairingNode getPrevious() {
                return previous;
            }
            
            private int getSize() {
                return players.size();
            }
            
            private void cleanupNodes() {
                if (next != null) {
                    next.cleanupNodes();
                }
                
                while (previous != null
                        && getSize() < minNodeSize
                        && previous.getSize() > minNodeSize) {
                    players.add(previous.surrenderLast());
                }
                
                if(getSize() == 1 
                        && players.get(0).hasHadBye() 
                        && previous != null) {
                    boolean matchFound = false;
                    AbstractParticipant toPair = players.get(0);
                    AbstractParticipant potentialOp;
                    PairingNode currentNodeToCheck = previous;

                    while(!matchFound && currentNodeToCheck != null) {
                        for(int i = 0; i < currentNodeToCheck.getSize(); i++) {
                            potentialOp = 
                                    currentNodeToCheck
                                            .getMatch()
                                            .getParticipants().get(i);

                            if(!potentialOp.hasPlayed(toPair)) {
                                players.add(
                                        currentNodeToCheck
                                                .surrenderPlayer(potentialOp));
                                matchFound = true;
                                break;
                            }
                        }

                        currentNodeToCheck = currentNodeToCheck.getPrevious();
                    }
                }
            }

            private AbstractParticipant surrenderLast() {
                return players.remove(players.size() - 1);
            }

            private AbstractParticipant surrenderPlayer(AbstractParticipant p) {
                return players.remove(players.indexOf(p));
            }
        }
    }
}
