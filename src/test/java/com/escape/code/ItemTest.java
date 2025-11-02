package com.escape.code;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ItemTest {

    
    private Item item;

    @Before
    public void setup() {
        item = new Item("Silver Key", "Opens final door", false);
    }

    @Test
    public void testConstructorSetsAllFields() {
        assertEquals("Silver Key", item.getName());
        assertEquals("Opens final door", item.getDescription());
        assertFalse(item.isUsed());
    }

    @Test
    public void testSettersChangeValues() {
        item.setName("Golden Key");
        item.setDescription("Unlocks treasure chest");
        item.setUsed(true);

        assertEquals("Golden Key", item.getName());
        assertEquals("Unlocks treasure chest", item.getDescription());
        assertTrue(item.isUsed());
    }
    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals("Diamond Key", item.getName());
        assertEquals("Opens a locked chest", item.getDescription());
        assertFalse(item.isUsed());
    }

    @Test
    public void testSetUsedMarksItemAsUsed() {
        item.setUsed(true);
        assertTrue(item.isUsed());
    }

    @Test
    public void testItemCanBeReusedAfterReset() {
        item.setUsed(true);
        item.setUsed(false);
        assertFalse(item.isUsed());
    }

    @Test
    public void testItemToStringContainsKeyInfo() {
        String desc = item.toString().toLowerCase();
        assertTrue(desc.contains("key") || desc.contains("silver"));
    }

    @Test
    public void testSetNullDescriptionDoesNotCrash() {
        try {
            item.setDescription(null);
            assertNull(item.getDescription());
        } catch (Exception e) {
            fail("null description should not throw an exception");
        }
    }
   
}
