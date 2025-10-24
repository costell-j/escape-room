package com.escape.code;

/**
 * Constants for JSON file reading/writing
 * @author Matthew Kight
 */

public abstract class DataConstants {
    //User Constants
    protected static final String USER_FILE_NAME = "src/main/java/com/escape/json/users.json";
    protected static final String USER_TEMP_FILE_NAME = "src/main/java/com/escape/json/users_temp.json";
    protected static final String USER_USER_NAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_SETTINGS = "settings";
    protected static final String USER_SETTINGS_VOLUME = "volume";
    protected static final String USER_SETTINGS_DIFFICULTY = "difficulty";
    protected static final String USER_PROGRESS = "Progress";
    protected static final String USER_PUZZLES_SOLVED = "puzzlesSolved";
    protected static final String USER_PUZZLE_HASH_KEY = "String";
    protected static final String USER_PUZZLE_HASH_VAL = "Puzzle";
    protected static final String USER_PUZZLE_DESC = "description";
    protected static final String USER_PUZZLE_HINTS = "hints";
    protected static final String USER_CLUES_USED = "cluesUsed";
    protected static final String USER_ACHIEVEMENTS = "achievements";
    protected static final String USER_ACHIEVEMENT_TITLE = "title";
    protected static final String USER_ACHIEVEMENT_UNLOCKED = "unlocked";
    protected static final String USER_PUZZLE_SOLUTION = "solution";
    protected static final String USER_PUZZLE_SOLVED = "isSolved";
    protected static final String PUZZLE_TYPE = "type";
    protected static final String USER_ROOMS = "rooms";
    protected static final String USER_ROOM_HASH_KEY = "UUID";
    protected static final String USER_ROOM_HASH_VAL = "Room";
    protected static final String USER_CURRENT_ROOM = "currentRoom";

    //Room Constants
    protected static final String ROOM_FILE_NAME = "src/main/java/com/escape/json/rooms.json";
    protected static final String ROOM_TEMP_FILE_NAME = "src/main/java/com/escape/json/rooms_temp.json";
    protected static final String ROOM_ID = "id";
    protected static final String ROOM_STORY = "story";
    protected static final String IMAGE_PATH = "imagePath";
    protected static final String ITEM_USED = "used";
    protected static final String PUZZLE_LOCKED = "locked";
    protected static final String ITEMS = "items";
    protected static final String ITEM = "item";
    protected static final String GIVEN_ITEM = "givenItem";
    protected static final String HINTS_USED = "usedHints";
    protected static final String ROOM_NAME = "name";
    protected static final String DECIPHER_SHIFT = "shift";
    protected static final String ROOM_MAP = "map";
    protected static final String ROOM_MAP_ISOPEN = "isOpen";
    protected static final String ROOM_MAP_NAME = "mapName";
    protected static final String ROOM_PROGRESS_LIST = "progressList";
    protected static final String ROOM_MAP_HEIGHT = "height";
    protected static final String ROOM_MAP_LENGTH = "length";
    protected static final String ROOM_TIMER = "timer";
    protected static final String ROOM_PUZZLES = "puzzles";
    protected static final String ROOM_DIFFICULTY = "difficulty";
    protected static final String ROOM_LEADERBOARD = "leaderboard";
    protected static final String ROOM_LEADERBOARD_OPEN = "open";
    protected static final String ROOM_LEADERBOARD_PLAYERS = "players";
    protected static final String ROOM_LEADERBOARD_HASH_KEY = "Integer";
    protected static final String ROOM_LEADERBOARD_HASH_VAL = "User";
    protected static final String ROOM_PROGRESS_COMPLETION_TIME = "completionTime";
    

}

