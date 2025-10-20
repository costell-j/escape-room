package com.escape.code;
import java.util.UUID;
import java.util.ArrayList;

public class EscapeRoomUI {
    private GameManager gameManager;

    EscapeRoomUI(){
        gameManager = new GameManager();
    }

    public void run(){
        new EscapeRoomUI();
        duplicateAccount();
        validUser();
        newUser();
        duplicateAccountnewAccount();
    }

    public void duplicateAccount(){
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
        
        gameManager.chooseRoom(UUID.fromString("e0d413f5-1bcf-4cdc-b90f-f051b35ba5bb"));
        gameManager.setDifficulty(3);
        gameManager.getLeaderboard();
        ArrayList<String> newHints = gameManager.getHints();
        System.out.println(newHints);

        gameManager.getMap();
        gameManager.saveGame();
        gameManager.logout();
    }

    public void validUser(){
        System.out.println();

        gameManager.login("randomname","it%j3o!'kfU");
        if (gameManager.login("randomname", "it%j3o!'kfU")) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }

        gameManager.getRoomList();
        gameManager.chooseRoom(UUID.fromString("d8b9e9e2-e9a3-4b49-818a-976c61288ae4"));
        gameManager.setDifficulty(1);
        gameManager.setVolume(80);

        gameManager.getMap();
        gameManager.closeMap();

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();

        ArrayList<String> newHints = gameManager.getHints();
        System.out.println(newHints);
        gameManager.logout();
    }

    public void newUser(){
        System.out.println();

        if (gameManager.createAccount("Guest", "guestpassword")){
            System.out.println("Account created!");
        } else {
            System.out.println("Account couldn't be created");
        }

        gameManager.login("Guest", "guestpassword");
        if (gameManager.login("Guest", "guestpassword")) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }

        gameManager.getRoomList();
        gameManager.chooseRoom(UUID.fromString("e0d413f5-1bcf-4cdc-b90f-f051b35ba5bb"));
        gameManager.setDifficulty(3);
        gameManager.setVolume(20);

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();
        ArrayList<String> newHints = gameManager.getHints();
        System.out.println(newHints);

        gameManager.getMap();
        gameManager.closeMap();

        gameManager.exit();
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

        gameManager.getRoomList();
        gameManager.chooseRoom(UUID.fromString("e0d413f5-1bcf-4cdc-b90f-f051b35ba5bb"));

        //default difficulty
        //default volume

        ArrayList<String> newHints = gameManager.getHints();
        System.out.println(newHints);

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();
        gameManager.saveGame();

    }


    public static void main(String[] args) {
		EscapeRoomUI escapeInterface = new EscapeRoomUI();
		escapeInterface.run();
	}
}
