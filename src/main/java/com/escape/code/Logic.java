package com.escape.code;
/*
 * logic
 * @author Barbarnas
 */
import java.util.ArrayList;

    
    /** logic puzzle that uses normalized text comparison. */
    public class Logic extends Puzzle<String> {
    /**
     * Constructs a new {@code Logic} puzzle.
     *
     * @param description a human-readable description shown to the player
     * @param name        the puzzle's name/title
     * @param hints       a list of hint strings; if {@code null}, an empty list is used
     * @param solution    the canonical correct answer
     * @param isSolved    initial solved state
     * @param locked      whether the puzzle starts locked (may require an item)
     * @param item        the {@link Item} awarded upon solving (nullable)
     * @param givenItem   the {@link Item} required to interact/unlock (nullable)
     */

        public Logic(String description, String name, ArrayList<String> hints, String solution, boolean isSolved, boolean locked, Item item, Item givenItem) {
            super(description, name, hints, solution, isSolved, locked, item, givenItem);
            super.type = "Logic";
            this.solution = solution;
        }
  /**
     * Attempts to solve the puzzle using the provided {@code answer}.
     * <p>
     * The method normalizes both {@code answer} and the stored {@code solution}
     * (trim + lowercase) and marks the puzzle solved when they are equal.
     *
     * @param answer the player's proposed solution; must be non-{@code null}
     * @throws NullPointerException if {@code answer} is {@code null}
     * @implNote This check uses exact equality after simple normalization
     *           (trim + lowercase). It does not accept partial/fuzzy matches.
     */

        @Override
        public void attempt(String answer) {
            String formattedAnswer = answer.trim(). toLowerCase();
            String formattedSolution = this.solution.trim().toLowerCase();
                if(formattedAnswer.equals(formattedSolution)){
                    super.solvePuzzle();
                }
        }

    /**
     * Compares two strings for equality after normalization.
     * <p>
     * Normalization trims, collapses horizontal whitespace to a single space,
     * and lowercases the strings. {@code null} is treated as an empty string.
     *
     * @param a first string to compare (nullable)
     * @param b second string to compare (nullable)
     * @return {@code true} if the normalized strings are equal; {@code false} otherwise
     * @see #norm(String)
     */
        protected static boolean equalsLoose(String a, String b) {
            return norm(a).equals(norm(b));
        }

    /*
     * helper to normilze string for comparison. 
     * and trim, take away big spaces, and makes case insensitive.
     * @param s the input string (nullable)
     * @return the normalized string (never {@code null})
     */
        private static String norm(String s) {
            if (s == null) return "";
            return s.trim().replaceAll("\\h", " ").toLowerCase();
        }
    }
    
