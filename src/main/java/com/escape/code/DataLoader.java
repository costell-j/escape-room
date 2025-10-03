package com.escape.code;

import java.util.ArrayList;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import json.simple.JSONArray;
import json.simple.JSONObject;
import json.simple.parser.JSONParser;;


/**
 * Class for loading JSON file data
 * @author Matthew Kight
 */

public class DataLoader extends DataConstants {
    
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        return users;
    }

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        return rooms;
    }

}
