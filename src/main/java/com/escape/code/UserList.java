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

    private UserList() {
        this.users = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if(userList == null)
            userList = new UserList();
        return userList;
    }

    // Getters

    public User getUser(String username, String password) {
        for( int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password))
                return users.get(i);
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    // Functionality Methods

    public boolean addUser(String username, String password, Settings settings, ArrayList<Room> rooms, Room currentRoom) {
        if(getUser(username, password) == null) {
            if(settings == null)
                settings = new Settings(0, 0);
            User user = new User(username, password, settings, rooms, currentRoom);
            users.add(user);
            return true;
        }
        return false;
    } 

    public void save() {
        DataWriter.saveUsers();
    }

    
}
