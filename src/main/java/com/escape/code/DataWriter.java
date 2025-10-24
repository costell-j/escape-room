package com.escape.code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

        String currentRoom = "";

        if(user.getCurrentRoom() != null){
            currentRoom = ""+user.getCurrentRoom().getId()+"";
        } 
        
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
        DataLoader.loadLeaderboards();
        ArrayList<Room> roomList = rooms.getAllRooms();

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
        String name = room.getName();
        roomDetails.put(ROOM_ID, id);
        roomDetails.put(ROOM_NAME, name);
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
        JSONArray storyJSON = writeStory(room);
        roomDetails.put(ROOM_STORY, storyJSON);

        return roomDetails;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject writeItem(Puzzle puzzle) {
        JSONObject itemJSON = new JSONObject();
        itemJSON.put(ROOM_NAME, puzzle.getItem().getName());
        itemJSON.put(USER_PUZZLE_DESC, puzzle.getItem().getDescription());
        itemJSON.put(ITEM_USED, puzzle.getItem().isUsed());

        return itemJSON;
    }

    /**
     * Creates a JSONObject from a given Puzzle
     * @param puzzle Puzzle to be serialized
     * @return a JSONObject representation of a Puzzle
     */
    @SuppressWarnings("unchecked")
    private static JSONObject writePuzzle(Puzzle puzzle) {
        //Create Puzzle JSON Object
        JSONObject puzzleJSON = new JSONObject();
        puzzleJSON.put(USER_PUZZLE_DESC, puzzle.getDescription());
        puzzleJSON.put(ROOM_NAME, puzzle.getName());
        puzzleJSON.put(PUZZLE_TYPE, puzzle.getType());
        puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
        puzzleJSON.put(USER_PUZZLE_SOLVED, puzzle.isSolved());
        puzzleJSON.put(ITEM, writeItem(puzzle));
        puzzleJSON.put(GIVEN_ITEM, writeItem(puzzle));
        puzzleJSON.put(PUZZLE_LOCKED, puzzle.isLocked());

        //Type Switch
        switch(puzzle.getType()) {
            case "Riddle" -> {
                puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
            }
            case "Math" -> {
                puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
            }
            case "Logic" -> {
                puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
            }
            case "Decipher" -> {
                puzzleJSON.put(USER_PUZZLE_SOLUTION, puzzle.getSolution());
                puzzleJSON.put(DECIPHER_SHIFT, puzzle.getShift());
            }
            default -> { break; }
        }

        //Create JSON Array of hints to store in Puzzle Object
        JSONArray hintsJSON = new JSONArray();
        ArrayList<String> hints = puzzle.getHints();
        for(int j=0; j<hints.size(); j++) {
            hintsJSON.add(hints.get(j));
        }

        //Continue adding to Puzzle Object
        puzzleJSON.put(USER_PUZZLE_HINTS, hintsJSON);

        return puzzleJSON;
    }

    /**
     * Extracts a HashMap of Puzzle Objects and adds them to a JSONArray
     * @param room a Room Object to read data from for placement into a JSONArray
     * @return a JSONArray containing a HashMap<String, Puzzle> in JSON format
     */
    @SuppressWarnings("unchecked")
    private static JSONArray writePuzzleList(Room room) {
        //Puzzles JSON Array
        JSONArray puzzlesJSON = new JSONArray();
        ArrayList<Puzzle> puzzles = room.getPuzzles();
        for(int i=0; i<puzzles.size(); i++) {
            JSONObject puzzleJSON = writePuzzle(puzzles.get(i));
            puzzlesJSON.add(puzzleJSON);
        }

        return puzzlesJSON;
    }

    /**
     * Extracts info from a Map Object from the passed Room and adds it to a JSONObject
     * @param room a Room Object to read data from for placement into a JSONObject
     * @return a JSONObject containing info from a Map Object
     */
    @SuppressWarnings("unchecked")
    private static JSONObject writeMap(Room room) {
        JSONObject mapJSON = new JSONObject();
        mapJSON.put(ROOM_MAP_NAME, room.getMap().getMapName());
        mapJSON.put(ROOM_MAP_HEIGHT, room.getMap().getHeight());
        mapJSON.put(ROOM_MAP_LENGTH, room.getMap().getLength());
        mapJSON.put(ROOM_MAP_ISOPEN, room.getMap().isOpen());

        return mapJSON;
    }

    /**
     * Extracts info from a Leaderboard Object from the passed Room and adds it to a JSONObject
     * @param room a Room Object to read data from for placement into a JSONObject
     * @return a JSONObject containing the info from a Leaderboard Object
     */
    @SuppressWarnings("unchecked")
    private static JSONObject writeLeaderboard(Room room) {
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
            String currentRoom = ""+user.getCurrentRoom().getId()+"";
            playerJSON.put(USER_CURRENT_ROOM, currentRoom);

            //Settings JSON Object
            JSONObject settingsJSON = writeSettings(user);
            playerJSON.put(USER_SETTINGS, settingsJSON);

            //Rooms JSON Array
            JSONArray roomsJSON = writeRoomList(user);
            playerJSON.put(USER_ROOMS, roomsJSON);

            leaderHash.put(ROOM_LEADERBOARD_HASH_VAL, playerJSON);
            playersJSON.add(leaderHash);
            }
            leaderboardJSON.put(ROOM_LEADERBOARD_PLAYERS, playersJSON);
            leaderboardJSON.put(ROOM_LEADERBOARD_OPEN, room.getLeaderboard().isOpen());

        return leaderboardJSON;
    }

    /**
     * Extracts info from a Progress Object from the passed Room and adds it to a JSONObject
     * @param room a Room Object to read data from for placement into a JSONObject
     * @return a JSONObject containing the info from a Progress Object
     */
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
            JSONObject puzzleJSON = writePuzzle(puzzle);
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

        JSONArray itemsJSON = new JSONArray();
        ArrayList<Item> items = room.getProgress().getItems();
        for(int i=0; i<items.size(); i++) {
            Puzzle puzzle = room.getPuzzles().get(room.getProgress().getCurrentPuzzle());
            itemsJSON.add(writeItem(puzzle));
        }
        roomProgress.put(ITEMS, itemsJSON);

        JSONArray hintsUsedJSON = new JSONArray();
        HashMap<String, String> hintsUsed = room.getProgress().getHintsUsed();
        for(String s : hintsUsed.keySet()) {
            JSONObject hintHashJSON = new JSONObject();
            hintHashJSON.put(HINT, s);
            hintHashJSON.put(ROOM_NAME, hintsUsed.get(s));
            hintsUsedJSON.add(hintHashJSON);
        }
        roomProgress.put(HINTS_USED, hintsUsedJSON);

        //Continuation of Progress Object
        roomProgress.put(USER_PUZZLES_SOLVED, puzzleJSONArray);
        roomProgress.put(USER_CLUES_USED, room.getProgress().getCluesUsed());
        roomProgress.put(ROOM_PROGRESS_COMPLETION_TIME, room.getProgress().getCompletionTime());
        roomProgress.put(USER_CURRENT_ROOM, room.getProgress().getCurrentPuzzle());

        return roomProgress;
    }

    /**
     * Extracts a HashMap of Progress Objects from the passed Room and adds them to a JSONArray
     * @param room a Room Object to read data from for placement into a JSONArray
     * @return a JSONArray containing a HashMap of usernames and Progress Objects
     */
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

    /**
     * Extracts info from a Settings Object contained within the passed User and adds this info to a JSONObject
     * @param user a User to read data from for placement into a JSONObject
     * @return a JSONObject containing the data of a Settings Object
     */
    @SuppressWarnings("unchecked")
    private static JSONObject writeSettings(User user) {
        //Settings JSON Object
        JSONObject userSettings = new JSONObject();
        userSettings.put(USER_SETTINGS_VOLUME, user.getSettings().getVolume());
        userSettings.put(USER_SETTINGS_DIFFICULTY, user.getSettings().getDifficulty());

        return userSettings;
    }

    /**
     * Extracts a list of room ids from a User Object and adds them to a JSONArray
     * @param user a User to read data from for placement into a JSONArray
     * @return a JSONArray containing a list of Room ids
     */
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

    @SuppressWarnings("unchecked")
    private static JSONArray writeStory(Room room) {
        JSONArray storyJSON = new JSONArray();
        ArrayList<Slide> slides = room.getStory();
        for(int i=0; i<slides.size(); i++) {
            JSONObject slideJSON = new JSONObject();
            slideJSON.put(USER_PUZZLE_DESC, slides.get(i).getDescription());
            slideJSON.put(IMAGE_PATH, slides.get(i).getImagePath());
            storyJSON.add(slideJSON);
        }

        return storyJSON;
    }

    public static void main(String[] args){
		DataWriter.saveRooms();
	}
}
