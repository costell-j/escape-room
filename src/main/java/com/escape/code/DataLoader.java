package com.escape.code;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;


/**
 * Class for loading JSON file data
 * @author Matthew Kight
 */

public class DataLoader extends DataConstants {
    
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i<peopleJSON.size(); i++) {
                //Get User JSON object
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                String userName = (String)personJSON.get(USER_USER_NAME);
                String password = (String)personJSON.get(USER_PASSWORD);

                //User Settings JSON Object
                JSONObject settingsJSON = (JSONObject)personJSON.get(USER_SETTINGS);
                int volume =  ((Long)settingsJSON.get(USER_SETTINGS_VOLUME)).intValue();
                int difficulty =  ((Long)settingsJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();
                Settings settings = new Settings(volume, difficulty);

                //Progress JSON Object
                JSONObject progressJSON = (JSONObject)personJSON.get(USER_PROGRESS);
                int cluesUsed = ((Long)progressJSON.get(USER_CLUES_USED)).intValue();

                //Puzzles Solved in Progress Object
                JSONArray puzzles = (JSONArray)progressJSON.get(USER_PUZZLES_SOLVED);
                HashMap<String, Puzzle> puzzleMap = new HashMap<>();
                for(int j=0; j<puzzles.size(); j++) {
                    JSONObject puzzleJSON = (JSONObject)puzzles.get(j);
                    String key = (String)puzzleJSON.get(USER_PUZZLE_HASH_KEY);
                    JSONObject value = (JSONObject)puzzleJSON.get(USER_PUZZLE_HASH_VAL);
                    String description = (String)value.get(USER_PUZZLE_DESC);
                    ArrayList<String> hints = new ArrayList<>();
                    JSONArray hintsJSON = (JSONArray)value.get(USER_PUZZLE_HINTS);
                    for(int k=0; k<hintsJSON.size(); k++) {
                        String hint = (String)hintsJSON.get(k);
                        hints.add(hint);
                    }
                    String solution = (String)value.get(USER_PUZZLE_SOLUTION);
                    boolean isSolved = (boolean)value.get(USER_PUZZLE_SOLVED);
                    Puzzle puzzle = new Puzzle(description, hints, solution, isSolved);
                    puzzleMap.put(key, puzzle);
                }

                //Progress Achievements JSON Array
                JSONArray achievementsJSON = (JSONArray)progressJSON.get(USER_ACHIEVEMENTS);
                ArrayList<Achievement> achievements = new ArrayList<>();
                for(int j=0; j<achievementsJSON.size(); j++) {
                    JSONObject achievementJSON = (JSONObject)achievementsJSON.get(j);
                    String title = (String)achievementJSON.get(USER_ACHIEVEMENT_TITLE);
                    boolean unlocked = (boolean)achievementJSON.get(USER_ACHIEVEMENT_UNLOCKED);
                    Achievement achievement = new Achievement(title, unlocked);
                    achievements.add(achievement);

                }

                //Rooms JSON Array
                JSONArray roomsJSON = (JSONArray)personJSON.get(USER_ROOMS);
                ArrayList<UUID> rooms = new ArrayList<>();
                for(int j=0; j<roomsJSON.size(); j++) {
                    UUID room = UUID.fromString((String)roomsJSON.get(j));
                    rooms.add(room);
                }

                //Current Room
                UUID currentRoom = UUID.fromString((String)personJSON.get(USER_CURRENT_ROOM));

                //Progress Object construction
                Progress progress = new Progress(currentRoom, puzzleMap, cluesUsed, achievements);

                //Create User Object & add to list
                User user = new User(userName, password, settings, progress, rooms, currentRoom);
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        return rooms;
    }

    public static void main(String[] args) {
        ArrayList<User> users = DataLoader.getUsers();

        for(User user : users) {
            System.out.println(user);
        }
    }

}
