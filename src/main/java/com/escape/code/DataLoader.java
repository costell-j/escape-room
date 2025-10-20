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
    
    /**
     * Reads a JSON file and provides a list of User Objects
     * @return An ArrayList of Users read from a JSON file
     */
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
                Settings settings = loadSettings(personJSON);
                RoomList roomSet = RoomList.getInstance();
                ArrayList<Room> rooms = loadRooms(personJSON);
                UUID currentRoomID = UUID.fromString((String)personJSON.get(USER_CURRENT_ROOM));
                Room currentRoom = roomSet.getRoom(currentRoomID);

                //Create User Object & add to list
                User user = new User(userName, password, settings, rooms, currentRoom);
                users.add(user);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Loads in an ArrayList of Rooms from a json file
     * all Room Objects are lazy loaded with a default Leaderboard to be filled later
     * @return An ArrayList of Rooms read from a JSON file
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        try {
            FileReader reader = new FileReader(ROOM_FILE_NAME);
            JSONArray roomsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i<roomsJSON.size(); i++) {
                //Get Room JSON Object
                JSONObject roomJSON = (JSONObject)roomsJSON.get(i);
                UUID id = UUID.fromString((String)roomJSON.get(ROOM_ID));
                String name = (String)roomJSON.get(ROOM_NAME);
                int timer = ((Long)roomJSON.get(ROOM_TIMER)).intValue();
                int difficulty = ((Long)roomJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();
                Map map = loadMap(roomJSON);
                HashMap<String, Progress> progressList = loadProgressList(roomJSON);
                Progress progress = loadProgress(roomJSON);
                ArrayList<Puzzle> puzzles = loadPuzzles(roomJSON);
                //Leaderboard leaderboard = loadLeaderboard(roomJSON);
                Leaderboard leaderboard = new Leaderboard();

                Room room = new Room(id, name, map, progressList, progress, leaderboard, puzzles, timer, difficulty);
                rooms.add(room);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    /**
     * Extracts a JSONObject and constructs a Progress Object from the contained data
     * @param parentJSON JSONObject to extract a progress JSONObject from
     * @return a Progress Object
     */
    private static Progress loadProgress(JSONObject parentJSON) {
        JSONObject progressJSON = (JSONObject)parentJSON.get(USER_PROGRESS);
        int cluesUsed = ((Long)progressJSON.get(USER_CLUES_USED)).intValue();
        int completionTime = ((Long)progressJSON.get(ROOM_PROGRESS_COMPLETION_TIME)).intValue();
        int currentRoom = ((Long)progressJSON.get(USER_CURRENT_ROOM)).intValue();

        //Puzzles Solved in Progress Object
        JSONArray puzzles = (JSONArray)progressJSON.get(USER_PUZZLES_SOLVED);
        HashMap<String, Puzzle> puzzleMap = new HashMap<>();
        for(int k=0; k<puzzles.size(); k++) {
            JSONObject puzzleJSON = (JSONObject)puzzles.get(k);
            String ProgressKey = (String)puzzleJSON.get(USER_PUZZLE_HASH_KEY);
            JSONObject ProgressValue = (JSONObject)puzzleJSON.get(USER_PUZZLE_HASH_VAL);
            Puzzle puzzle = loadPuzzle(ProgressValue);
            puzzleMap.put(ProgressKey, puzzle);
        }

        //Progress Achievements JSON Array
        JSONArray achievementsJSON = (JSONArray)progressJSON.get(USER_ACHIEVEMENTS);
        ArrayList<Achievement> achievements = new ArrayList<>();
        for(int k=0; k<achievementsJSON.size(); k++) {
            JSONObject achievementJSON = (JSONObject)achievementsJSON.get(k);
            String title = (String)achievementJSON.get(USER_ACHIEVEMENT_TITLE);
            boolean unlocked = (boolean)achievementJSON.get(USER_ACHIEVEMENT_UNLOCKED);
            Achievement achievement = new Achievement(title, unlocked);
            achievements.add(achievement);

        }
        Progress progress = new Progress(puzzleMap, cluesUsed, completionTime, currentRoom, achievements);

        return progress;
    }

    /**
     * Extracts a JSONArray and constructs a HashMap from the contained data, providing a list of progress
     * @param parentJSON JSONObject to extract a progressList JSONArray from
     * @return a HashMap<String, Progress>
     */
    private static HashMap<String, Progress> loadProgressList(JSONObject parentJSON) {
        JSONArray progressListJSON = (JSONArray)parentJSON.get(ROOM_PROGRESS_LIST);
        HashMap<String, Progress> progressList = new HashMap<>();
        for(int i=0; i<progressListJSON.size(); i++) {
            JSONObject progressJSON = (JSONObject)progressListJSON.get(i);
            Progress progress = loadProgress(progressJSON);
            String username = (String)progressJSON.get(USER_PUZZLE_HASH_KEY);
            progressList.put(username, progress);
        }

        return progressList;
    }

    /**
     * Extracts a JSONArray and constructs an ArrayList from the contained data, providing a list of rooms
     * @param parentJSON JSONObject to extract a JSONArray from
     * @return an ArrayList<Room>
     */
    private static ArrayList<Room> loadRooms(JSONObject parentJSON) {
        //List of Rooms for a User
        RoomList roomlist = RoomList.getInstance();
        JSONArray userRoomsJSON = (JSONArray)parentJSON.get(USER_ROOMS);
        ArrayList<Room> roomList = new ArrayList<>();
        for(int i=0; i<userRoomsJSON.size(); i++) {
            UUID roomID = UUID.fromString((String)userRoomsJSON.get(i));
            Room room = roomlist.getRoom(roomID);

            roomList.add(room);
        }

        return roomList;
    }

    /**
     * Extracts a JSONObject and constructs a Settings Object from the contained data
     * @param parentJSON a JSONObject to extract a Settings JSONObject from
     * @return a Settings Object
     */
    private static Settings loadSettings(JSONObject parentJSON) {
        //User Settings JSON Object
        JSONObject settingsJSON = (JSONObject)parentJSON.get(USER_SETTINGS);
        int volume = ((Long)settingsJSON.get(USER_SETTINGS_VOLUME)).intValue();
        int userDifficulty = ((Long)settingsJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();
        Settings settings = new Settings(volume, userDifficulty);

        return settings;
    }

    /**
     * Iterates over rooms.json objects to add filled out Leaderboards
     */
    public static void loadLeaderboards() {

        try {
            FileReader reader = new FileReader(ROOM_FILE_NAME);
            JSONArray roomsJSON = (JSONArray)new JSONParser().parse(reader);
            RoomList rooms = RoomList.getInstance();

            for(int i=0; i<roomsJSON.size(); i++) {
                JSONObject roomJSON = (JSONObject)roomsJSON.get(i);
                UUID id = UUID.fromString((String)roomJSON.get(ROOM_ID));
                //Get Room Leaderboard Object
                JSONObject leaderboardJSON = (JSONObject)roomJSON.get(ROOM_LEADERBOARD);
                JSONArray leaderHash = (JSONArray)leaderboardJSON.get(ROOM_LEADERBOARD_PLAYERS);
                HashMap<Integer, User> players = new HashMap<>();
                for(int j=0; j<leaderHash.size(); j++) {
                    //User JSON Object
                    JSONObject playerJSON = (JSONObject)leaderHash.get(j);
                    Integer key = ((Long)playerJSON.get(ROOM_LEADERBOARD_HASH_KEY)).intValue();
                    JSONObject value = (JSONObject)playerJSON.get(ROOM_LEADERBOARD_HASH_VAL);
                    String username = (String)value.get(USER_USER_NAME);
                    String password = (String)value.get(USER_PASSWORD);

                    //User Settings JSON Object
                    Settings settings = loadSettings(value);

                    //List of Rooms for a User
                    ArrayList<Room> roomList = loadRooms(value);

                    UUID currentRoomID = UUID.fromString((String)roomJSON.get(ROOM_ID));
                    Room currentRoom = rooms.getRoom(currentRoomID);

                    User user = new User(username, password, settings, roomList, currentRoom);
                    players.put(key, user);
                }
                boolean open = (boolean)leaderboardJSON.get(ROOM_LEADERBOARD_OPEN);
                Leaderboard leaderboard = new Leaderboard(players, open);
                Room room = rooms.getRoom(id);
                room.setLeaderboard(leaderboard);
                rooms.deleteRoom(id);
                rooms.addRoom(room);
                ArrayList<Room> newRooms = rooms.getAllRooms();

                for(User user : players.values()) {
                    user.setCurrentRoom(room);
                    user.setRooms(newRooms);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts a JSONObject and constructs a Map Object from the contained data
     * @param parentJSON a JSONObject to extract a Map JSONObject from
     * @return a Map Object
     */
    private static Map loadMap(JSONObject parentJSON) {
        //Get Room Map JSON Object
        JSONObject mapJSON = (JSONObject)parentJSON.get(ROOM_MAP);
        String mapName = (String)mapJSON.get(ROOM_MAP_NAME);
        int height = ((Long)mapJSON.get(ROOM_MAP_HEIGHT)).intValue();
        int length = ((Long)mapJSON.get(ROOM_MAP_LENGTH)).intValue();
        boolean isOpen = (boolean)mapJSON.get(ROOM_MAP_ISOPEN);
        Map map = new Map(mapName, height, length, isOpen);

        return map;
    }

    
    private static Puzzle loadPuzzle(JSONObject parentJSON) {
        String description = (String)parentJSON.get(USER_PUZZLE_DESC);
        String name = (String)parentJSON.get(ROOM_NAME);
        boolean isSolved = (boolean)parentJSON.get(USER_PUZZLE_SOLVED);
        ArrayList<String> hints = new ArrayList<>();
        JSONArray hintsJSON = (JSONArray)parentJSON.get(USER_PUZZLE_HINTS);
        for(int k=0; k<hintsJSON.size(); k++) {
            String hint = (String)hintsJSON.get(k);
            hints.add(hint);
        }
        
        String type = (String)parentJSON.get(PUZZLE_TYPE);
        Puzzle puzzle = new Riddle(description, name, "", hintsJSON, isSolved);
        switch(type) {
            case "Riddle" -> {
                String solution = (String)parentJSON.get(USER_PUZZLE_SOLUTION);
                puzzle = new Riddle(description, name, solution, hints, isSolved);
            }
            case "Math" -> {
                double solution = ((Double)parentJSON.get(USER_PUZZLE_SOLUTION));
                puzzle = new MathPuzzle(description, name, solution, hints, isSolved);
            }
            case "Logic" -> {
                String solution = (String)parentJSON.get(USER_PUZZLE_SOLUTION);
                puzzle = new Logic(description, name, solution, hints, isSolved);
            }
            case "Decipher" -> {
                String solution = (String)parentJSON.get(USER_PUZZLE_SOLUTION);
                int shift = ((Long)parentJSON.get(DECIPHER_SHIFT)).intValue();
                puzzle = new Decipher(description, name, solution, hints, isSolved, shift);
            }
                default -> {break;}
            }

            return puzzle;
    }
    
    /**
     * Extracts a JSONArray and constructs an ArrayList from the contained data, providing a list of puzzles
     * @param parentJSON a JSONObject to extract a JSONArray from
     * @return an ArrayList<Puzzle>
     */
    private static ArrayList<Puzzle> loadPuzzles(JSONObject parentJSON) {
        //Get Room Puzzles JSON Array
        JSONArray puzzlesJSON = (JSONArray)parentJSON.get(ROOM_PUZZLES);
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        for(int j=0; j<puzzlesJSON.size(); j++) {
            JSONObject puzzleJSON = (JSONObject)puzzlesJSON.get(j);
            Puzzle puzzle = loadPuzzle(puzzleJSON);
            puzzles.add(puzzle);
        }

        return puzzles;
    }

    public static void main(String[] args) {
        RoomList roomList = RoomList.getInstance();
        loadLeaderboards();
        ArrayList<Room> rooms = roomList.getAllRooms();

        for(Room room : rooms) {
            System.out.println(room);
        }
    }

}
