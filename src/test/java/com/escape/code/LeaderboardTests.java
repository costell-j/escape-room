package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

public class LeaderboardTests {

    private Leaderboard leaderboard;

    @Before
    public void setUp() {
        leaderboard = new Leaderboard();
    }

    @Test
    public void defaultConstructorInitializesEmptyPlayers() {
        HashMap<Integer, User> players = leaderboard.getPlayers();
        assertNotNull("players map should not be null", players);
        assertTrue("players map should be empty", players.isEmpty());
        assertEquals("toString of empty leaderboard should be empty string", "", leaderboard.toString());
    }

    @Test
    public void paramConstructorKeepsReferenceToProvidedMap() {
        HashMap<Integer, User> external = new HashMap<>();
        Leaderboard lb = new Leaderboard(external, true);
        assertSame("getPlayers should return the same map instance passed to constructor", external, lb.getPlayers());
    }

    @Test
    public void getPlayersReturnsMutableBackingMap() {
        HashMap<Integer, User> players = leaderboard.getPlayers();
        int before = players.size();
        // put a null value to avoid creating User instances
        players.put(1, null);
        assertEquals("backing map size should increase after put", before + 1, leaderboard.getPlayers().size());
        // remove and verify size goes back
        players.remove(1);
        assertEquals("backing map size should decrease after remove", before, leaderboard.getPlayers().size());
    }

    @Test
    public void setOpenAndIsOpenReflectState() {
        leaderboard.setOpen(true);
        assertTrue("leaderboard should be open after setOpen(true)", leaderboard.isOpen());

        leaderboard.setOpen(false);
        assertFalse("leaderboard should be closed after setOpen(false)", leaderboard.isOpen());
    }

    @Test
    public void paramConstructorOpenFlagHonored() {
        HashMap<Integer, User> external = new HashMap<>();
        Leaderboard openLB = new Leaderboard(external, true);
        assertTrue("leaderboard constructed with open=true should be open", openLB.isOpen());

        Leaderboard closedLB = new Leaderboard(external, false);
        assertFalse("leaderboard constructed with open=false should NOT be open", closedLB.isOpen());
    }
}
