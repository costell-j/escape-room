package com.escape.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for Room.java
 * @author Matthew Kight
 */

public class RoomTests {
    private ArrayList<Room> rooms;
    private Room room;

    @Before
    public void setup() {
        DataLoader.getUsers();
        RoomList rl = RoomList.getInstance();
        DataLoader.loadLeaderboards();
        rooms = rl.getAllRooms();
        room = rooms.get(0);
    }
    
    @Test
    public void selectExistingProgressTest() {
        room.setProgress("MAK524");
        boolean correct = room.getProgress().getCompletionTime() == 1000;

        assertTrue(correct);
    }

    @Test
    public void selectNewProgressTest() {
        room.setProgress("MAK");
        boolean correct = room.getProgress().getCompletionTime() == 0;

        assertTrue(correct);
    }

    @Test
    public void selectInvalidUserProgress() {
        room.setProgress("");
        boolean correct = room.getProgress().getCompletionTime() == 0;

        assertFalse(correct);
    }

    @Test
    public void setValidDifficultyTest() {
        room.setDifficulty(2);
        room.timeChange(2);
        boolean correct = room.getTimer() == 900;

        assertTrue(correct);
    }

    @Test
    public void setInvalidDifficultyTest() {
        room.setDifficulty(19);
        room.timeChange(19);
        boolean correct = room.getTimer() == 1200;

        assertTrue(correct);
    }

    @Test
    public void timerFormatTest() {
        room.setDifficulty(2);
        room.timeChange(2);
        String time = room.formatTimer();
        boolean correct = time.equals("15:00");

        assertTrue(correct);
    }

    @Test
    public void startTimerTest() {
        room.setDifficulty(2);
        room.timeChange(2);
        room.startTimer();
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String time = room.formatTimer();
        boolean correct = time.equals("14:59");

        assertTrue(correct);
    }

    @Test
    public void stopTimerTest() {
        room.setDifficulty(2);
        room.timeChange(2);
        room.startTimer();
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        room.stopTimer();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String time = room.formatTimer();
        boolean correct = time.equals("14:59");

        assertTrue(correct);
    }

    @Test
    public void resetTimerTest() {
        room.setDifficulty(2);
        room.timeChange(2);
        room.startTimer();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        room.resetTimer();
        String time = room.formatTimer();
        boolean correct = time.equals("15:00");

        assertTrue(correct);
    }

    @Test
    public void getLeaderboardTest() {
        Leaderboard lb = room.getLeaderboard();
        boolean correct = lb.getPlayers().size() == 3;

        assertTrue(correct);
    }

    @Test
    public void getMapTest() {
        Map map = room.getMap();
        boolean correct = map.getMapName().equals("map") && map.getHeight() == 500 && map.getLength() == 500 && map.isOpen() == false;

        assertTrue(correct);
    }

    @Test
    public void getStoryTest() {
        ArrayList<Slide> story = room.getStory();
        boolean correct = story.size() == 8;

        assertTrue(correct);
    }

    @Test
    public void scoreCalcTest() {
        room.setDifficulty(1);
        room.timeChange(1);
        room.getProgress().setCluesUsed(-100);
        double num = room.getFinalScore();
        boolean correct = room.getFinalScore() < 1000;

        assertTrue(correct);
    }
}
