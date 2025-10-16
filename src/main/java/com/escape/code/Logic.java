package com.escape.code;
/*
 *logic
 * @author Barbarnas
 */
import java.util.ArrayList;

    
    /** logic puzzle that uses normalized text comparison. */
    public class Logic extends Puzzle {

        public Logic(String description, ArrayList<String> hints,
                     String solution, boolean isSolved) {
            super(description, hints, solution, isSolved);
        }
/*
 * implements the core check then compares answer to stored solution using loose equals ingnore case and whitespace
 */
        public boolean attempt(String answer) {
            if (equalsLoose(answer, getSolution())) {
                isSolved = true;
                return true;
            }
            return false;
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
            return s.trim().replaceAll("\\s+", " ").toLowerCase();
        }
    }
    
