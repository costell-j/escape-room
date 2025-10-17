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
    /**
     * Constructor for RoomList, using DataLoader
     */
    private RoomList() {
        this.rooms = DataLoader.getRooms();
    }

    /**
     * getInstance method, creates the RoomList if it's null
     * @return RoomList object
     */
    public static RoomList getInstance() {
        if(roomList == null)
            roomList = new RoomList();
        return roomList;
    }

    // Getters

   /**
    * Finds a room based on it's UUID
    * @param id UUID of the desired room
    * @return The desired room or null if not found
    */
    public Room getRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
            return rooms.get(i);
        }
        return null;
    }

    /**
     * Returns all off rooms
     * @return an ArrayList of Rooms
     */
    public ArrayList<Room> getAllRooms() {
        if(this.rooms == null)
            this.rooms = new ArrayList<Room>();
        return this.rooms;
    }

    // Functionality Methods

    /**
     * Deletes a room from the ArrayList
     * @param id the UUID of the desired room
     */
    public void deleteRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
                rooms.remove(i);
        }
    }

    /**
     * Adds a room to the ArrayList
     * @param room the room you want to add
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    
    /**
     * Saves the RoomList to the DataWriter
     */
    public void save() {
        DataWriter.saveRooms();
    }

}
