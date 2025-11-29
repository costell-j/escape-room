package com.escape;

import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.fxml.Initializable;

public class PuzzleController implements Initializable {
    private GameManager gm;
    private Puzzle puzzle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        puzzle = gm.getPuzzle();
    }
}
