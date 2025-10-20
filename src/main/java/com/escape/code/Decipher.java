package com.escape.code;
/*
 * Decipher
 * @author Barbarnas
 */
import java.util.ArrayList;

/** Decipher puzzle —  performs a simple normalized string comparison. */
public class Decipher extends Puzzle {

    public Decipher (String description, String name, ArrayList<String> hints, String solution, boolean isSolved) {
        super(description, name, hints, solution, isSolved);
    }
   
    public boolean attempt(String answer) {
        if (equalsLoose(answer, getSolution())) {
            isSolved = true;
            return true;
        }
        return false;
    }

    protected static boolean equalsLoose(String a, String b) {
        return norm(a).equals(norm(b));
    }

    private static String norm(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\h", " ").toLowerCase();
    }
}
