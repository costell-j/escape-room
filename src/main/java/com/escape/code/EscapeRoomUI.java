package com.escape.code;
import java.util.Scanner;
import java.util.UUID;

public class EscapeRoomUI {
    private GameManager gameManager;

    EscapeRoomUI(){
        gameManager = new GameManager();
    }

    public void run(){
        saveAndLogout();
        completeRoom();
        guestUser();
    }

    public void saveAndLogout(){
        System.out.println();

        gameManager.createAccount("MAK524", "password");
        gameManager.login("MAK524","password");
        if (gameManager.login("MAK524", "password") != null){
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log you in");
        }

        
        gameManager.chooseRoom("8b9e9e2-e9a3-4b49-818a-976c61288ae4");
        gameManager.setDifficulty(3);
        gameManager.getLeaderboard();
        gameManager.getHints();

        gameManager.getMap();
        gameManager.saveGame();
        gameManager.logout();
    }

    public void completeRoom(){
        System.out.println();

        gameManager.createAccount("randomname","it%j3o!'kfU");
        gameManager.login("randomname","it%j3o!'kfU");
        if (gameManager.login("randomname", "it%j3o!'kfU") != null) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }

        gameManager.getRoomList();
        System.out.println("The rooms are '...', '....', '...', and '...'");
        gameManager.chooseRoom("d8b9e9e2-e9a3-4b49-818a-976c61288ae4");
        gameManager.setDifficulty(1);
        gameManager.setVolume(80);

        gameManager.getMap();
        gameManager.closeMap();

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();

        gameManager.getHints();
        gameManager.getHints();
        gameManager.getHints();
        gameManager.getHints();
    }

    public void guestUser(){
        System.out.println();

        gameManager.login("Guest", "guestpassword");
        if (gameManager.login("Guest", "guestpassword") != null) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log in");
        }

        gameManager.getRoomList();
        gameManager.chooseRoom("d8b9e9e2-e9a3-4b49-818a-976c61288ae4");
        gameManager.setDifficulty(3);
        gameManager.setVolume(20);

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();
        gameManager.getHints();

        gameManager.getMap();
        gameManager.closeMap();
        gameManager.getHints();

        gameManager.exit();
    }
}
