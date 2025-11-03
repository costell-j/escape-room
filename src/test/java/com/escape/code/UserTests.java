package com.escape.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests methods in User
 * @author Costell Johnson
 */
public class UserTests {
    @Test
    public void testGuestConstructor() {
        User guest = new User();
        assertEquals("guest", guest.getUsername());
        assertEquals("password", guest.getPassword());
        assertNotNull(guest.getSettings());
        assertNull(guest.getCurrentRoom());
    }

    @Test
    public void testUserConstructor() {
        Settings s = new Settings(0, 1);
        Room r = new Room();
        ArrayList<Room> rl = new ArrayList<>();
        rl.add(r);
        User amy = new User("amy", "passw", s, rl, r);
        assertEquals("amy", amy.getUsername());
        assertEquals("passw", amy.getPassword());
        assertEquals(s, amy.getSettings());
        assertEquals(rl, amy.getRooms());
        assertEquals(r, amy.getCurrentRoom());
    }

    @Test
    public void testSetters() {
        User u = new User();
        Room r = new Room();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(r);

        u.setRooms(rooms);
        u.setCurrentRoom(r);

        assertEquals(rooms, u.getRooms());
        assertEquals(r, u.getCurrentRoom());
    }

}
