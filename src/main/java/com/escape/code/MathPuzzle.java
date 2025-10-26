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
    /**
     * Constructs a new {@code MathPuzzle}.
     *
     * @param description a human-readable description of the puzzle
     * @param name        the puzzle's name/title
     * @param hints       a list of hint strings; if {@code null}, an empty list is used
     * @param solution    the correct numeric answer
     * @param isSolved    initial solved state
     * @param locked      whether the puzzle starts locked (may require an item)
     * @param item        the {@link Item} awarded upon solving (nullable)
     * @param givenItem   the {@link Item} required to interact/unlock (nullable)
     */
    public MathPuzzle (String description, String name, ArrayList<String> hints, double solution, boolean isSolved, boolean locked, Item item, Item givenItem) {
        super(description, name, hints, solution, isSolved, locked, item, givenItem);
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

