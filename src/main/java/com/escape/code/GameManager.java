package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static GameManager gm;

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
        return this.userList.addUser(username, password, null, getRoomList(), null);
    }

    /**
     * Logs player into existing account
     * @param username user's username
     * @param password user's password
     * @return true if logged in
     */
    public boolean login(String username, String password) {
        this.user = this.userList.getUser(username, password);
        return this.user != null;
    }

    /**
     * get the list of users
     * @return userList
     */
    public ArrayList<User> getUserList() {
        return this.userList.getUsers();
    }
    
    /**
     * Gets the current user
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    //Room related methods

    /**
     * Gets the list of rooms
     * @return roomList
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList.getAllRooms();
    }

    /**
     * Formats the timer
     * @return formatted time
     */
    public String formatTimer() {
        return this.room.formatTimer();
    }
    /**
     * Chooses a room based off the Room
     * It sets the room, the puzzles, and current puzzle
     * @param r room of desired room
     * @return true if the room exists
     */
    public boolean chooseRoom(Room r) {
        this.room = this.roomList.getRoom(r.getId());
        if (this.room == null) 
            return false;
        this.puzzles = this.room.getPuzzles();
        return true;
    }

    /**
     * Starts the timer
     */
    public void startTimer() {
        this.room.startTimer();
    }

    /**
     * Stops the timer
     */
    public void stopTimer() {
        this.room.stopTimer();
    }

    /**
     * Sets the difficulty of the room, then adjusts the time
     * @param difficulty desired difficulty
     */
    public void setDifficulty(int difficulty) {
        if(this.room != null) {
            this.room.setDifficulty(difficulty);
            this.room.timeChange(difficulty);
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
        this.room.getLeaderboard().setOpen(true);
    }

    /**
     * Sets the leaderboard to the closed state
     */
    public void closeLeaderboard() {
        this.room.getLeaderboard().setOpen(false);
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
        this.room.getMap().setOpen(true);
    }

    /**
     * Sets the map to the closed state
     */
    public void closeMap() {
        this.room.getMap().setOpen(false);
    }

    /**
     * Gets the percentage of puzzles completed
     * @return percent as a double
     */
    public double percentDone() {
        return this.room.percentComplete();
    }

    /**
     * Gets the player's final score for a room
     * @return final score as a double
     */
    public double getFinalScore() {
        return this.room.getFinalScore();
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
        if(p != null) {
            this.puzzle = p;
            this.room.getProgress().setCurrentPuzzle(this.puzzles.indexOf(p));
        }
    }

    /**
     * Gets the current puzzle
     * @return puzzle
     */
    public Puzzle getPuzzle() {
        if(this.puzzle == null)
            throw new IllegalArgumentException("Player must choose puzzle");
        return this.puzzle;
    }

    /**
     * Attempts the puzzle, returning true if it's solved
     * @param <T> allows for different answer types
     * @param index the puzzle index
     * @param answer the desired answer
     * @return true if solved, false if not
     */
    public <T> boolean attemptPuzzle(Puzzle puzzle, T answer) {
        return this.room.attemptPuzzle(puzzle, answer);
    }

    public HashMap<String, Puzzle> viewCompletedPuzzles() {
        return this.room.getProgress().getPuzzlesSolved();
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
            this.userList.save(); 
            this.roomList.save(); 
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
