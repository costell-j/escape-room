package com.escape.code;

import java.util.ArrayList;
import java.util.Objects;

public class MathPuzzle extends Puzzle<Double> {


    public MathPuzzle(String description, String name, double solution, ArrayList<String> hints, boolean isSolved) {
        super(description, name, solution, hints, isSolved);
        super.type = "Math";
    }

    @Override
    public void attempt(Double answer) {
        if(Objects.equals(answer, this.solution)) {
            super.solvePuzzle();
        }
    }
}

