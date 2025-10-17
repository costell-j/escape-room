package com.escape.code;
/*
 * math puzzle 
 * @author Barbarnas
 */
import java.util.ArrayList;
/*
 * math puzzle that checks user answer as a double
 */
public class MathPuzzle extends Puzzle {
/*
 * tolerance for a floating point comparison and EPS(Epsilon) allows for small diffence in answers
 */
    private static final double EPS = 1e-6; 

    public MathPuzzle (String description, ArrayList<String> hints, String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }
  
   
     public boolean attempt(String answer) {
        if (answer == null) return false;
/*
 * used to convert from strings to real numbers and trim them to takr spaces away
 */
        try {
            double correctValue = Double.parseDouble(getSolution().trim());
            double userValue = Double.parseDouble(answer.trim());

            if (Math.abs(correctValue - userValue) <= EPS) {
                isSolved = true;
                return true;
            }
            else{

                double diffrence = correctValue - userValue;
                    System.out.println("Your off by this much" +diffrence);

            }
            /*
             * if the parsing fail as in a non number is entered it will automatically fail.
             */
        } 
        
        catch (NumberFormatException e) {
           
            return false;
        }

        return false;
    }
}

