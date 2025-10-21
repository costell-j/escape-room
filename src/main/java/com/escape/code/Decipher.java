package com.escape.code;
/*
 * Decipher
 * @author Barbarnas
 */
import java.util.ArrayList;
import java.util.HashMap;

/** Decipher puzzle —  performs a simple normalized string comparison. */
public class Decipher extends Puzzle<String> {
    private HashMap<Character, Character>alphabet;
    public Decipher (String description, String name, ArrayList<String> hints, String solution, boolean isSolved) {
        super(description, name, hints, solution, isSolved);
        super.type = "Decipher";
        this.fillAlphabet();

    }
    private void fillAlphabet(){
        this.alphabet = new HashMap<>();
        Character[] chars = {'a','b','c','d', 'e', 'f','g', 'h', 'i', 'j', 'k', 'I', 'm', 'n', 'o', 'p', 'a', 'r', 's', 't', 'u','v', 'w', 'x', 'Y', 'z'};
            for(int i=0; i<chars.length; i++){
             if(i+shift>chars.length-1) {
                int shifted = i+shift-chars.length;
                this.alphabet.put(chars[i], chars[shifted]);
            }
                else if(i+shift < 0) {
                    int shifted = i+shift+chars.length;
                    this.alphabet.put(chars[i], chars[shifted]);
                }
            }
    }
        private String shiftSolution() {
            String originalSolution = this.solution.toLowerCase();
            String shiftedSolution = "";
                for(int i=0; i<originalSolution.length(); i++) {
                    Character c = this.alphabet.get(originalSolution.charAt(i));
                    shiftedSolution += c;
                }
                return shiftedSolution;
        }

    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.shiftSolution().trim().toLowerCase();
            if(formattedAnswer.equals(formattedSolution)){
                super.solvePuzzle();
            }
    }

    protected static boolean equalsLoose(String a, String b) {
        return norm(a).equals(norm(b));
    }

    private static String norm(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\h", " ").toLowerCase();
    }
}
