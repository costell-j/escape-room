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
    public void createAccount(String username, String password) {
        userList.addUser(username, password, new Settings(0, 0), getRoomList(), room);
    }

    public void login(String username, String password) {
        this.user = userList.getUser(username, password);
    }

    public ArrayList<User> getUserList() {
        return userList.getUsers();
    }

    //Room related methods
    public ArrayList<Room> getRoomList() {
        return roomList.getAllRooms();
    }

    public void chooseRoom(UUID id) {
        this.room = roomList.getRoom(id);
        this.puzzle = room.getPuzzles().get(room.getCurrentPuzzle());
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

    //Settings related methosd
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
