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
    private ArrayList<Puzzle> puzzles;

    public GameManager() {
        this.userList = UserList.getInstance();
        this.roomList = RoomList.getInstance();
        DataLoader.loadLeaderboards();
    }

    //User related methods
    public boolean createGuest() {
        this.user = new User();
        return true;
    }
    public boolean createAccount(String username, String password) {
        return userList.addUser(username, password, null, getRoomList(), null);
    }

    public boolean login(String username, String password) {
        boolean validUsername = (!"".equals(username) && !username.matches("\\h") && !username.matches("[\\\'\"]") && username != null && !userList.checkUsernames(username));
        boolean validPassword = (!"".equals(password) && !password.matches("\\h") && !password.matches("[\\\'\"]") && password != null);

        if(validUsername && validPassword) { this.user = userList.getUser(username, password); }

        return this.user != null;
    }

    public ArrayList<User> getUserList() {
        return userList.getUsers();
    }

    //Room related methods
    public ArrayList<Room> getRoomList() {
        return roomList.getAllRooms();
    }

    public ArrayList<Puzzle> getPuzzles() {
        return this.puzzles;
    }

    public void setPuzzles() {
        ArrayList<Puzzle> puzzles = this.room.getPuzzles();
        this.puzzles = puzzles;
    }

    public void setPuzzle(Puzzle p) {
        this.puzzle = p;
    }

    public void changeDifficulty(int difficulty) {
        this.room.difficultyTweak(difficulty);
    } 

    public String getFormattedTime() {
        return this.room.formatTimer();
    }

    public boolean chooseRoom(UUID id) {
        this.room = roomList.getRoom(id);
        boolean success = this.room.setProgress(this.user.getUsername());
        if(!success) {
            Progress progress = new Progress();
            this.room.setProgress(progress);
        }
        this.puzzle = room.getPuzzles().get(room.getProgress().getCurrentPuzzle());
        return this.room != null;
    }

    public void setDifficulty(int difficulty) {
        if(this.room != null)
            this.room.setDifficulty(difficulty);
    }


    public Leaderboard getLeaderboard() {
        return (this.room != null) ? this.room.getLeaderboard() : null;
    }

    public Map getMap() {
        return (this.room != null) ? this.room.getMap() : null;
    }
    public void openLeaderboard() {
        room.getLeaderboard().setOpen(true);
    }
    public void closeLeaderboard() {
        room.getLeaderboard().setOpen(false);
    }

    public User getUser() {
        return this.user;
    }

    public Room getRoom() {
        return this.room;
    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    public void openMap() {
        room.getMap().setOpen(true);
    }

    public void closeMap() {
        room.getMap().setOpen(false);
    }


    //Puzzle related methods
    public ArrayList<String> getHints() {
        return this.puzzle.hints;
    }

    //Settings related methosd
    public void setVolume(int volume) {
        if(this.user != null && this.user.getSettings() != null)
            this.user.getSettings().changeVolume(volume);
    }
    
    public boolean saveGame() {
        if(userList != null || roomList != null) { 
            userList.save(); 
            roomList.save(); 
            return true;
        }
    return false;
    }

    public boolean logout() {
        saveGame();
        return true;
    }

    public void exit() {
        System.exit(0);
    }
}
