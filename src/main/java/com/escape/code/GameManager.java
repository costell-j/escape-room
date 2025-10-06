package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

/*
 * GameManager handles user sessions, room selection, and game state.
 * It interacts with UserList and RoomList singletons to manage users and rooms.
 */
public class GameManager {
    private User user;            
    private UserList userList;    
    private RoomList roomList;    

    public GameManager() {
      
    }

    public User createAccount(String username, String password) {
        
        return null;
    }

    public ArrayList<User> getUserList() {
        return null;
    }

    public ArrayList<Room> getRoomList() {
       
        return null;
    }

    public boolean logout() {
       
        return false;
    }

    public void exit() {
    }

    public User login(String username, String password) {
       return userList.getUser(username, password);
    }

    public boolean isMatch(String username, String password) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password))
            return true;
        return false;
    }

    public Room chooseRoom(UUID id) {
        return roomList.getRoom(id);
    }

    public void setDifficulty(int difficulty) {
       
    }

    public Leaderboard getLeaderboard() {
        
        return null;
    }

    public Map getMap() {
        
        return null;
    }

    public void saveGame() {
       DataWriter.saveRooms();
    }

    public String getHint() {
        
        return null;
    }
}
