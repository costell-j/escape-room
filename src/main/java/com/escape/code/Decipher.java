package com.escape.code;

import java.util.ArrayList;
/*
 * Decipher is a type of Puzzle that involves decrypting encoded messages.
 * @author Barbarnas
 */
public class Decipher extends Puzzle {

    public Decipher(String description, ArrayList<String> hints,
                    String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

    /**
     * Checks if player's answer correctly decrypts the puzzle text
     * using a Caesar cipher (shift by 3).
     */
    public boolean attempt(String answer) {
        if (answer == null || answer.isEmpty()) return false;

        String decrypted = decrypt(getDescription(), 3);

        if (answer.trim().equalsIgnoreCase(decrypted.trim()) ||
            answer.trim().equalsIgnoreCase(getSolution().trim())) {
            this.isSolved = true;
            return true;
        }

        return false;
    }
/*
 * Decrypts text using a Caesar cipher with the specified shift.
 */
    private String decrypt(String text, int shift) {
        if(text == null) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char) ((c - base - shift + 26) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
