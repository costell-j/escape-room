package com.escape.code;

import java.util.ArrayList;

public class Logic extends Puzzle<String> {


    public Logic(String description, String name, String solution, ArrayList<String> hints, boolean isSolved) {
        super(description, name, solution, hints, isSolved);
        super.type = "Logic";
        this.solution = solution;
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