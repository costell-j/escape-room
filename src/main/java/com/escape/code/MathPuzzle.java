package com.escape.code;
/*
 * math puzzle 
 * @author Barbarnas
 */
import java.util.ArrayList;
import java.util.Objects;
/*
 * Math puzzle class that extends puzzle and returns as a double.
 */
public class MathPuzzle extends Puzzle<Double> {
/*
 * Public mathpuzzle mataches the class name and passes in the parameters 
 * then using super to call the parent class Puzzle, then setting the type to math. 
 */
    public MathPuzzle (String description,String name, ArrayList<String> hints, double solution, boolean isSolved) {
        super(description, name, hints, solution, isSolved);
        super.type= "Math";
    }
/*
 * overides the parrent class attempt method 
 * with it being void just meaning that it return nothing being void helps with null as it will return false if null.
 * also does not allow close enough answers as of right now.
 * super calls the parent class to mark this puzzle as sloved.
 */
    @Override
    public void attempt(Double answer){
        if (Objects.equals(answer, this.solution)){
            super.solvePuzzle();
        }
    }
}

