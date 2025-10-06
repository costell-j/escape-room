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
       
        return null;
    }

    public boolean isMatch(String username, String password) {
       
        return false;
    }

    public Room chooseRoom(UUID id) {
        
        return null;
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
       
    }

    public String getHint() {
        
        return null;
    }
}
