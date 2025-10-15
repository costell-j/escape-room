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
    private Puzzle puzzle;  

    public GameManager() {
        this.userList = UserList.getInstance();
        this.roomList = RoomList.getInstance();
    }

    //User related methods
    //Make User and Add to UserList, don't return User
    //Provide empty ArrayList<Room> and default settings object instead of null
    public User createAccount(String username, String password) {
        return new User(username, password, null, null, null);
    }

    //Just set User object, don't return a User
    public User login(String username, String password) {
        this.user = userList.getUser(username, password);
        return this.user;
    }

    public ArrayList<User> getUserList() {
        return userList.getUsers();
    }

    //Room related methods
    public ArrayList<Room> getRoomList() {
        return roomList.getAllRooms();
    }

    public Room chooseRoom(UUID id) { //don't return room, just set GameManager Objects
        this.room = roomList.getRoom(id);
        this.puzzle = room.getPuzzles().get(room.getCurrentPuzzle());
        return this.room;
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
    public boolean closeLeaderboard() {
        return false;
    }
    public boolean closeMap() {
        return false;
    }


    //Puzzle related methods
    public ArrayList<String> getHints() {
        return puzzle.getHints();
    }

    //Settings related methods
    public void setVolume(int volume) {
        user.getSettings().changeVolume(volume);
    }
    
    public void saveGame() {
        DataWriter.saveRooms();
        DataWriter.saveUsers();
    }

    public boolean logout() {
        saveGame();
        return true;
    }

    public void exit() {
        System.exit(0);
    }
}
