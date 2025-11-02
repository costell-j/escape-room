package com.escape.code;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

public class SavingTests {
    private UserList userList = UserList.getInstance();
    private RoomList roomList = RoomList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Room> rooms = roomList.getAllRooms();

    @AfterClass
    public static void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    @Test
    public void testSaveRooms() {
        DataLoader.loadLeaderboards();

        Room room = new Room();
        roomList.addRoom(room);
        DataWriter.saveRooms();
        rooms = DataLoader.getRooms();
        boolean found = false;

        for(Room r : rooms) {
            if(r.getName().equals("none")) { found = true; }
        }

        assertTrue(found);
    }

    @Test
    public void testSaveRoomsWithNulls() {
        DataLoader.loadLeaderboards();

        Room room = new Room();
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        Puzzle puzzle = new Riddle(null, null, null, null, false, false, null, null);
        puzzles.add(puzzle);

        room.setPuzzles(puzzles);
        roomList.addRoom(room);
        DataWriter.saveRooms();
        rooms = DataLoader.getRooms();
        boolean found = false;

        for(Room r : rooms) {
            if(r.getName().equals("none")) { found = true; }
        }

        assertTrue(found);
    }

    @Test
    public void testSaveUsers() {
        Settings settings = new Settings(0, 0);
        userList.addUser("matty", "pass", settings, null, null);
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        boolean found = false;

        for(User user : users) {
            if(user.getUsername().equals("matty")) {
            found = true;
            break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testSaveExistingUser() {
        Settings settings = new Settings(0, 0);
        userList.addUser("MAK524", "pass", settings, null, null);
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        int count = 0;

        for(User user : users) {
            if(user.getUsername().equals("MAK524")) { count++; }
        }

        assertEquals(1, count);
    }

    @Test
    public void testSaveEmptyUser() {
        userList.addUser("", "", null, null, null);
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        boolean found = false;

        for(User user : users) {
            if(user.getUsername().equals("")) {
                found = true;
                break;
            }
        }

        assertFalse(found);
    }

    @Test
    public void testLoadUserWithNulls() {
        userList.addUser("kljhgc", "lkjhgdf", null, null, null);
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        boolean loaded = false;

        for(User user : users) {
            if(user.getUsername().equals("kljhgc")) {
                loaded = user.getPassword().equals("lkjhgdf") && user.getSettings() != null && user.getRooms().isEmpty() && user.getCurrentRoom() != null;
            }
        }

        assertTrue(loaded);
    }

    @Test
    public void testWriteRoomWithAllNulls() {
        Room room = new Room(null, null, null, null, null, null, null, null, 0, 0);
        roomList.addRoom(room);
        DataWriter.saveRooms();
        rooms = DataLoader.getRooms();
        boolean found = true;

        for(Room r : rooms) {
            if(r == null) {
                found = false;
                break;
            }
        }

        assertTrue(found);
    }

}
