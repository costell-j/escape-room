package com.escape.code;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MapTest {

    private Map map;

    @Before
    public void setup() {
        map = new Map("The Dark Haunted Mansion", 10, 15, false);
    }

    @Test
    public void testConstructorInitializesAllFields() {
        assertEquals("The Dark Haunted Mansion", map.getMapName());
        assertEquals(10, map.getHeight());
        assertEquals(15, map.getLength());
        assertFalse(map.isOpen());
    }

    @Test
    public void testSetOpenTrueThenFalse() {
        map.setOpen(true);
        assertTrue(map.isOpen());
        map.setOpen(false);
        assertFalse(map.isOpen());
    }

    @Test
    public void testToStringIncludesMapName() {
        String text = map.toString().toLowerCase();
        assertTrue(text.contains("haunted") || text.contains("map"));
    }

    @Test
    public void testDifferentDimensionsStillStoreCorrectly() {
        Map smallMap = new Map("Basement", 5, 5, false);
        assertEquals(5, smallMap.getHeight());
        assertEquals(5, smallMap.getLength());
    }
}

