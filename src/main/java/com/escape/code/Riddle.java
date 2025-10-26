package com.escape.code;
import java.lang.reflect.Array;
/*
 * Riddle 
 * @author Barbarnas
 */
import java.util.ArrayList;

/** iddle puzzle that checks answer vs. solution (case/space-insensitive). */
public class Riddle extends Puzzle<String> {
    /**
     * Constructs a new {@code Riddle} puzzle.
     *
     * @param description a human-readable description or the riddle text
     * @param name        the puzzle's name/title
     * @param hints       list of hint strings; if {@code null}, an empty list is used
     * @param solution    the canonical correct answer
     * @param isSolved    initial solved state
     * @param locked      whether the puzzle starts locked (may require an item)
     * @param item        the {@link Item} awarded upon solving (nullable)
     * @param givenItem   the {@link Item} required to interact/unlock (nullable)
     */
    public Riddle( String description, String name, ArrayList<String> hints, String solution, boolean isSolved, boolean locked, Item item, Item givenItem) {
        super(description, name, hints, solution, isSolved, locked, item, givenItem);
        super.type = "Riddle";
    }


    /**
     * Attempts to solve the riddle using the provided {@code answer}.
     * <p>
     * The method trims and lowercases both {@code answer} and the stored
     * {@code solution}, then marks the puzzle solved if they are equal.
     *
     * @param answer the player's proposed solution; must be non-{@code null}
     * @throws NullPointerException if {@code answer} is {@code null}
     * @implNote This performs an exact equality check after simple normalization
     *           (trim + lowercase). For looser comparison (e.g., collapsing
     *           internal whitespace), consider {@link #equalsLoose(String, String)}.
     * @see #solvePuzzle()
     */
    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.solution.trim().toLowerCase();

            if(formattedAnswer.equals(formattedSolution)){
                super.solvePuzzle();
            }

    }
        
        
 /**
     * Compares two strings for equality after loose normalization.

     * @param a first string (nullable)
     * @param b second string (nullable)
     * @return {@code true} if the normalized strings are equal; {@code false} otherwise
     * @see #norm(String)
     */
    protected static boolean equalsLoose(String a, String b) {
        return norm(a).equals(norm(b));
    }

    private static String norm(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\h", " ").toLowerCase();
    }
}
