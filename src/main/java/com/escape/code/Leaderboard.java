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

    /**
     * Default constructor for Leaderboard
     */
    public Leaderboard() {
        this.players = new HashMap<>();
    }

    /**
     * Param constuctor for leaderboard
     * @param players Hashmap of players
     * @param open Boolean for leaderboard's state
     */
    public Leaderboard(HashMap<Integer, User> players, boolean open) {
        this.players = players;
        this.open = open;
    }

    // Getters

    /**
     * Gets the Hashmap of users
     * @return players
     */
    public HashMap<Integer, User> getPlayers() {
        return this.players;
    }

    /**
     * Checks if the Leaderboard is open
     * @return true if it is open
     */
    public boolean isOpen() {
        return open = true;
    }

    // Setters
    
    /**
     * Sets the leaderboard to open or closed
     * @param open true for open, false for closed
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    // Other Methods

    /**
     * toString method for Leaderboard
     */
    @Override
    public String toString() {
        String desc = "";
        for(User user : this.players.values()) {
            desc += "Player Info: \n"+user.toString();
        }
        return desc;
    }
}
