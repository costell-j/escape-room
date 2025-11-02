package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
/*
 * Testing for Leaderboard class
 * @Author Erin Check
 */
public class LeaderboardTests {

    private Leaderboard leaderboard;

    @Before
    public void setUp() {
        leaderboard = new Leaderboard();
    }

    @Test
    public void defaultConstructorInitializesEmptyPlayers() {
        HashMap<Integer, User> players = leaderboard.getPlayers();
        assertNotNull(players);
        assertTrue(players.isEmpty());
        assertEquals("", leaderboard.toString());
    }

    @Test
    public void paramConstructorKeepsReferenceToProvidedMap() {
        HashMap<Integer, User> external = new HashMap<>();
        Leaderboard lb = new Leaderboard(external, true);
        assertSame(external, lb.getPlayers());
    }

    @Test
    public void getPlayersReturnsMutableBackingMap() {
        HashMap<Integer, User> players = leaderboard.getPlayers();
        int before = players.size();
        players.put(1, null);
        assertEquals(before + 1, leaderboard.getPlayers().size());
        players.remove(1);
        assertEquals(before, leaderboard.getPlayers().size());
    }

    @Test
    public void setOpenAndIsOpenReflectState() {
        leaderboard.setOpen(true);
        assertTrue(leaderboard.isOpen());

        leaderboard.setOpen(false);
        assertFalse(leaderboard.isOpen());
    }

    @Test
    public void paramConstructorOpenFlagHonored() {
        HashMap<Integer, User> external = new HashMap<>();
        Leaderboard openLB = new Leaderboard(external, true);
        assertTrue(openLB.isOpen());

        Leaderboard closedLB = new Leaderboard(external, false);
        assertFalse(closedLB.isOpen());
    }
}
