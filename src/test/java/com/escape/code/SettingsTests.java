package com.escape.code;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SettingsTests {
    Settings settings = new Settings(0, 0);

    @Test
    public void testPositiveVolume() {
        settings.changeVolume(5);
        assertEquals(5, settings.getVolume());
    }

    @Test
    public void testNegativeVolume() {
        settings.changeVolume(-3);
        assertEquals(0, settings.getVolume());
    }

    @Test
    public void testPositiveDifficulty() {
        settings.changeDifficulty(2);
        assertEquals(2, settings.getDifficulty());
    }

    @Test
    public void testNegtiveDifficulty() {
        settings.changeDifficulty(-1);
        assertEquals(1, settings.getDifficulty());
    }

    @Test
    public void testDifficultyOutOfBounds() {
        settings.changeDifficulty(5);
        assertEquals(1, settings.getDifficulty());
    }

    @Test
    public void testNegativeVolumeInConstructor() {
        Settings s = new Settings(-3,1);
        assertEquals(0, s.getVolume());
    }
    @Test
    public void testNegativeDifficultyInConstructor() {
        Settings s = new Settings(0, -4);
        assertEquals(0, s.getDifficulty());
    }
    
    @Test
    public void testDifficultyOutOfBoundsInConstructor() {
        Settings s = new Settings(0, 6);
        assertEquals(1, s.getDifficulty());
    }
}
