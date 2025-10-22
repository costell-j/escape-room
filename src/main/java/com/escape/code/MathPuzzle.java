package com.escape.code;
/*
 * math puzzle 
 * @author Barbarnas
 */
import java.util.ArrayList;
import java.util.Objects;
/*
 * math puzzle that checks user answer as a double
 */
public class MathPuzzle extends Puzzle<Double> {

    public MathPuzzle (String description,String name, ArrayList<String> hints, double solution, boolean isSolved) {
        super(description, name, hints, solution, isSolved);
        super.type= "Math";
    }

    @Override
    public void attempt(Double answer){
        if (Objects.equals(answer, this.solution)){
            super.solvePuzzle();
        }
    }
}

