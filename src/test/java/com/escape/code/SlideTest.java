package com.escape.code;
/**
 * Testing class for the slide 
 * @author Barbarnas Sumpter Jr.
 */
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
    public void testUpdateDescription() {
        slide.setDescription("New Slide Text");
        assertEquals("New Slide Text", slide.getDescription());
    }
    @Test
    public void testUpdateImagePath() {
        slide.setImagePath("updated.png");
        assertEquals("updated.png", slide.getImagePath());
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
    @Test
    public void testNullDescriptionDoesNotThrow() {
        try {
            slide.setDescription(null);
            assertNull(slide.getDescription());
        } catch (Exception e) {
            fail("null description should not throw");
        }
    }
}
