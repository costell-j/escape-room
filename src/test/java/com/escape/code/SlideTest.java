package com.escape.code;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SlideTest {

    private Slide slide;

    @Before
    public void setup() {
        slide = new Slide("Welcome to the Escape Room!", "intro.png");
    }

    @Test
    public void testConstructorSetsFields() {
        assertEquals("Welcome to the Escape Room!", slide.getDescription());
        assertEquals("intro.png", slide.getImagePath());
    }

    @Test
    public void testSettersUpdateValues() {
        slide.setDescription("Updated Description");
        slide.setImagePath("updated.png");

        assertEquals("Updated Description", slide.getDescription());
        assertEquals("updated.png", slide.getImagePath());
    }

    @Test
    public void testToStringContainsImagePath() {
        String str = slide.toString().toLowerCase();
        assertTrue(str.contains("intro") || str.contains("image"));
    }

    @Test
    public void testEmptyImagePathHandling() {
        Slide blankSlide = new Slide("Blank", "");
        assertEquals("", blankSlide.getImagePath());
    }
}
