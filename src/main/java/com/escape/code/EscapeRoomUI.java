package com.escape.code;
import java.util.Scanner;
import java.util.UUID;

public class EscapeRoomUI {
    private GameManager gameManager;

    EscapeRoomUI(){
        gameManager = new GameManager();
    }

    public void run(){
        scenario1();
        scenario2();
    }

    public void scenario1(){
        System.out.println();

        gameManager.createAccount("csmith", "12345");
        gameManager.login("csmith","12345");
        if (gameManager.isMatch("csmith", "12345")){
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log you in");
        }

        
        gameManager.chooseRoom(8b9e9e2-e9a3-4b49-818a-976c61288ae4);
        gameManager.setDifficulty(3);
        gameManager.getLeaderboard();
        System.out.println("You are in first place");
        gameManager.getHints();
        System.out.println("4 hints left");

        gameManager.getMap();
        gameManager.saveGame();
        gameManager.logout();
    }

    public void scenario2(){
        System.out.println();

        gameManager.createAccount("echeck","45601");
        gameManager.login("echeck","45601");
        if (gameManager.isMatch("echeck", "45601")){
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Couldn't log you in");
        }

        gameManager.getRoomList();
        System.out.println("The rooms are '...', '....', '...', and '...'");
        gameManager.chooseRoom(d8b9e9e2-e9a3-4b49-818a-976c61288ae4);
        gameManager.setDifficulty(1);
        gameManager.setVolume(20);
        System.out.println("Volume is set to 20");

        gameManager.getMap();
        gameManager.closeMap();

        gameManager.getLeaderboard();
        gameManager.closeLeaderboard();

        gameManager.getHints();
        System.out.println("2 hints left");
        gameManager.getHints();
        System.out.println("1 hint left");
        gameManager.getHints();
        System.out.println("No hints left");
        gameManager.getHints();
        System.out.println("No hints left");


    }
}
