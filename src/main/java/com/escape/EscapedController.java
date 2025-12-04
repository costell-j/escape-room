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

    private String formatTime(int time) {
        String formattedTimer;
        int minutes = time/60;
        int seconds = time%60;
        String secondsFormatted;
        if(seconds < 10) {
            secondsFormatted = "0"+seconds;
        }
        else {
            secondsFormatted = ""+seconds+"";
        }

        formattedTimer = minutes+":"+secondsFormatted;

        return formattedTimer;
    }

    private int getTime(int time) {
        int res = switch(gm.getRoom().getDifficulty()) {
            case 1 -> 1200 - time;
            case 2 -> 900 - time;
            case 3 -> 600 - time;
            default -> 1200 - time;
        };

        return res;
    }

    @FXML
    private void displayInfo() throws IOException {
        String time = formatTime(getTime(gm.getRoom().getTimer()));
        finalScore_label.setText("Score: "+Double.toString(gm.getRoom().getFinalScore()));
        finishTime_label.setText("Completion Time: "+time);
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
