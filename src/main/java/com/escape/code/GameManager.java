package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

/**
 * GameManager handles user sessions, room selection, and game state.
 * It interacts with UserList and RoomList singletons to manage users and rooms.
 * @author Costell Johnson
 */
public class GameManager {
    private User user;    
    private Room room;        
    private UserList userList;    
    private RoomList roomList;    

    public GameManager() {
      
    }

    public User createAccount(String username, String password) {
        return new User(username, password, null, null, null);
    }

    public ArrayList<User> getUserList() {
        return userList.getUsers();
    }

    public ArrayList<Room> getRoomList() {
        return roomList.getAllRooms();
    }

    public boolean logout() {
        saveGame();
        return true;
    }

    public void exit() {
        saveGame();
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
        room.setDifficulty(difficulty);
    }

    public Leaderboard getLeaderboard() {
        return room.getLeaderboard();
    }

    public Map getMap() {
        return room.getMap();
    }

    public void saveGame() {
       DataWriter.saveRooms();
       DataWriter.saveUsers();
    }

    public String getHint() {
        return null;
    }
}
