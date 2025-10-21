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

    /**
     * Constructor for GameManager, pulls from DataLoader to get the lists and leaderboard
     */
    public GameManager() {
        this.userList = UserList.getInstance();
        this.roomList = RoomList.getInstance();
        DataLoader.loadLeaderboards();
    }

    //User related methods

    /**
     * Creates a blank user for guest
     * @return true if created
     */
    public boolean createGuest() {
        this.user = new User();
        return true;
    }

    /**
     * Creates a new account
     * @param username desired username
     * @param password desired password
     * @return true if account is created
     */
    public boolean createAccount(String username, String password) {
        return userList.addUser(username, password, null, getRoomList(), null);
    }

    /**
     * Logs player into existing account
     * @param username user's username
     * @param password user's password
     * @return true if logged in
     */
    public boolean login(String username, String password) {
        this.user = userList.getUser(username, password);
        return this.user != null;
    }

    /**
     * get the list of users
     * @return userList
     */
    public ArrayList<User> getUserList() {
        return userList.getUsers();
    }

    //Room related methods

    /**
     * Gets the list of rooms
     * @return roomList
     */
    public ArrayList<Room> getRoomList() {
        return roomList.getAllRooms();
    }

    /**
     * Formats the timer
     * @return formatted time
     */
    public String formatTimer() {
        return room.formatTimer();
    }
    /**
     * Chooses a room based off the UUID
     * It sets the room, the puzzles, and current puzzle
     * @param id uuid of desired room
     * @return true if the room exists
     */
    public boolean chooseRoom(UUID id) {
        this.room = roomList.getRoom(id);
        this.puzzles = this.room.getPuzzles();
        this.puzzle = room.getPuzzles().get(room.getProgress().getCurrentPuzzle());
        return this.room != null;
    }

    /**
     * Sets the difficulty of the room, then adjusts the time
     * @param difficulty desired difficulty
     */
    public void setDifficulty(int difficulty) {
        if(this.room != null) {
            this.room.setDifficulty(difficulty);
            room.timeChange(difficulty);
        }
    }

    /**
     * Gets the leaderboard if the room is not null
     * @return leadrboard
     */
    public Leaderboard getLeaderboard() {
        return (this.room != null) ? this.room.getLeaderboard() : null;
    }

    /**
     * Gets the map is the room isn't null
     * @return map
     */
    public Map getMap() {
        return (this.room != null) ? this.room.getMap() : null;
    }

    /**
     * Sets the leaderboard to the open state
     */
    public void openLeaderboard() {
        room.getLeaderboard().setOpen(true);
    }

    /**
     * Sets the leaderboard to the closed state
     */
    public void closeLeaderboard() {
        room.getLeaderboard().setOpen(false);
    }

    /**
     * Gets the current user
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Gets the current room
     * @return room
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Sets the map to the open state
     */
    public void openMap() {
        room.getMap().setOpen(true);
    }

    /**
     * Sets the map to the closed state
     */
    public void closeMap() {
        room.getMap().setOpen(false);
    }


    //Puzzle related methods

    /**
     * Gets the lsit of hints
     * @return hints
     */
    public ArrayList<String> getHints() {
        return this.puzzle.hints;
    }

    /**
     * Gets the list of puzzles
     * @return puzzles
     */
    public ArrayList<Puzzle> getPuzzles() {
        return this.puzzles;
    }
    
    /**
     * Sets the puzzle to the desired puzzle
     * @param p desired puzzle
     */
    public void setPuzzle(Puzzle p) {
        this.puzzle = p;
    }

    /**
     * Gets the current puzzle
     * @return puzzle
     */
    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    //Settings related methosd

    /**
     * Sets the volume while checking for nulls
     * @param volume desired volume
     */
    public void setVolume(int volume) {
        if(this.user != null && this.user.getSettings() != null)
            this.user.getSettings().changeVolume(volume);
    }
    
    /**
     * Saves the game if userList and roomList are not null
     * @return true if saved, false if not
     */
    public boolean saveGame() {
        if(userList != null || roomList != null) { 
            userList.save(); 
            roomList.save(); 
            return true;
        }
    return false;
    }

    /**
     * Logs the user out while saving game
     * @return true if logged out
     */
    public boolean logout() {
        saveGame();
        return true;
    }

    /**
     * Closes the game, meant for guests
     */
    public void exit() {
        System.exit(0);
    }
}
