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
            file.write("\n");
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

    public static boolean saveRooms() {
        return true;
    }

    public static JSONObject getRoomJSON(Room room) {
        JSONObject roomDetails = new JSONObject();


        return roomDetails;
    }

    public static void main(String[] args){
		DataWriter.saveUsers();
	}
}
