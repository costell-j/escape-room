package com.escape.code;
import com.escape.speech.Speak;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class EscapeRoomUI {
    private static GameManager gameManager;
    private static ArrayList<Room> rooms;

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
        if(gameManager.createAccount("leniRogers8","password8")){
            gameManager.login("leniRogers8","password8");
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
        Room room = gameManager.getRoom();
        ArrayList<Slide> story = room.getStory();
        for (Slide slide : story) {
            System.out.println("\n" + slide.getDescription());
            Speak.speak(slide.getDescription());
        }
        
    }

    public void completePuzzles(){
        System.out.println();
        ArrayList<Puzzle> puzzles = gameManager.getPuzzles();
        ArrayList<String> currentHints = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        System.out.println("Puzzles: ");
        for (int i = 0; i < puzzles.size(); i++){
            Puzzle temp = puzzles.get(i);
            System.out.println(i + ". " + temp.getName());
        }
        Puzzle p1 = puzzles.get(0);
        gameManager.setPuzzle(p1);
        System.out.println(p1.getDescription());
        String answer = "C";
        if (answer.equals(p1.getSolution())){
            System.out.println("Correct!\n");
            gameManager.getPuzzles().remove(0);
            items.add(new Item("Silver star", "Unused", false));
            System.out.println("You earned a silver star");
        } else {
            System.out.println("Incorrect Answer");
        }

        Puzzle p2 = gameManager.getPuzzles().get(1);
        boolean correct = false;
        gameManager.setPuzzle(p2);
        System.out.println(p2.getDescription());
        String answer1 = "C";
        if (answer1.equals(p2.getSolution())){
            System.out.println("Correct!\n");
            correct = true;
            gameManager.getPuzzles().remove(gameManager.getPuzzle());
        } else {
            System.out.println("Incorrect Answer");
            currentHints = p2.getHints();
             System.out.println(currentHints+"\n");
            String answer3 = "A keyboard";
            if (answer3.equals(p2.getSolution())){
            System.out.println("Correct!\n");
            correct = true;
            gameManager.getPuzzles().remove(1);
            }
            if (correct) {
                items.add(new Item("Gold Key", "Unlocks the last puzzle", false));
            }
        }

        boolean goldKey = false;
        for (Item item : items) {
            if (item.getName().equals("Gold key")){
                item.setUsed(true);
                goldKey = true;
            }
        }
        if (goldKey){
            Puzzle p3 = gameManager.getPuzzles().get(1);
            gameManager.setPuzzle(p3);
            gameManager.setPuzzle(p3);
            System.out.println(p3.getDescription());
            String answer2 = "C";
            if (answer2.equals(p3.getSolution())){
                System.out.println("Correct!");
                gameManager.getPuzzles().remove(2);
            } else {
                System.out.println("Incorrect Answer");
                currentHints = p3.getHints();
                System.out.println(currentHints);
                String answer4 = "Spooky and scary";
                if (answer4.equals(p3.getSolution())){
                System.out.println("Correct!\n");
                gameManager.getPuzzles().remove(2);
                }
            }
        }
        if(gameManager.getPuzzles().isEmpty()){
            System.out.println("Congrats you've finished the puzzle");
        } else {
            System.out.println("Keep going");
        }
    }

    public void logoutAndShowData(){
        System.out.println();
        gameManager.logout();
        gameManager.login("leniRogers8","password8");
        Room room = gameManager.getRoom();
        Progress progress = room.getProgress();
        System.out.println("Current progress\n");
        System.out.println(gameManager.getRoom().percentComplete());

        HashMap<String, Puzzle> solved = progress.getPuzzlesSolved();
        System.out.println("Puzzles answered: ");
        ArrayList<Puzzle> solvedPuzzles = new ArrayList<>(solved.values());
        for (int i = 0; i < solved.size(); i++) {
            Puzzle p = solvedPuzzles.get(i);
            System.out.println(" - " + p.getName());
        }

        HashMap<String, String> hintsUsed = progress.getHintsUsed();
        for (String hint : hintsUsed.keySet()) {
            String puzzle = hintsUsed.get(hint);
            System.out.println("Hint: " + hint);
            System.out.println("Used on puzzle: " + puzzle);
        }

    }

    public void finishGame(){
        gameManager.login("leniRogers8","password8");
        Room room = gameManager.getRoom();
        User user = gameManager.getUser();
        Progress progress = room.getProgress();
        String gameName = room.getName();
        String userName = user.getUsername();
        int difficulty = room.getDifficulty();
        int hintsUsed = progress.getHintsUsed().size();
        double finalScore = room.getFinalScore();
        String coolMessage = " ";
        if (difficulty == 1) {
            coolMessage = "Easy peasy lemon squeezy!";
        } else if (difficulty == 2) {
            coolMessage = "Amazing job on the medium difficulty!";
        } else if (difficulty == 3) {
            coolMessage = "Even the hardest challenge wasn't hard enough for you!";
        } 

        try (PrintWriter writer = new PrintWriter(new FileWriter("CertificateOfComplete.txt"))){
            writer.println("------------------");
            writer.println("Escape Room Certificate");
            writer.println("------------------");
            writer.println();
            writer.println("Player: " + userName);
            writer.println("Game: " + gameName);
            writer.println();
            writer.println(coolMessage);
            writer.println();
            writer.println("Difficulty: " + difficulty);
            writer.println("Hints Used: " + hintsUsed);
            writer.println("Final Score: " + finalScore + "/1000");
            writer.println();
            writer.println("Thank you for playing! Play again soon");
        } catch (IOException e) {
            System.out.println("Error writing certificate: " + e.getMessage());
            return;
        }
        gameManager.openLeaderboard();
        System.out.println(gameManager.getLeaderboard());
        gameManager.closeLeaderboard();
    }
    public static void main(String[] args) {
		EscapeRoomUI escapeInterface = new EscapeRoomUI();
		escapeInterface.run();
	}
}
