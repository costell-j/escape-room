package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class EscapedController implements Initializable {
    private GameManager gm;
    private Progress progress;
    @FXML private Label finalScore_label;
    @FXML private Label finishTime_label;
    @FXML private Label hintsUsed_label;
    @FXML private Label difficulty_label;

    @FXML
    private void displayInfo() throws IOException {
        finalScore_label.setText("Score: "+Double.toString(gm.getRoom().getFinalScore()));
        finishTime_label.setText("Completion Time: "+progress.getFormattedCompletionTime());
        hintsUsed_label.setText("Hints Used: "+Integer.toString(progress.getCluesUsed()));
        difficulty_label.setText("Difficulty: "+Integer.toString(gm.getRoom().getDifficulty()));
    }

    @FXML
    private void btnExitClicked() throws IOException {
        if(!gm.getUser().getUsername().equals("guest")) {
            gm.logout();
        }
        gm.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        progress = gm.getRoom().getProgress();
        try {
            displayInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
