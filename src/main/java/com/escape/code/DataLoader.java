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
                JSONObject mapJSON = (JSONObject)roomJSON.get(ROOM_MAP);
                String mapName = (String)mapJSON.get(ROOM_MAP_NAME);
                int height = ((Long)mapJSON.get(ROOM_MAP_HEIGHT)).intValue();
                int length = ((Long)mapJSON.get(ROOM_MAP_LENGTH)).intValue();
                Map map = new Map(mapName, height, length);

                //Get Room Puzzles JSON Array
                JSONArray puzzlesJSON = (JSONArray)roomJSON.get(ROOM_PUZZLES);
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

                //Get Room Leaderboard Object
                JSONObject leaderboardJSON = (JSONObject)roomJSON.get(ROOM_LEADERBOARD);
                JSONArray playersJSON = (JSONArray)leaderboardJSON.get(ROOM_LEADERBOARD_PLAYERS);
                HashMap<Integer, User> players = new HashMap<>();
                for(int j=0; j<playersJSON.size(); j++) {
                    //User JSON Object
                    JSONObject playerJSON = (JSONObject)playersJSON.get(j);
                    Integer key = ((Long)playerJSON.get(ROOM_LEADERBOARD_HASH_KEY)).intValue();
                    JSONObject value = (JSONObject)playerJSON.get(ROOM_LEADERBOARD_HASH_VAL);
                    String username = (String)value.get(USER_USER_NAME);
                    String password = (String)value.get(USER_PASSWORD);

                    //User Settings JSON Object
                    JSONObject settingsJSON = (JSONObject)value.get(USER_SETTINGS);
                    int volume = ((Long)settingsJSON.get(USER_SETTINGS_VOLUME)).intValue();
                    int userDifficulty = ((Long)settingsJSON.get(USER_SETTINGS_DIFFICULTY)).intValue();
                    Settings settings = new Settings(volume, userDifficulty);

                    //User Progress JSON Object
                    JSONObject progressJSON = (JSONObject)value.get(USER_PROGRESS);
                    int cluesUsed = ((Long)progressJSON.get(USER_CLUES_USED)).intValue();

                    //Puzzles Solved in Progress Object
                    JSONArray puzzleProgressJSON = (JSONArray)progressJSON.get(USER_PUZZLES_SOLVED);
                    HashMap<String, Puzzle> puzzleMap = new HashMap<>();
                    for(int k=0; k<puzzles.size(); k++) {
                        JSONObject puzzleJSON = (JSONObject)puzzleProgressJSON.get(k);
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
                    Progress progress = new Progress(id, puzzleMap, cluesUsed, achievements);

                    //List of Rooms for a User
                    JSONArray userRoomsJSON = (JSONArray)value.get(USER_ROOMS);
                    ArrayList<UUID> roomList = new ArrayList<>();
                    for(int k=0; k<userRoomsJSON.size(); k++) {
                        UUID room = UUID.fromString((String)userRoomsJSON.get(k));
                        roomList.add(room);
                    }
                    UUID currentRoom = UUID.fromString((String)value.get(USER_CURRENT_ROOM));
                    User player = new User(username, password, settings, progress, roomList, currentRoom);
                    players.put(key, player);
                }
                Leaderboard leaderboard = Leaderboard.getInstance(players);
                Room room = new Room(id, map, leaderboard, puzzles, timer, difficulty);
                rooms.add(room);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public static void main(String[] args) {
        ArrayList<Room> rooms = DataLoader.getRooms();

        for(Room room : rooms) {
            System.out.println(room);
        }
    }

}
