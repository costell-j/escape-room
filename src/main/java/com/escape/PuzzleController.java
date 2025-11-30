package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PuzzleController implements Initializable {
    private GameManager gm;
    private Puzzle puzzle;
    private ArrayList<String> hints;
    private int hintNum;
    @FXML private Label question_label;
    @FXML private TextField answer_textfield;
    @FXML private Label hint_label;
    @FXML private Label earnedItem_label;
    @FXML private ImageView item_image;
    @FXML private Label timer_label;
    Thread timerThread = new Thread(() -> {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            final String time = gm.formatTimer();
            Platform.runLater(() -> {
                timer_label.setText(time);
            });
        }
    });

    @FXML
    public void setup() throws IOException {
        question_label.setText(puzzle.getDescription());
        earnedItem_label.setText(puzzle.getName());
    }

    @FXML
    public void btnSubmitClicked() throws IOException {
        String answer = answer_textfield.getText();
        if(!gm.attemptPuzzle(puzzle, answer)) {
            earnedItem_label.setText("Wrong Answer");
            return;
        } else {
            if(puzzle.getGivenItem().getName().equals("none")) {
                earnedItem_label.setText("CORRECT!!");
            } else {
                earnedItem_label.setText("CORRECT!! You Got: "+puzzle.getGivenItem().getName());
            }
        }
    }

    @FXML
    private void btnExitClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @FXML
    private void btnHintClicked() throws IOException {
        if(hintNum < hints.size()) {
            earnedItem_label.setText(hints.get(hintNum));
            hintNum++;
        } else {
            earnedItem_label.setText("No hints left");
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        timerThread.start();
        puzzle = gm.getPuzzle();
        hints = puzzle.getHints();
        hintNum = 0;
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
