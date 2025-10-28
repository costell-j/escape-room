package com.escape.code;

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
}
