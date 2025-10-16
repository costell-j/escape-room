package com.escape.code;

import java.util.HashMap;
/**
 * Classes used for the leaderboard and its functions
 * @author Costell Johnson
 */
public class Leaderboard {
    
    private HashMap<Integer, User> players;
    private boolean open;        

    // Constructors

    public Leaderboard() {
        this.players = new HashMap<>();
    }

    public Leaderboard(HashMap<Integer, User> players, boolean open) {
        this.players = players;
        this.open = open;
    }

    // Getters

    public HashMap<Integer, User> getPlayers() {
        return this.players;
    }
    public boolean isOpen() {
        return open;
    }

    // Setters

    public void setOpen(boolean open) {
        this.open = open;
    }

    // Other Methods

    @Override
    public String toString() {
        String desc = "";
        for(User user : this.players.values()) {
            desc += "Player Info: \n"+user.toString();
        }
        return desc;
    }
}
