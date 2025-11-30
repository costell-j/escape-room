package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RoomProgressController implements Initializable {
    private GameManager gm;
    private Progress progress;
    @FXML private Label timeLeft_label;
    @FXML private Label hintsUsed_label;
    @FXML private Label puzzlesSolved_label;
    
    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @FXML
    private void displayInfo() throws IOException {
        timeLeft_label.setText("Time Left: "+gm.formatTimer());
        hintsUsed_label.setText("Hints Used: "+Integer.toString(progress.getCluesUsed()));
        puzzlesSolved_label.setText("Puzzles Solved: "+Integer.toString(progress.getPuzzlesSolved().size()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        progress = gm.getRoom().getProgress();
        try {
            displayInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
