package com.escape.code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Class for writing to JSON files
 * @author Matthew Kight
 */

public class DataWriter extends DataConstants {
    
    /**
     * Writes all User Objects to a JSON file for storage
     * @return returns true after successful save
     */
    @SuppressWarnings("unchecked")
    public static boolean saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();

        JSONArray jsonUsers = new JSONArray();
        for(int i=0; i<userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
            
            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Takes a User and serializes them into a JSON Object
     * @param user A User Object to be serialized to a JSON Object
     * @return a JSON Object representation of a User
     */
    @SuppressWarnings("unchecked")
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_USER_NAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());

        //Settings JSON Object
        JSONObject userSettings = writeSettings(user);
        userDetails.put(USER_SETTINGS, userSettings);

        //Rooms JSON Array
        JSONArray roomsJSON = writeRoomList(user);
        userDetails.put(USER_ROOMS, roomsJSON);

        String currentRoom = ""+user.getCurrentRoom().getId()+"";
        userDetails.put(USER_CURRENT_ROOM, currentRoom);


        return userDetails;
    }

    /**
     * Writes all Room Objects to a JSON file for storage
     * @return returns true after successful save
     */
    @SuppressWarnings("unchecked")
    public static boolean saveRooms() {
        RoomList rooms = RoomList.getInstance();
        ArrayList<Room> roomList = rooms.getRooms();

        JSONArray jsonUsers = new JSONArray();
        for(int i=0; i<roomList.size(); i++) {
            jsonUsers.add(getRoomJSON(roomList.get(i)));
        }

        try (FileWriter file = new FileWriter(ROOM_TEMP_FILE_NAME)) {
            
            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Takes a Room and serializes it into a JSON Object
     * @param room A Room Object to be serialized to a JSON Object
     */
    @SuppressWarnings("unchecked")
    public static JSONObject getRoomJSON(Room room) {
        JSONObject roomDetails = new JSONObject();
        String id = ""+room.getId()+"";
        roomDetails.put(ROOM_ID, id);
        roomDetails.put(ROOM_TIMER, room.getTimer());
        roomDetails.put(ROOM_DIFFICULTY, room.getDifficulty());
        JSONArray puzzlesJSON = writePuzzleList(room);
        roomDetails.put(ROOM_PUZZLES, puzzlesJSON);
        JSONObject mapJSON = writeMap(room);
        roomDetails.put(ROOM_MAP, mapJSON);
        JSONArray progressListJSON = writeProgressList(room);
        roomDetails.put(ROOM_PROGRESS_LIST, progressListJSON);
        JSONObject progressJSON = writeProgress(room);
        roomDetails.put(USER_PROGRESS, progressJSON);
        JSONObject leaderboardJSON = writeLeaderboard(room);
        roomDetails.put(ROOM_LEADERBOARD, leaderboardJSON);

        return roomDetails;
    }

    @SuppressWarnings("unchecked")
    private static JSONArray writePuzzleList(Room room) {
        //Puzzles JSON Array
        JSONArray puzzlesJSON = new JSONArray();
        ArrayList<Puzzle> puzzles = room.getPuzzles();
        for(int i=0; i<puzzles.size(); i++) {
            //Create Puzzle JSON Object
            JSONObject puzzleJSON = new JSONObject();
            puzzleJSON.put(USER_PUZZLE_DESC, puzzles.get(i).getDescription());
            puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzles.get(i).getSolution());
            puzzleJSON.put(USER_PUZZLE_SOLVED, puzzles.get(i).isSolved());

            //Create JSON Array of hints to store in Puzzle Object
            JSONArray hintsJSON = new JSONArray();
            ArrayList<String> hints = puzzles.get(i).getHints();
            for(int j=0; j<hints.size(); j++) {
                hintsJSON.add(hints.get(j));
            }

            //Continue adding to Puzzle Object
            puzzleJSON.put(USER_PUZZLE_HINTS, hintsJSON);
            puzzlesJSON.add(puzzleJSON);
        }

        return puzzlesJSON;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject writeMap(Room room) {
        JSONObject mapJSON = new JSONObject();
        mapJSON.put(ROOM_MAP_NAME, room.getMap().getMapName());
        mapJSON.put(ROOM_MAP_HEIGHT, room.getMap().getHeight());
        mapJSON.put(ROOM_MAP_LENGTH, room.getMap().getLength());

        return mapJSON;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject writeLeaderboard(Room room) {
        JSONObject leaderboardJSON = new JSONObject();
        JSONArray playersJSON = new JSONArray();
        HashMap<Integer, String> players = room.getLeaderboard().getPlayers();
        for(String string : players.values()) {
            JSONObject leaderHash = new JSONObject();
            Integer key = 0;
            for(Integer val : players.keySet()) {
                if(players.get(val).equals(string)) {
                    key = val;
                }
            }
            leaderHash.put(ROOM_LEADERBOARD_HASH_KEY, key);
            leaderHash.put(ROOM_LEADERBOARD_HASH_VAL, string);
            playersJSON.add(leaderHash);
        }
        leaderboardJSON.put(ROOM_LEADERBOARD_PLAYERS, playersJSON);

        return leaderboardJSON;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject writeProgress(Room room) {
        //Progress JSON Object
        JSONObject roomProgress = new JSONObject();
        JSONArray puzzleJSONArray = new JSONArray();
        HashMap<String, Puzzle> userPuzzles = room.getProgress().getPuzzlesSolved();

        //Iterate through hashmap of puzzles to add to userProgress
        for(Puzzle puzzle : userPuzzles.values()) {
            //Begin adding to puzzleMap
            JSONObject puzzleMapJSON = new JSONObject();
            puzzleMapJSON.put(USER_PUZZLE_HASH_KEY, puzzle.getDescription());

            //Create Puzzle JSON Object for HashMap value
            JSONObject puzzleJSON = new JSONObject();
            puzzleJSON.put(USER_PUZZLE_DESC, puzzle.getDescription());
            puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
            puzzleJSON.put(USER_PUZZLE_SOLVED, puzzle.isSolved());

            //Create JSON Array of hints to store in Puzzle Object
            JSONArray hintsJSON = new JSONArray();
            ArrayList<String> hints = puzzle.getHints();
            for(int j=0; j<hints.size(); j++) {
                hintsJSON.add(hints.get(j));
            }

            //Continue adding to Puzzle Object
            puzzleJSON.put(USER_PUZZLE_HINTS, hintsJSON);
            puzzleMapJSON.put(USER_PUZZLE_HASH_VAL, puzzleJSON);
            puzzleJSONArray.add(puzzleMapJSON);

            //Achievements JSON Array
            JSONArray achievementsJSON = new JSONArray();
            ArrayList<Achievement> achievements = room.getProgress().getAchievements();
            for(int i=0; i<achievements.size(); i++) {
                JSONObject achievementJSON = new JSONObject();
                achievementJSON.put(USER_ACHIEVEMENT_TITLE, room.getProgress().getAchievements().get(i).getTitle());
                achievementJSON.put(USER_ACHIEVEMENT_UNLOCKED, room.getProgress().getAchievements().get(i).isUnlocked());
                achievementsJSON.add(achievementJSON);
            }
            roomProgress.put(USER_ACHIEVEMENTS, achievementsJSON);
        }

        //Continuation of Progress Object
        roomProgress.put(USER_PUZZLES_SOLVED, puzzleJSONArray);
        roomProgress.put(USER_CLUES_USED, room.getProgress().getCluesUsed());

        return roomProgress;
    }

    @SuppressWarnings("unchecked")
    private static JSONArray writeProgressList(Room room) {
        JSONArray progressListJSON = new JSONArray();
        HashMap<String, Progress> progressList = room.getProgressList();
        for(Progress progress : progressList.values()) {
            JSONObject progressJSON = writeProgress(room);
            JSONObject progressHash = new JSONObject();
            String key = "";
            for(String string : progressList.keySet()) {
                if(progressList.get(string) == progress) {
                    key = string;
                }
            }
            progressHash.put(USER_PROGRESS, progressJSON);
            progressHash.put(USER_PUZZLE_HASH_KEY, key);
            progressListJSON.add(progressHash);
        }

        return progressListJSON;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject writeSettings(User user) {
        //Settings JSON Object
        JSONObject userSettings = new JSONObject();
        userSettings.put(USER_SETTINGS_VOLUME, user.getSettings().getVolume());
        userSettings.put(USER_SETTINGS_DIFFICULTY, user.getSettings().getDifficulty());

        return userSettings;
    }

    @SuppressWarnings("unchecked")
    private static JSONArray writeRoomList(User user) {
        //Rooms JSON Array
        JSONArray roomsJSON = new JSONArray();
        ArrayList<Room> rooms = user.getRooms();
        for(int i=0; i<rooms.size(); i++) {
            roomsJSON.add(""+rooms.get(i).getId()+"");
        }

        return roomsJSON;
    }

    public static void main(String[] args){
		DataWriter.saveUsers();
	}
}
