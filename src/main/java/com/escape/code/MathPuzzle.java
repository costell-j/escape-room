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
/*
 * tolerance for a floating point comparison and EPS(Epsilon) allows for small diffence in answers
 */
    private static final double EPS = 1e-6; 

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
/*
 * used to convert from strings to real numbers and trim them to takr spaces away
 */
}

