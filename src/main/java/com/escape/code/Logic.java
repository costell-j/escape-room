package com.escape.code;
/*
 * logic
 * @author Barbarnas
 */
import java.util.ArrayList;

    
    /** logic puzzle that uses normalized text comparison. */
    public class Logic extends Puzzle<String> {

        public Logic(String description, String name, ArrayList<String> hints, String solution, boolean isSolved, boolean locked, Item item, Item givenItem) {
            super(description, name, hints, solution, isSolved, locked, item, givenItem);
            super.type = "Logic";
            this.solution = solution;
        }
/*
 * implements the core check then compares answer to stored solution using loose equals ingnore case and whitespace
 */

        @Override
        public void attempt(String answer) {
            String formattedAnswer = answer.trim(). toLowerCase();
            String formattedSolution = this.solution.trim().toLowerCase();
                if(formattedAnswer.equals(formattedSolution)){
                    super.solvePuzzle();
                }
        }
/*  
 * helper method. exposes a normalized string equality. 
 * and normilizes both strings.
 */
        protected static boolean equalsLoose(String a, String b) {
            return norm(a).equals(norm(b));
        }
/*
 * helper to normilze string for comparison. 
 * and trim, take away big spaces, and makes case insensitive.
 */
        private static String norm(String s) {
            if (s == null) return "";
            return s.trim().replaceAll("\\h", " ").toLowerCase();
        }
    }
    
