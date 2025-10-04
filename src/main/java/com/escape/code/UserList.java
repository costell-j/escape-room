package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
       User user = User(username, passwordusername, password, null, null, null, null);
        return user;
    }
    public void addUser(String name, String username, String password, Settings settings, HashMap<UUID, Room> rooms, Room currentRoom) {
        User user = new User(username, password, null, null, null, null);
        users.add(user);
    } 

    
}
