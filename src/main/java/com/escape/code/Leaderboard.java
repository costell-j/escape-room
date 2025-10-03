package com.escape.code;

import java.util.HashMap;

public class Leaderboard {
    
    private HashMap<Integer, User> players;
    private static Leaderboard leaderboard;

    private Leaderboard(HashMap<Integer, User> players) {
        this.players = players;
    }
}
