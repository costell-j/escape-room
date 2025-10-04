package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String username;
    private String password;
    private Settings settings;
    private Progress progress;
    private ArrayList<UUID> rooms;
    private UUID currentRoom;
    
    public User(String username, String password, Settings settings, Progress progress, ArrayList<UUID> rooms, UUID currentRoom) {
        this.username = username;
        this.password = password;
        this.settings = settings;
        this.progress = progress;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public ArrayList<UUID> getRooms() {
        return this.rooms;
    }

    public UUID getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public String toString() {
        String userdata = "";
        userdata += "Username: "+this.username+"\nPassword: "+this.password;
        userdata += this.settings.toString()+this.progress.toString();
        for(int i=0; i<this.rooms.size(); i++) {
            userdata += "ID: "+this.rooms.get(i)+"\n";
        }
        userdata += "Current ID: "+this.currentRoom+"\n";
        return userdata;
    }
}
