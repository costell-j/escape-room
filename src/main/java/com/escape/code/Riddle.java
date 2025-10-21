package com.escape.code;
/*
 * Riddle 
 * @author Barbarnas
 */
import java.util.ArrayList;

/** iddle puzzle that checks answer vs. solution (case/space-insensitive). */
public class Riddle extends Puzzle<String> {

    public Riddle( String description,String name, ArrayList<String> hints, String solution, boolean isSolved) {
        super( description, name, hints, solution, isSolved);
        super.type = "Riddle";
    }

    
    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.solution.trim().toLowerCase();

            if(formattedAnswer.equals(formattedSolution)){
                super.solvePuzzle();
            }

    }
        
        
            /** Local fallback if your Puzzle doesn't expose equalsLoose */
    protected static boolean equalsLoose(String a, String b) {
        return norm(a).equals(norm(b));
    }

    private static String norm(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\h", " ").toLowerCase();
    }
}
