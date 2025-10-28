package com.escape.code;

import java.lang.reflect.Array;
import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SavingTests {
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

    @Test
    public void testSaveRooms() {
        RoomList rommList = RoomList.getInstance();
        UserList userList = UserList.getInstance();
        DataLoader.loadLeaderboards();

        Room room = new Room();
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
