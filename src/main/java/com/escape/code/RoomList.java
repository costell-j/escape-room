package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;
/**
 * This class handles the RoomList and its methods
 * @author Costell Johnson
 */
public class RoomList {
    private static RoomList roomList;
    private ArrayList<Room> rooms;
    
    // Initializers

    private RoomList() {
        this.rooms = DataLoader.getRooms();
    }

    public static RoomList getInstance() {
        if(roomList == null)
            roomList = new RoomList();
        return roomList;
    }

    // Getters

    public Room getRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
            return rooms.get(i);
        }
        return null;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public ArrayList<Room> getAllRooms() {
        if(this.rooms == null)
            this.rooms = new ArrayList<Room>();
        return this.rooms;
    }

    // Functionality Methods

    public void deleteRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
                rooms.remove(i);
        }
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    
    public void save() {
        DataWriter.saveRooms();
    }

}
