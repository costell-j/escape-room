package com.escape.code;

import java.lang.reflect.Array;
import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SavingTests {

    /**
     * Steps of test:
     * 0: Loads data
     * 1: Adds A User
     * 2: Saves Users
     * 3: Reloads Users and checks to see if added User exists
     */
    @Test
    public void testSaveUsers() {
        RoomList rommList = RoomList.getInstance();
        UserList userList = UserList.getInstance();
        Settings settings = new Settings(0, 0);
        Room room = new Room();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        userList.addUser("matty", "pass", settings, rooms, room);
        DataWriter.saveUsers();
        ArrayList<User> users = DataLoader.getUsers();
        boolean found = false;

        for(User user : users) {
            if(user.getUsername().equals("matty")) { found = true; }
        }

        assertTrue(found);
    }

    /**
     * Steps of test:
     * 0: Loads data
     * 1: Adds default Room
     * 2: Saves Rooms
     * 3: Reloads Rooms and checks to see if added Room exists
     */
    @Test
    public void testSaveRooms() {
        RoomList rommList = RoomList.getInstance();
        UserList userList = UserList.getInstance();
        DataLoader.loadLeaderboards();

        Room room = new Room();
        DataWriter.saveRooms();
        ArrayList<Room> rooms = DataLoader.getRooms();
        boolean found = false;

        for(Room r : rooms) {
            if(r.getName().equals("none")) { found = true; }
        }

        assertTrue(found);
    }

    /**
     * Steps of test:
     * 0: Loads data
     * 1: Adds default Room
     * 2: Adds Objects/lists containing nulls
     * 3: Saves Rooms
     * 4: Reloads Rooms and checks to see if added Room exists
     */
    @Test
    public void testSaveRoomsWithNulls() {
        RoomList rommList = RoomList.getInstance();
        UserList userList = UserList.getInstance();
        DataLoader.loadLeaderboards();

        Room room = new Room();
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        Puzzle puzzle = new Riddle(null, null, null, null, false, false, null, null);
        puzzles.add(puzzle);

        room.setPuzzles(puzzles);
        rommList.addRoom(room);
        DataWriter.saveRooms();
        ArrayList<Room> rooms = DataLoader.getRooms();
        boolean found = false;

        for(Room r : rooms) {
            if(r.getName().equals("none")) { found = true; }
        }

        assertTrue(found);
    }
}
