package com.escape.code;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProgressTests {
    @Test
    public void testDefaultConsrtuctor() {
        Progress progress = new Progress();
        assertNotNull(progress.getPuzzlesSolved());
        assertNotNull(progress.getAchievements());
        assertNotNull(progress.getItems());
        assertNotNull(progress.getHintsUsed());
        assertEquals(0, progress.getCluesUsed());
        assertEquals(0, progress.getCompletionTime());
    }

    @Test
    public void testAddItem() {
        Progress progress = new Progress();
        Item i = new Item("Key", "Opens door", false);
        progress.addItem(i);
        assertTrue(progress.getItems().contains(i));
    }

    @Test
    public void testAddNullItem() {
        Progress progress = new Progress();
        progress.addItem(null);
        assertTrue(progress.getItems().isEmpty());
    }

    @Test
    public void testSetAndGetCurrentPuzzle() {
        Progress progress = new Progress();
        progress.setCurrentPuzzle(3);
        assertEquals(3, progress.getCurrentPuzzle());
    }

        
}
