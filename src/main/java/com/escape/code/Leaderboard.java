package com.escape.code;

import java.util.HashMap;

public class Leaderboard {
    
    private HashMap<Integer, User> players;
            
    public Leaderboard() {
        this.players = new HashMap<>();
    }
    public Leaderboard(HashMap<Integer, User> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        String desc = "";
        for(User user : this.players.values()) {
            desc += "Player Info: \n"+user.toString();
        }
        return desc;
    }
    
    public HashMap<Integer, User> getPlayers() {
        return this.players;
    }
}
