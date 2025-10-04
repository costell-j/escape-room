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
       return User(username, password);
    }
    public void addUser(String username, String password, 
                        Settings settings, HashMap<UUID, Room> rooms, Room currentRoom) {
        users.add(new User(username, password, settings, rooms, currentRoom));
    } 

    
}
