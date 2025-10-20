package com.escape.code;

import java.util.ArrayList;
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
    
    public User() {
        this.username = "guest";
        this.password = "password";
        this.settings = new Settings(0, 1);
        this.rooms = RoomList.getInstance().getAllRooms();
        this.currentRoom = null;
    }
    
    public User(String username, String password, Settings settings, ArrayList<Room> rooms, Room currentRoom) {
        this.username = username;
        this.password = password;
        this.settings = settings;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
    }
    // Getters
    /*
     * gets the users username
     */
    public String getUsername() {
        return this.username;
    }
    /*
     * gets the users password
     */
    public String getPassword() {
        return this.password;
    }
    /*
     * gets the users settings
     */  
    public Settings getSettings() {
        return this.settings;
    }
    /*
     * gets the users rooms
     */
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }
    /*
     * gets the users current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Setters
    /*
     * sets the users room
     * @param rooms is the list of rooms
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    /*
     * sets the users current room
     * @param currentRoom is the users current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    // Other Methods
    /*
    * Prints the User's info
    */
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
