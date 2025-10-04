package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

public class RoomList {
    private static RoomList roomList;
    private ArrayList<Room> rooms;
    
    private RoomList() {
        rooms = DataLoader.getRooms();
    }
    public static RoomList getInstance() {
        if(roomList == null)
            roomList = new RoomList();
        return roomList;
    }
    public Room getRoom(UUID id) {
       
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }
    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

}
