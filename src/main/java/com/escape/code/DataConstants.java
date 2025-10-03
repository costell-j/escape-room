package com.escape.code;

/**
 * Constants for JSON file reading/writing
 * @author Matthew Kight
 */

public abstract class DataConstants {
    protected static final String USER_FILE_NAME = "src/main/java/com/json/users.json";
    //User Constants
    protected static final String USER_USER_NAME = "userName";
    protected static final String USER_PASSWORD = "password";
    //Settings Constants
    protected static final String USER_SETTINGS = "settings";
    protected static final String USER_SETTINGS_VOLUME = "volume";
    protected static final String USER_SETTINGS_DIFFICULTY = "difficulty";
    //Progress Constants
    protected static final String USER_PROGRESS = "progress";
    protected static final String USER_PUZZLES_SOLVED = "puzzlesSolved";
    protected static final String USER_PUZZLE_HASH_KEY = "String";
    protected static final String USER_PUZZLE_HASH_VAL = "Puzzle";
    protected static final String USER_PUZZLE_DESC = "description";
    protected static final String USER_PUZZLE_HINTS = "hints";
    protected static final String USER_PUZZLE_SOLUTION = "solution";
    protected static final String USER_PUZZLE_SOLVED = "isSolved";
    //User-Room Constants
    protected static final String USER_ROOMS = "rooms";
    protected static final String USER_ROOM_HASH_KEY = "UUID";
    protected static final String USER_ROOM_HASH_VAL = "Room";
    protected static final String USER_CURRENT_ROOM = "currentRoom";
}

