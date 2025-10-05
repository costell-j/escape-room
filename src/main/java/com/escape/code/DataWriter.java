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

    @SuppressWarnings("unchecked")
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_USER_NAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());

        //Settings JSON Object
        JSONObject userSettings = new JSONObject();
        userSettings.put(USER_SETTINGS_VOLUME, user.getSettings().getVolume());
        userSettings.put(USER_SETTINGS_DIFFICULTY, user.getSettings().getDifficulty());
        userDetails.put(USER_SETTINGS, userSettings);

        //Progress JSON Object
        JSONObject userProgress = new JSONObject();
        JSONArray puzzleJSONArray = new JSONArray();
        HashMap<String, Puzzle> puzzles = user.getProgress().getPuzzlesSolved();

        //Iterate through hashmap of puzzles to add to userProgress
        for(Puzzle puzzle : puzzles.values()) {
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
        }

        //Continuation of Progress Object
        userProgress.put(USER_PUZZLES_SOLVED, puzzleJSONArray);
        userProgress.put(USER_CLUES_USED, user.getProgress().getCluesUsed());

        //Achievements JSON Array
        JSONArray achievementsJSON = new JSONArray();
        ArrayList<Achievement> achievements = user.getProgress().getAchievements();
        for(int i=0; i<achievements.size(); i++) {
            JSONObject achievementJSON = new JSONObject();
            achievementJSON.put(USER_ACHIEVEMENT_TITLE, user.getProgress().getAchievements().get(i).getTitle());
            achievementJSON.put(USER_ACHIEVEMENT_UNLOCKED, user.getProgress().getAchievements().get(i).isUnlocked());
            achievementsJSON.add(achievementJSON);
        }
        userProgress.put(USER_ACHIEVEMENTS, achievementsJSON);
        userDetails.put(USER_PROGRESS, userProgress);

        //Rooms JSON Array
        JSONArray roomsJSON = new JSONArray();
        ArrayList<UUID> rooms = user.getRooms();
        for(int i=0; i<rooms.size(); i++) {
            roomsJSON.add(""+rooms.get(i)+"");
        }
        userDetails.put(USER_ROOMS, roomsJSON);
        String currentRoom = ""+user.getCurrentRoom()+"";
        userDetails.put(USER_CURRENT_ROOM, currentRoom);


        return userDetails;
    }

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

    @SuppressWarnings("unchecked")
    public static JSONObject getRoomJSON(Room room) {
        JSONObject roomDetails = new JSONObject();
        String id = ""+room.getId()+"";
        roomDetails.put(ROOM_ID, id);
        roomDetails.put(ROOM_TIMER, room.getTimer());
        roomDetails.put(ROOM_DIFFICULTY, room.getDifficulty());
        
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
        roomDetails.put(ROOM_PUZZLES, puzzlesJSON);

        //Map JSON Object
        JSONObject mapJSON = new JSONObject();
        mapJSON.put(ROOM_MAP_NAME, room.getMap().getMapName());
        mapJSON.put(ROOM_MAP_HEIGHT, room.getMap().getHeight());
        mapJSON.put(ROOM_MAP_LENGTH, room.getMap().getLength());
        roomDetails.put(ROOM_MAP, mapJSON);

        //Leaderboard JSON Object
        JSONObject leaderboardJSON = new JSONObject();
        JSONArray playersJSON = new JSONArray();
        HashMap<Integer, User> players = room.getLeaderboard().getPlayers();
        for(User user : players.values()) {
            JSONObject leaderHash = new JSONObject();
            JSONObject playerJSON = new JSONObject();
            Integer key = 0;
            for(Integer val : players.keySet()) {
                if(players.get(val) == user) {
                    key = val;
                }
            }
            leaderHash.put(ROOM_LEADERBOARD_HASH_KEY, key);
            playerJSON.put(USER_USER_NAME, user.getUsername());
            playerJSON.put(USER_PASSWORD, user.getPassword());
            String currentRoom = ""+user.getCurrentRoom()+"";
            playerJSON.put(USER_CURRENT_ROOM, currentRoom);

            //Settings JSON Object
            JSONObject settingsJSON = new JSONObject();
            settingsJSON.put(USER_SETTINGS_VOLUME, user.getSettings().getVolume());
            settingsJSON.put(USER_SETTINGS_DIFFICULTY, user.getSettings().getVolume());
            playerJSON.put(USER_SETTINGS, settingsJSON);

            //Rooms JSON Array
            JSONArray roomsJSON = new JSONArray();
            ArrayList<UUID> rooms = user.getRooms();
            for(int i=0; i<rooms.size(); i++) {
                String roomID = ""+rooms.get(i)+"";
                roomsJSON.add(roomID);
            }
            playerJSON.put(USER_ROOMS, roomsJSON);

            //Progress JSON Object
            JSONObject userProgress = new JSONObject();
            JSONArray puzzleJSONArray = new JSONArray();
            HashMap<String, Puzzle> userPuzzles = user.getProgress().getPuzzlesSolved();

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
            }

            //Continuation of Progress Object
            userProgress.put(USER_PUZZLES_SOLVED, puzzleJSONArray);
            userProgress.put(USER_CLUES_USED, user.getProgress().getCluesUsed());

            //Achievements JSON Array
            JSONArray achievementsJSON = new JSONArray();
            ArrayList<Achievement> achievements = user.getProgress().getAchievements();
            for(int i=0; i<achievements.size(); i++) {
                JSONObject achievementJSON = new JSONObject();
                achievementJSON.put(USER_ACHIEVEMENT_TITLE, user.getProgress().getAchievements().get(i).getTitle());
                achievementJSON.put(USER_ACHIEVEMENT_UNLOCKED, user.getProgress().getAchievements().get(i).isUnlocked());
                achievementsJSON.add(achievementJSON);
            }
            userProgress.put(USER_ACHIEVEMENTS, achievementsJSON);
            playerJSON.put(USER_PROGRESS, userProgress);
            leaderHash.put(ROOM_LEADERBOARD_HASH_VAL, playerJSON);
            playersJSON.add(leaderHash);
            }
            leaderboardJSON.put(ROOM_LEADERBOARD_PLAYERS, playersJSON);
            roomDetails.put(ROOM_LEADERBOARD, leaderboardJSON);

        return roomDetails;
    }

    public static void main(String[] args){
		DataWriter.saveRooms();
	}
}
