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
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Reads a JSON file and provides a list of Room Objects
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
                int timer = ((Long)roomJSON.get(ROOM_TIMER)).intValue();
                int difficulty = ((Long)roomJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();

                //Get Room Map JSON Object
                Map map = loadMap(roomJSON);

                //Progress List
                HashMap<String, Progress> progressList = loadProgressList(roomJSON);

                //Progress Object
                Progress progress = loadProgress(roomJSON);

                //Get Room Puzzles JSON Array
                ArrayList<Puzzle> puzzles = loadPuzzles(roomJSON);

                //Get Room Leaderboard Object
                Leaderboard leaderboard = loadLeaderboard(roomJSON);


                Room room = new Room(id, map, progressList, progress, leaderboard, puzzles, timer, difficulty);
                rooms.add(room);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    private static Progress loadProgress(JSONObject parentJSON) {
        //User Progress JSON Object
        JSONObject progressJSON = (JSONObject)parentJSON.get(USER_PROGRESS);
        int cluesUsed = ((Long)progressJSON.get(USER_CLUES_USED)).intValue();

        //Puzzles Solved in Progress Object
        JSONArray puzzles = (JSONArray)progressJSON.get(USER_PUZZLES_SOLVED);
        HashMap<String, Puzzle> puzzleMap = new HashMap<>();
        for(int k=0; k<puzzles.size(); k++) {
            JSONObject puzzleJSON = (JSONObject)puzzles.get(k);
            String ProgressKey = (String)puzzleJSON.get(USER_PUZZLE_HASH_KEY);
            JSONObject ProgressValue = (JSONObject)puzzleJSON.get(USER_PUZZLE_HASH_VAL);
            String description = (String)ProgressValue.get(USER_PUZZLE_DESC);
            ArrayList<String> hints = new ArrayList<>();
            JSONArray hintsJSON = (JSONArray)ProgressValue.get(USER_PUZZLE_HINTS);
            for(int l=0; l<hintsJSON.size(); l++) {
                String hint = (String)hintsJSON.get(l);
                hints.add(hint);
            }
            String solution = (String)ProgressValue.get(USER_PUZZLE_SOLUTION);
            boolean isSolved = (boolean)ProgressValue.get(USER_PUZZLE_SOLVED);
            Puzzle puzzle = new Puzzle(description, hints, solution, isSolved);
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
        Progress progress = new Progress(puzzleMap, cluesUsed, achievements);

        return progress;
    }

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

    private static ArrayList<Room> loadRooms(JSONObject parentJSON) {
        //List of Rooms for a User
        JSONArray userRoomsJSON = (JSONArray)parentJSON.get(USER_ROOMS);
        ArrayList<Room> roomList = new ArrayList<>();
        for(int i=0; i<userRoomsJSON.size(); i++) {
            UUID roomID = UUID.fromString((String)userRoomsJSON.get(i));
            Room room = RoomList.getRoom(roomID);

            roomList.add(room);
        }

        return roomList;
    }

    private static Settings loadSettings(JSONObject parentJSON) {
        //User Settings JSON Object
        JSONObject settingsJSON = (JSONObject)parentJSON.get(USER_SETTINGS);
        int volume = ((Long)settingsJSON.get(USER_SETTINGS_VOLUME)).intValue();
        int userDifficulty = ((Long)settingsJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();
        Settings settings = new Settings(volume, userDifficulty);

        return settings;
    }

    private static Leaderboard loadLeaderboard(JSONObject parentJSON) {
        //Get Room Leaderboard Object
        JSONObject leaderboardJSON = (JSONObject)parentJSON.get(ROOM_LEADERBOARD);
        JSONArray playersJSON = (JSONArray)leaderboardJSON.get(ROOM_LEADERBOARD_PLAYERS);
        HashMap<Integer, String> players = new HashMap<>();
        for(int i=0; i<playersJSON.size(); i++) {
            JSONObject hashSet = (JSONObject)playersJSON.get(i);
            Integer key = ((Long)hashSet.get(ROOM_LEADERBOARD_HASH_KEY)).intValue();
            String value = (String)hashSet.get(USER_PUZZLE_HASH_KEY);
            players.put(key, value);
        }
        Leaderboard leaderboard = new Leaderboard(players);

        return leaderboard;
    }

    private static Map loadMap(JSONObject parentJSON) {
        //Get Room Map JSON Object
        JSONObject mapJSON = (JSONObject)parentJSON.get(ROOM_MAP);
        String mapName = (String)mapJSON.get(ROOM_MAP_NAME);
        int height = ((Long)mapJSON.get(ROOM_MAP_HEIGHT)).intValue();
        int length = ((Long)mapJSON.get(ROOM_MAP_LENGTH)).intValue();
        Map map = new Map(mapName, height, length);

        return map;
    }

    private static ArrayList<Puzzle> loadPuzzles(JSONObject parentJSON) {
        //Get Room Puzzles JSON Array
        JSONArray puzzlesJSON = (JSONArray)parentJSON.get(ROOM_PUZZLES);
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        for(int j=0; j<puzzlesJSON.size(); j++) {
            JSONObject puzzleJSON = (JSONObject)puzzlesJSON.get(j);
            String description = (String)puzzleJSON.get(USER_PUZZLE_DESC);
            ArrayList<String> hints = new ArrayList<>();
            JSONArray hintsJSON = (JSONArray)puzzleJSON.get(USER_PUZZLE_HINTS);
            for(int k=0; k<hintsJSON.size(); k++) {
                String hint = (String)hintsJSON.get(k);
                hints.add(hint);
            }
            String solution = (String)puzzleJSON.get(USER_PUZZLE_SOLUTION);
            boolean isSolved = (boolean)puzzleJSON.get(USER_PUZZLE_SOLVED);
            Puzzle puzzle = new Puzzle(description, hints, solution, isSolved);
            puzzles.add(puzzle);
        }

        return puzzles;
    }

    public static void main(String[] args) {
        ArrayList<Room> rooms = DataLoader.getRooms();

        for(Room room : rooms) {
            System.out.println(room);
        }
    }

}
