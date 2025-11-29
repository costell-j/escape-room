package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PuzzleRoomController implements Initializable {
    private GameManager gm;
    private ArrayList<Puzzle> puzzles;
    private ArrayList<Pane> panes;
    private int count;
    @FXML private Pane room1_pane;
    @FXML private Pane room2_pane;
    @FXML private Pane room3_pane;
    @FXML private Pane room4_pane;

    @FXML
    private void displayDoors() throws IOException {
        for(int i=0; i<panes.size(); i++) {
            Puzzle puzzle = puzzles.get(i);
            VBox vbox = new VBox();
            Label puzzleName = new Label(puzzle.getName());
            puzzleName.setFont(new Font(14));
            puzzleName.setStyle("-fx-text-fill: DarkOrchid");
            vbox.getChildren().add(puzzleName);
            Image image = new Image(getClass().getResourceAsStream("/images/loadingScreen.png"));
            if(!puzzle.isLocked()) {
                image = new Image(getClass().getResourceAsStream("/images/loginScreen.png"));
            }
            ImageView image_room = new ImageView(image);
            image_room.setFitWidth(200);
            image_room.setPreserveRatio(true);
            vbox.getChildren().add(image_room);
            //vbox.getStyleClass().add("book-grid-item");
            panes.get(i).getChildren().add(vbox);

            image_room.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    try {
                        gm.setPuzzle(puzzle);
                        App.setRoot("puzzle");
                        count++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        count = 1;
        puzzles = gm.getRoom().getPuzzles();
        panes = new ArrayList<>();
        panes.add(room1_pane);
        panes.add(room2_pane);
        panes.add(room3_pane);
        panes.add(room4_pane);
        try {
            displayDoors();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
