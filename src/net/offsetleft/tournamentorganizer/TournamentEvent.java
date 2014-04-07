package net.offsetleft.tournamentorganizer;

import java.util.ArrayList;

public final class TournamentEvent<E extends AbstractPlayer> {
    
    private final ArrayList<E> allPlayers = new ArrayList<>();
    private final ArrayList<E> activePlayers = new ArrayList<>();
    private final ArrayList<E> droppedPlayers = new ArrayList<>();
    private final ArrayList<EventRound> rounds = new ArrayList<>();
    
    private final int MIN_PLAYERS, MAX_PLAYERS;
    
    public TournamentEvent(int minPlayers, int maxPlayers) {
        this.MIN_PLAYERS = minPlayers;
        this.MAX_PLAYERS = maxPlayers;
    }
    
    public final void addPlayer(E player) {
        if(!this.allPlayers.contains(player)) {
            this.allPlayers.add(player);
            this.activePlayers.add(player);
        }
    }
    
    public final void dropPlayer(E player) {
        this.activePlayers.remove(player);
        this.droppedPlayers.add(player);
    }
    
    public final void reinstatePlayer(E player) {
        this.droppedPlayers.remove(player);
        this.activePlayers.add(player);
    }
    
    public final void deletePlayer(E player) throws Exception {
        if(rounds.isEmpty()) {
            this.activePlayers.remove(player);
            this.allPlayers.remove(player);
        } else {
            throw new Exception("Cannot remove player after event has started.");
        }
    }
    
    public final ArrayList<E> getAllPlayersList() {
        return this.allPlayers;
    }
    
    public final ArrayList<E> getActivePlayersList() {
        return this.activePlayers;
    }
    
    public final ArrayList<E> getDroppedPlayersList() {
        return this.droppedPlayers;
    }
    
    public final void createRound() {
        EventRound round = PairingFactory.generateMatches(activePlayers, MIN_PLAYERS, MAX_PLAYERS);
        
        this.rounds.add(round);
    }
    
    public final ArrayList<EventRound> getRounds() {
        return this.rounds;
    }
    
}
