package com.escape.code;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests methods in Puzzle and its subclasses
 * @author Costell Johnson
 */
public class PuzzleTests {
    MathPuzzle math = new MathPuzzle("What is 2+2?", "Math 1", null, 4, false, false, null, null);
    Logic logic = new Logic("Question", "Logic1", null, "Yes", false, false, null, null);
    Decipher d = new Decipher("Decode this", "Cipher1", null, "Answer", false, false, null, null, 3);
    Riddle r = new Riddle("What has keys but can't open doors?", "R1", null, "piano", false, false, null, null);

    @Test
    public void testIncorrentAnswer() {
        math.attempt(0.0);
        assertEquals(false, math.isSolved());
    }

    @Test
    public void testAnswer() {
        math.attempt(4.0);
        assertEquals(true, math.isSolved());
    }
    @Test
    public void testPuzzleType() {
        assertEquals("Math", math.getType());
    }

    @Test
    public void testRepeatedSolveKeepsSolvedTrue() {
        math.attempt(4.0);
        math.attempt(4.0);
        assertTrue(math.isSolved());
    }

    @Test
    public void testWrongAnswerNotSolvedMath() {
        math.attempt(5.0);
        assertFalse(math.isSolved());
    }
    @Test
    public void testIsSolvedLogic() {
        logic.attempt(" yes ");
        assertTrue(logic.isSolved());
    }

    @Test
    public void testWrongAnswerNotSolvedLogic() {
        logic.attempt("no");
        assertFalse(logic.isSolved());
    }

    @Test
    public void testEqualsLooseIgnoresCaseAndSpace() {
        assertTrue(Logic.equalsLoose("Yes", " yes "));
        assertFalse(Logic.equalsLoose("Yes", "no"));
    }
    @Test
    public void testIsSolvedDecipher() {
        d.attempt("answer");
        assertTrue(d.isSolved());
    }

    @Test
    public void testWrongAnswerNotSolvedDecipher() {
        d.attempt("wrong");
        assertFalse(d.isSolved());
    }

    @Test
    public void testShiftValueStored() {
        Decipher d = new Decipher("Desc", "Name", null, "sol", false, false, null, null, 5);
        assertEquals(5, d.getShift());
    }
    @Test
    public void testIsSolvedRiddle() {
        r.attempt("Piano");
        assertTrue(r.isSolved());
    }

    @Test
    public void testWrongAnswerNotSolvedRiddle() {
        r.attempt("keyboard");
        assertFalse(r.isSolved());
    }

    @Test
    public void testEqualsLooseWorks() {
        assertTrue(Riddle.equalsLoose(" Piano ", "piano"));
        assertFalse(Riddle.equalsLoose("piano", "violin"));
    }

}
