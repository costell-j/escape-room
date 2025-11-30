package com.escape.code;

import java.util.ArrayList;

/*
 * Puzzle is the base class for all types of puzzles in the escape room game.
 * It contains common attributes and methods shared by all puzzle types.
 * @author Barbarnas
 */

public abstract class Puzzle<T> {

    protected String description;
    protected String name;
    protected ArrayList<String> hints;
    protected boolean locked;
    protected Item item;
    protected Item givenItem; 
    protected  T solution;
    protected boolean isSolved;
    protected String type;
    protected int shift;

    /**
     * Constructs a new {@code Puzzle}.
     *
     * @param description human-readable description; if {@code null}, defaults to
     *                    {@code "No description available."}
     * @param name        puzzle name/title
     * @param hints       list of hint strings; if {@code null}, an empty list is used
     * @param solution    solution value of type {@code T}
     * @param isSolved    initial solved state
     * @param locked      whether the puzzle starts locked
     * @param item        item awarded upon solving (nullable)
     * @param givenItem   item required to interact/unlock (nullable)
     */

    public Puzzle(String description, String name, ArrayList<String> hints, T solution, boolean isSolved, boolean locked, Item item, Item givenItem) {
        setDescription(description);
        this.name = name;
        this.locked = locked;
        this.item = item;
        this.givenItem = givenItem;
        setHints(hints);
        this.solution = solution;
        setSolved(isSolved);
    }

     /**
     * Returns the canonical solution value.
     *
     * @return the solution of type {@code T}
     */

    public T getSolution(){
        return this.solution;
    }
     /**
     * Returns the item awarded upon solving, if any.
     *
     * @return the reward {@link Item}, or {@code null} if none
     */
    public Item getItem() {
        return this.item;
    }

     /**
     * Returns the item required to interact/unlock, if any.
     *
     * @return the required {@link Item}, or {@code null} if none
     */
    public Item getGivenItem() {
        return this.givenItem;
    }

   /**
     * Returns the subclass-defined type label.
     *
     * @return the puzzle type (e.g., "Math", "Logic", "Decipher")
     */
    public String getType(){
        return this.type;
    }
     /**
     * Returns the shift value (useful for cipher-based puzzles).
     *
     * @return the integer shift (default {@code 0} if unused)
     */
    public int getShift(){
        return this.shift;
    }

      /**
     * Returns the human-readable description.
     *
     * @return the description string (never {@code null})
     */
    public String getDescription() {
        return this.description;
    }

  
    /**
     * Returns the puzzle's name/title.
     *
     * @return the name string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of hint strings.
     *
     * @return a mutable {@link ArrayList} of hints (never {@code null})
     */
    public ArrayList<String> getHints() {
        return this.hints;
    }

    /**
     * Indicates whether the puzzle is currently locked.
     *
     * @return {@code true} if locked; {@code false} otherwise
     */
    public boolean isLocked() {
        return this.locked;
    }

    /*
    * looks to the puzzle class to define the attempt and type
    */
    public abstract void attempt(T param);

    /**
     * Marks the puzzle as solved.
     * <p>
     * Subclasses should invoke this when an attempt matches the solution.
     */
    public void solvePuzzle(){
    this.isSolved = true;
    }

    public void unlock() {
        this.locked = false;
    }

    /**
     * Sets the description, applying a null-safe default.
     *
     * @param description new description; if {@code null}, uses
     *                    {@code "No description available."}
     */
    public void setDescription(String description) {
        this.description = description != null ? description : "No description available.";
    }

    /**
     * Sets the reward item.
     *
     * @param i the item to award upon solving (nullable)
     */
    public void setItem(Item i) {
        this.item = i;
    }

    /**
     * Sets the required item.
     *
     * @param i the item required to interact/unlock (nullable)
     */
    public void setGivenItem(Item i) {
        this.givenItem = i;
    }

   /**
     * Sets the hints list, applying a null-safe default.
     *
     * @param hints list of hint strings; if {@code null}, an empty list is used
     */
    public void setHints(ArrayList<String> hints) {
        this.hints = hints != null ? hints : new ArrayList<>();
    }

    /**
    * Sets whether this puzzle has been solved.
     *
    * @param isSolved {@code true} if the puzzle is solved; 
    *                 {@code false} if it remains unsolved.
    */
    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

     /**
     * Returns whether this puzzle has been solved.
     *
     * @return {@code true} if solved; {@code false} otherwise
     */
    public boolean isSolved() {
        return this.isSolved;
    }

     /**
     * Returns a multi-line string with key puzzle details, including
     * name, description, solution, solved status, and hints.
     *
     * @return human-readable representation of this puzzle
     */
    @Override
    public String toString() {
        String puzzle = "Puzzle: "+this.name+"\nDescription: "+this.description+"\nSolution: "+this.solution+"\nSolved: "+this.isSolved+"\nHints:\n";
        for(int i=0; i<this.hints.size(); i++) {
            puzzle += this.hints.get(i)+"\n";
        }
        return puzzle;
    }
}
