package com.escape.code;

/**
 * Tests the Slide class.
 * Author: Barbarnas Sumpter Jr.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SlideTest {

    @Test
    public void testSlideCreationAndAccessors() {
        Slide slide = new Slide("Welcome to the escape room!", "intro.png");
        assertEquals("Welcome to the escape room!", slide.getDescription());
        assertEquals("intro.png", slide.getImagePath());
    }

    @Test
    public void testSlideSetters() {
        Slide slide = new Slide("Old", "old.png");
        slide.setDescription("Updated");
        slide.setImagePath("new.png");

        assertEquals("Updated", slide.getDescription());
        assertEquals("new.png", slide.getImagePath());
    }
}
