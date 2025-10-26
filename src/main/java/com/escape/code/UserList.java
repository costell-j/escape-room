package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
/**
 * This class handles the Usergit List and its methods
 * @author Costell Johnson
 */
public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    // Initializers

    /**
     * The constructor for the UserList, uses DataLoader to load
     * any preexisting UserList
     */
    private UserList() {
        this.users = DataLoader.getUsers();
    }

    /**
     * Checks if UserList is null, if so, it creates one
     * @return the UserList
     */
    public static UserList getInstance() {
        if(userList == null)
            userList = new UserList();
        return userList;
    }

    // Getters

    /**
     * Finds a user based off their username and password
     * @param username the user's username
     * @param password the users' password
     * @return the desired user, or null if they don't exist
     */
    public User getUser(String username, String password) {
        for( int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password))
                return users.get(i);
        }
        return null;
    }

    /**
     * Returns the ArrayList of Users
     * @return arraylist of users
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    // Functionality Methods

    /**
     * Creates a new User and adds them to the UserList, while checking for duplicates
     * @param username user's desired username
     * @param password user's desired password
     * @param settings user's settings
     * @param rooms user's list of rooms
     * @param currentRoom user's current room
     * @return a boolean, true if user is created, false if not
     */
    public boolean addUser(String username, String password, Settings settings, ArrayList<Room> rooms, Room currentRoom) {
        boolean validUsername = username != null && !username.trim().isEmpty() && !username.contains("\n");
        boolean validPassword = password != null && !password.isEmpty() && !password.contains("\n");
        if(getUser(username, password) == null && validUsername && validPassword) {
            if(settings == null)
                settings = new Settings(0, 0);
            User user = new User(username, password, settings, rooms, currentRoom);
            users.add(user);
            return true;
        }
        return false;
    } 

    /**
     * Saves the UserList to the DataWriter
     */
    public void save() {
        DataWriter.saveUsers();
    }

    
}
