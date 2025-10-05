package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

/** Minimal stub aligned with your UML. Fill in once User/Room/etc. exist. */
public class GameManager {
    private User user;            // TODO: create class
    private UserList userList;    // TODO: create class (singleton)
    private RoomList roomList;    // TODO: create class (singleton)

    public GameManager() {
        // TODO: initialize userList = UserList.getInstance(); roomList = RoomList.getInstance();
    }

    public User createAccount(String username, String password) {
        // TODO: use userList.addUser(...)
        return null;
    }

    public ArrayList<User> getUserList() {
        // TODO: return userList as ArrayList<User>
        return null;
    }

    public ArrayList<Room> getRoomList() {
        // TODO: return roomList.getAllRooms()
        return null;
    }

    public boolean logout() {
        // TODO: clear current user/session
        return false;
    }

    public void exit() {
        // TODO: persist state and close
    }

    public User login(String username, String password) {
        // TODO: return userList.getUser(username, password)
        return null;
    }

    public boolean isMatch(String username, String password) {
        // TODO: validate credentials via userList
        return false;
    }

    public Room chooseRoom(UUID id) {
        // TODO: return roomList.getRoom(id)
        return null;
    }

    public void setDifficulty(int difficulty) {
        // TODO: propagate difficulty to current room/settings
    }

    public Leaderboard getLeaderboard() {
        // TODO: return global or room leaderboard
        return null;
    }

    public Map getMap() {
        // TODO: return current room's map
        return null;
    }

    public void saveGame() {
        // TODO: DataWriter.saveUsers()/saveRooms()
    }

    public String getHint() {
        // TODO: fetch next hint from current puzzle
        return null;
    }
}
