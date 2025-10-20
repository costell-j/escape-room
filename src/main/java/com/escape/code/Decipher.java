package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;


public class Decipher extends Puzzle<String> {

    private HashMap<Character, Character> alphabet;

    public Decipher(String description, String name, String solution, ArrayList<String> hints, boolean isSolved, int shift) {
        super(description, name, solution, hints, isSolved);
        super.type = "Decipher";
        this.shift = shift;
        this.fillAlphabet();
    }

    private void fillAlphabet() {
        this.alphabet = new HashMap<>();
        Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i=0; i<chars.length; i++) {
            this.alphabet.put(chars[i], chars[i+shift]);
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

        if(formattedAnswer.equals(formattedSolution)) {
            super.solvePuzzle();
        }
    }
}