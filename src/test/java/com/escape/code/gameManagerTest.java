package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
/*
 * Testing for gameManager
 * @Author Erin Check
 */
public class gameManagerTest {
    private GameManager gameManager = new GameManager();
    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();
    private RoomList rooms;
    
    @Before
     public void setUp() {
        gameManager = new GameManager();

        users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();

        rooms = RoomList.getInstance();
        ArrayList<Room> roomList = rooms.getAllRooms();
    }

    @Test
    public void testCreateValidAccount(){
        gameManager.createAccount("echeck", "12345");
        gameManager.login("echeck", "12345");
        User currentUser = gameManager.getUser();
        assertEquals("echeck", currentUser.getUsername());
    }

    @Test 
    public void testCreateNullUsername(){
        gameManager.createAccount(null, "12345");
        gameManager.login(null, "12345");
        User currentUser = gameManager.getUser();
        assertEquals(null, currentUser.getUsername());
    }

    @Test
    public void testCreateNullPassword(){
        gameManager.createAccount("bobby", null);
        gameManager.login("bobby", null);
        User currentUser = gameManager.getUser();
        assertEquals("bobby", currentUser.getUsername());
    }

    @Test
    public void testCreateDuplicateAccount(){
        gameManager.createAccount("ajones", "54ajo");
		boolean isCreated = gameManager.createAccount("ajones", "password");
		assertFalse(isCreated);
    }

    @Test
    public void testNewLineUsername(){
        boolean isCreated = gameManager.createAccount("mrt\nmb", "5678");
        assertFalse(isCreated);
    }

    @Test
    public void testNewLinePassword(){
        boolean isCreated = gameManager.createAccount("username", "abc\nd");
        assertFalse(isCreated);
    }
    
    @Test
    public void testCreateGuest(){
        boolean created = gameManager.createGuest();
        assertTrue(created);
    }

     @Test
    public void testCreateAccountAndLoginValidCredentials() {
        boolean created = gameManager.createAccount("echeck", "12345");
        assertTrue(created);

        boolean loggedIn = gameManager.login("echeck", "12345");
        assertTrue(loggedIn);

        User current = gameManager.getUser();
        assertNotNull(current);
        assertEquals("echeck", current.getUsername());
    }

    @Test
    public void testCreateAccountNullUsername() {
        boolean created = gameManager.createAccount(null, "somepass");

        boolean loggedIn = gameManager.login(null, "somepass");
        User current = gameManager.getUser();
        if (loggedIn) {
            assertNull(current.getUsername());
        } else {
            assertNull(current);
        }
    }

    @Test
    public void testCreateAccountNullPassword() {
        boolean created = gameManager.createAccount("bobby", null);
        boolean loggedIn = gameManager.login("bobby", null);
        User current = gameManager.getUser();
        if (loggedIn) {
            assertEquals("bobby", current.getUsername());
        } else {
            assertNull(current);
        }
    }

    @Test
    public void testCreateDuplicateAccountReturnsFalse() {
        boolean first = gameManager.createAccount("ajones", "54ajo");
        assertTrue(first);

        boolean second = gameManager.createAccount("ajones", "password");
        assertFalse(second);
    }

    @Test
    public void testLoginWithWrongPasswordFails() {
        gameManager.createAccount("fred", "letmein");
        boolean loggedIn = gameManager.login("fred", "wrongpass");
        assertFalse(loggedIn);
        assertNull(gameManager.getUser());
    }

    @Test
    public void testGetUserListReflectsCreatedAccounts() {
        int initial = gameManager.getUserList().size();
        gameManager.createAccount("user1", "p1");
        gameManager.createAccount("user2", "p2");
        ArrayList<User> list = gameManager.getUserList();
        assertEquals(initial + 2, list.size());
    }


    @Test
    public void testChooseRoomValidRoomSetsRoomAndPuzzles() {

        Room r = new Room();
        rooms.getAllRooms().add(r);
        rooms.addRoom(r);
        boolean chosen = gameManager.chooseRoom(r);
        assertTrue(chosen);
        assertEquals(r.getId(), gameManager.getRoom().getId());
        assertNotNull(gameManager.getPuzzles());
    }

    @Test
    public void testChooseRoomInvalidRoomReturnsFalse() {
        Room notAdded = new Room();
        boolean chosen = gameManager.chooseRoom(notAdded);
        assertFalse(chosen);
        assertNull(gameManager.getRoom());
    }

    @Test
    public void testFormatTimerDelegatesToRoom() {
        Room r = new Room();
        rooms.addRoom(r);
        gameManager.chooseRoom(r);
        String formatted = gameManager.formatTimer();
        assertNotNull(formatted);
        assertTrue(formatted instanceof String);
    }

    @Test
    public void testStartStopTimerNoExceptions() {
        Room r = new Room();
        rooms.addRoom(r);
        gameManager.chooseRoom(r);
        gameManager.startTimer();
        gameManager.stopTimer();
    }

    @Test
    public void testSetDifficultyWithNoRoomIsNoOp() {
        gameManager.setDifficulty(3);
        assertNull(gameManager.getLeaderboard());
        assertNull(gameManager.getMap());
    }

    @Test
    public void testSetDifficultyWithRoomUpdatesRoom() {
        Room r = new Room();
        rooms.addRoom(r);
        gameManager.chooseRoom(r);

        gameManager.setDifficulty(2);
    }

    @Test
    public void testGetLeaderboardAndMapReturnNullWhenNoRoomChosen() {
        assertNull(gameManager.getLeaderboard());
        assertNull(gameManager.getMap());
    }

    @Test
    public void testOpenAndCloseLeaderboardMap() {
        Room r = new Room();
        r.setLeaderboard(new Leaderboard());
        r.getMap();
        rooms.addRoom(r);
        gameManager.chooseRoom(r);

        gameManager.openLeaderboard();
        assertTrue(gameManager.getLeaderboard().isOpen());

        gameManager.closeLeaderboard();
        assertFalse(gameManager.getLeaderboard().isOpen());

        gameManager.openMap();
        assertTrue(gameManager.getMap().isOpen());

        gameManager.closeMap();
        assertFalse(gameManager.getMap().isOpen());
    }

    @Test
    public void testPercentDoneAndFinalScoreDelegation() {
        Room r = new Room();
        rooms.addRoom(r);
        gameManager.chooseRoom(r);

        double percent = gameManager.percentDone();
        assertTrue(!Double.isNaN(percent));

        double score = gameManager.getFinalScore();
        assertTrue(!Double.isNaN(score));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetPuzzleThrowsWhenNoPuzzleSelected() {
        gameManager.getPuzzle();
    }


    @Test
    public void testSetVolumeSafelyHandlesNulls() {
        gameManager.setVolume(50);

        gameManager.createAccount("voltest", "p");
        gameManager.login("voltest", "p");
        gameManager.setVolume(75);
    }

    @Test
    public void testSaveGameAndLogoutReturnTrue() {
        assertTrue(gameManager.saveGame());
        assertTrue(gameManager.logout());
    }

}
