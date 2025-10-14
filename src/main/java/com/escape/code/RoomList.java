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
    
    private RoomList() {
        this.rooms = DataLoader.getRooms();
    }
    public static RoomList getInstance() {
        if(roomList == null)
            roomList = new RoomList();
        return roomList;
    }
    public Room getRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
            return rooms.get(i);
         }
         return null;
    }
<<<<<<< HEAD

    public void removeRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id)) {
                rooms.remove(i);
                break;
            }
=======
    public void deleteRoom(UUID id) {
        for( int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId().equals(id))
             rooms.remove(i);
>>>>>>> 57d412061454efa250af8f7acb5d81950322bc57
         }
    }
 
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    public ArrayList<Room> getAllRooms() {
        return this.rooms;
    }

}
