package com.escape.code;

import java.util.HashMap;

public class Leaderboard {
    
    private HashMap<Integer, String> players;
            
    public Leaderboard(HashMap<Integer, String> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        String desc = "";
        
        return desc;
    }
    
    public HashMap<Integer, String> getPlayers() {
        return this.players;
    }
}
