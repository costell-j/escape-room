package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;
/*
 * Class used to create a new user
 * @Author Erin Check
 */
public class User {

    private String username;
    private String password;
    private Settings settings;
    private ArrayList<Room> rooms;
    private Room currentRoom;
    
    
    public User(String username, String password, Settings settings, ArrayList<Room> rooms, Room currentRoom) {
        this.username = username;
        this.password = password;
        this.settings = settings;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    @Override
    public String toString() {
        String userdata = "";
        userdata += "Username: "+this.username+"\nPassword: "+this.password;
        userdata += this.settings.toString();
        for(int i=0; i<this.rooms.size(); i++) {
            userdata += "ID: "+this.rooms.get(i).getId()+"\n";
        }
        userdata += "Current ID: "+this.currentRoom.getId()+"\n";
        return userdata;
    }
}
