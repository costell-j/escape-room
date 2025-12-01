package com.escape;

import java.io.IOException;

import com.escape.code.*;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

public class ChooseDifficulty extends Dialog {

    @FXML private VBox vbox;
    @FXML private Label header;
    @FXML private Button easy;
    @FXML private Button medium;
    @FXML private Button hard;
    private GameManager gm = GameManager.getInstance(); 

    public ChooseDifficulty() {
        buildUI();
    }

    @FXML
    private void buildUI() {
        vbox = new VBox();
        header = new Label("Choose Your Difficulty");
        addDifficultyButton("easy", easy);
        addDifficultyButton("medium", medium);
        addDifficultyButton("hard", hard);

        getDialogPane().setContent(vbox);
    }

    @FXML
    private void addDifficultyButton(String name, Button b) {
        b = new Button(name);
        vbox.getChildren().add(b);
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                switch(name) {
                    case "easy" -> gm.setDifficulty(1);
                    case "medium" -> gm.setDifficulty(2);
                    case "hard" -> gm.setDifficulty(3);
                    default -> gm.setDifficulty(1);
                }
                try {
                    App.setRoot("Dialog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
