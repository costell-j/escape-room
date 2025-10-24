package com.escape.code;
import java.util.ArrayList;

public class EscapeRoomUI {
    private static GameManager gameManager;
    private static ArrayList<Room> rooms;
    private static ArrayList<Puzzle> hintsUsed;

    private static void init() {
        gameManager = new GameManager();
        rooms = gameManager.getRoomList();
    }

    public void run(){
        init();
        //duplicateAccountandLogin();
        //validUser();
        //newUser();
        //duplicateAccountnewAccount();
        //choosingRoom();
        //playingPuzzles();
        duplicateUser();
        successfullyCreatedAccount();
        chooseAndHearStory();
        completePuzzles();
        logoutAndShowData();
        finishGame();
    }

    public void duplicateAccountandLogin(){
        System.out.println();

        if (gameManager.createAccount("MAK524", "password")){
            System.out.println("Successfully Created account");
        } else {
            System.out.println("Account already created");
        }

        gameManager.login("MAK524","password");
        if (gameManager.login("MAK524", "password")){
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }
    }

    public void validUser(){
        System.out.println();

        gameManager.login("randomname","it%j3o!'kfU");
        if (gameManager.login("randomname", "it%j3o!'kfU")) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }
    }

    public void newUser(){
        System.out.println();

        if (gameManager.createAccount("Guest", "guestpassword")){
            System.out.println("Account created!");
        } else {
            System.out.println("Account couldn't be created");
        }

        if (gameManager.login("Guest", "guestpassword")) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }
    }

    public void duplicateAccountnewAccount(){
        System.out.println();

        if (gameManager.createAccount("MAK524", "password")){
            System.out.println("Successfully Created account");
        } else {
            System.out.println("Account already created");
        }

        if (gameManager.createAccount("newUser", "new")){
            System.out.println("Successfully Created account");
        } else {
            System.out.println("Account already created");
        }
        gameManager.login("newUser", "new");
        if (gameManager.login("newUser", "new")) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }
    }

    public void choosingRoom(){
        System.out.println();
        System.out.println("Escape Rooms: ");

        for (int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            System.out.println(i + ". " + room.getName());
        }
        gameManager.chooseRoom(rooms.get(0));
        System.out.println("Choose your difficulty:\n 1. Lowest difficulty: 20 minute timer\n 2. Med difficulty: 15 minute timer\n 3. Hardest difficulty: 10 minute timer");
        gameManager.setDifficulty(1);
        gameManager.openLeaderboard();
        gameManager.getLeaderboard().toString();
        gameManager.closeLeaderboard();
    }

    public void playingPuzzles(){
        System.out.println();
        System.out.println("Puzzles: ");
        for (int i = 0; i < gameManager.getPuzzles().size(); i++){
            System.out.println(i + ". " + gameManager.getPuzzle().getName());
        }
        gameManager.setPuzzle(gameManager.getPuzzles().get(0));
        System.out.println(gameManager.getPuzzle().getDescription());

        String answer = "50";
        if (answer.equals(gameManager.getPuzzle().getSolution())){
            System.out.println("Correct!");
            gameManager.getPuzzles().remove(gameManager.getPuzzle());
        } else {
            System.out.println("Incorrect Answer");
        }
        if(gameManager.getPuzzles().isEmpty()){
            System.out.println("Congrats you've finished the puzzle");
        }
    }

    public void duplicateUser(){
        //Should be unable to because lrogers is already in for Logan Rogers
        if(gameManager.createAccount("lrogers","password")){
            System.out.println("Successfully created class");
        } else {
            System.out.println("Unable to create account");
        }
    }

    public void successfullyCreatedAccount(){
        if(gameManager.createAccount("leniRogers5","password5")){
            gameManager.login("leniRogers5","password5");
            System.out.println("Successfully created account and logged in");
        } else {
            System.out.println("Unable to create account");
        }
    }

    public void chooseAndHearStory(){
        System.out.println();
        System.out.println("Escape Rooms: ");

        for (int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            System.out.println(i + ". " + room.getName());
        }
        gameManager.chooseRoom(rooms.get(0));
        //Text to speech the story
        
    }

    public void completePuzzles(){
        System.out.println();
        System.out.println("Puzzles: ");
        for (int i = 0; i < gameManager.getPuzzles().size(); i++){
            System.out.println(i + ". " + gameManager.getPuzzle().getName());
        }
        gameManager.setPuzzle(gameManager.getPuzzles().get(0));
        System.out.println(gameManager.getPuzzle().getDescription());
    }

    public void logoutAndShowData(){
        System.out.println();
        gameManager.logout();
        gameManager.login("leniRogers5","password5");
        System.out.println("Current progress\n");
        System.out.println(gameManager.getRoom().getFinalScore());
        
    }

    public void finishGame(){
        //I thought she was already logged in from logout and show data?
        gameManager.openLeaderboard();
        System.out.println(gameManager.getLeaderboard());
        gameManager.closeLeaderboard();
    }
    public static void main(String[] args) {
		EscapeRoomUI escapeInterface = new EscapeRoomUI();
		escapeInterface.run();
	}
}
