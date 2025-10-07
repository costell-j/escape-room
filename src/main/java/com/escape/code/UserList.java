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

    private UserList() {
       users = DataLoader.getUsers();
    }
    public static UserList getInstance() {
        if(userList == null)
            userList = new UserList();
        return userList;
    }
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
     
    public void addUser(String name, String username, String password, Settings settings, HashMap<UUID, Room> rooms, Room currentRoom) {
        User user = new User(username, password, null, null, null);
        users.add(user);
    } 

    
}
