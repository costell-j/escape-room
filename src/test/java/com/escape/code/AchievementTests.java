package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
/*
 * Testing for Achievement class
 * @Author Erin Check
 */
public class AchievementTests {

    private Achievement lockedAchievement;
    private Achievement unlockedAchievement;

    @Before
    public void setUp() {
        lockedAchievement = new Achievement("First Step", false);
        unlockedAchievement = new Achievement("Winner", true);
    }

    @Test
    public void constructorInitializesFields() {
        assertEquals("First Step", lockedAchievement.getTitle());
        assertFalse(lockedAchievement.isUnlocked());

        assertEquals("Winner", unlockedAchievement.getTitle());
        assertTrue(unlockedAchievement.isUnlocked());
    }

    @Test
    public void unlockDoesNotChangeLockedStateWithCurrentLogic() {
        lockedAchievement.unlock();
        assertFalse(lockedAchievement.isUnlocked());

        unlockedAchievement.unlock();
        assertTrue(unlockedAchievement.isUnlocked());
    }

    @Test
    public void toStringIncludesTitleAndUnlockedState() {
        String s1 = lockedAchievement.toString();
        assertTrue("toString should include the title", s1.contains("First Step"));
        assertTrue("toString should include the unlocked state", s1.contains("false"));

        String s2 = unlockedAchievement.toString();
        assertTrue("toString should include the title", s2.contains("Winner"));
        assertTrue("toString should include the unlocked state", s2.contains("true"));
    }

    @Test
    public void toStringUsesExpectedFormat() {
        String expectedPrefix = "Title: ";
        assertTrue(lockedAchievement.toString().startsWith(expectedPrefix));
    }
}
