package com.escape.code;
/**
 * Tests the Item class.
 * Author: Barbarnas Sumpter Jr.
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTest {

    @Test
    public void testItemCreationAndAccessors() {
        Item item = new Item("Silver Key", "Opens the final door", false);
        assertEquals("Silver Key", item.getName());
        assertEquals("Opens the final door", item.getDescription());
        assertFalse(item.isUsed());
    }

    @Test
    public void testItemSetters() {
        Item item = new Item("Coin", "Shiny", false);
        item.setName("Gold Coin");
        item.setDescription("Ancient currency");
        item.setUsed(true);

        assertEquals("Gold Coin", item.getName());
        assertEquals("Ancient currency", item.getDescription());
        assertTrue(item.isUsed());
    }
}
