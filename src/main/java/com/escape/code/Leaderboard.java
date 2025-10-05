package com.escape.code;

import java.util.HashMap;

public class Leaderboard {
    
    private static HashMap<Integer, User> players;
    private static Leaderboard leaderboard;
            
    private Leaderboard(HashMap<Integer, User> players) {
        this.players = players;
    }
    public static Leaderboard getInstance(HashMap<Integer, User> players) {
        if(leaderboard ==  null)
            leaderboard = new Leaderboard(players);
        return leaderboard;
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
