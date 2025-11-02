package com.escape.code;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;


public class RoomListTest {

    private RoomList roomList;

    @Before
    public void setup() {
        roomList = RoomList.getInstance();
        ArrayList<Room> all = roomList.getAllRooms();
        all.clear();
    }

    @Test
    public void testSingletonAlwaysReturnsSameInstance() {
        RoomList first = RoomList.getInstance();
        RoomList second = RoomList.getInstance();
        assertSame(first, second);
    }

    @Test
    public void testAddRoomIncreasesListSize() {
        int before = roomList.getAllRooms().size();
        Room room = new Room();              
        roomList.addRoom(room);
        int after = roomList.getAllRooms().size();
        assertEquals(before + 1, after);
    }

    @Test
    public void testGetRoomReturnsCorrectRoom() {
        Room room = new Room();              
        roomList.addRoom(room);
        UUID id = room.getId();              

        Room fetched = roomList.getRoom(id);
        assertNotNull(fetched);
        assertEquals(room, fetched);
    }
    
    @Test
    public void testGetRoomWithUnknownUUIDReturnsNull() {
        UUID randomId = UUID.randomUUID();
        assertNull(roomList.getRoom(randomId));
    }

    @Test
    public void testDeleteRoomRemovesFromList() {
        Room room = new Room();
        roomList.addRoom(room);
        UUID id = room.getId();

        roomList.deleteRoom(id);
        assertFalse(roomList.getAllRooms().contains(room));
    }

    @Test
    public void testGetAllRoomsNeverNull() {
        ArrayList<Room> list = roomList.getAllRooms();
        assertNotNull(list);
    }

    @Test
    public void testSaveDoesNotThrow() {
        try {
            roomList.save();
        } catch (Exception e) {
            fail("save should not throw: " + e.getMessage());
        }
    }
}
