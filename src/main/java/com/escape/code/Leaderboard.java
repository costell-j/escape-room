package com.escape.code;

import java.util.HashMap;

public class Leaderboard {
    
    private static HashMap<Integer, User> players;
    private static Leaderboard leaderboard;
    private static int rank;
            
    private Leaderboard(HashMap<Integer, User> players, int rank) {
        Leaderboard.getInstance();
    }
    public static Leaderboard getInstance() {
        if(leaderboard ==  null)
            leaderboard = new Leaderboard(players, rank);
        return leaderboard;
    }
}
