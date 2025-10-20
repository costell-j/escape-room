package com.escape.code;

import java.util.ArrayList;

public class Riddle extends Puzzle<String> {


    public Riddle(String description, String name, String solution, ArrayList<String> hints, boolean isSolved) {
        super(description, name, solution, hints, isSolved);
        super.type = "Riddle";
    }

    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.solution.trim().toLowerCase();

        if(formattedAnswer.equals(formattedSolution)) {
            super.solvePuzzle();
        }
    }
}