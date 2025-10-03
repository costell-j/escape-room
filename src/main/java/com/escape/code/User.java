package com.escape.code;

import java.util.HashMap;
import java.util.UUID;

public class User {

    private String username;
    private String password;
    private Settings settings;
    private HashMap<UUID, Room> rooms;
    private Room currentRoom;
    
    public User(String username, String password, Settings settings, HashMap<UUID, Room> rooms, Room currentRoom) {
        this.username = username;
        this.password = password;
        this.settings = settings;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
    }
}
