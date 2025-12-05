package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.application.Platform;
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
    @FXML private Pane room1_pane;
    @FXML private Pane room2_pane;
    @FXML private Pane room3_pane;
    @FXML private Pane room4_pane;
    @FXML private Pane room5_pane;
    @FXML private Pane room6_pane;
    @FXML private ImageView map_image;
    @FXML private ImageView leaderboard_image;
    @FXML private ImageView inventory_image;
    @FXML private ImageView settings_image;
    @FXML private ImageView profile_image;
    @FXML private Label timer_label;
    final Thread timerThread = new Thread(() -> {
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
    private void displayDoors() throws IOException {
        for(int i=0; i<panes.size(); i++) {
            Puzzle puzzle = puzzles.get(i);
            VBox vbox = new VBox();
            Image image = new Image(getClass().getResourceAsStream("/images/lock-icon-11.png"));
            if(!puzzle.isLocked()) {
                image = new Image(getClass().getResourceAsStream("/images/exclamation-mark-2.png"));
            }
            ImageView image_room = new ImageView(image);
            image_room.setFitWidth(50);
            image_room.setPreserveRatio(true);
            vbox.getChildren().add(image_room);
            panes.get(i).getChildren().add(vbox);

            image_room.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    try {
                        if(!puzzle.isLocked()) {
                            gm.setPuzzle(puzzle);
                            App.setRoot("puzzle");
                        } else {
                            ArrayList<Item> items = gm.getRoom().getProgress().getItems();
                            for(int i=0; i<items.size(); i++) {
                                if(puzzle.getItem().getName().equals(items.get(i).getName())) {
                                    gm.setPuzzle(puzzle);
                                    gm.getPuzzle().unlock();
                                    gm.getRoom().getProgress().getItems().remove(items.get(i));
                                    App.setRoot("puzzle");
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    public void setupImages() throws IOException {
        Image map = new Image(getClass().getResourceAsStream("/images/mapIcon.PNG"));
        Image profile = new Image(getClass().getResourceAsStream("/images/profileIcon.PNG"));
        Image leaderboard = new Image(getClass().getResourceAsStream("/images/leaderboardIcon.PNG"));
        Image inventory = new Image(getClass().getResourceAsStream("/images/chestIcon.PNG"));
        Image settings = new Image(getClass().getResourceAsStream("/images/settingsIcon.png"));
        
        map_image.setImage(map);
        inventory_image.setImage(inventory);
        settings_image.setImage(settings);
        profile_image.setImage(profile);
        leaderboard_image.setImage(leaderboard);

        map_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    gm.stopTimer();
                    App.setRoot("Map");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        leaderboard_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    gm.stopTimer();
                    App.setRoot("leaderboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        settings_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    gm.stopTimer();
                    App.setRoot("Settings");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        inventory_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    gm.stopTimer();
                    App.setRoot("inventory");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        profile_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    gm.stopTimer();
                    App.setRoot("Profile");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        gm.startTimer();
        timerThread.start();
        puzzles = gm.getRoom().getProgress().getPuzzlesSolved();
        panes = new ArrayList<>();
        panes.add(room1_pane);
        panes.add(room2_pane);
        panes.add(room3_pane);
        panes.add(room4_pane);
        panes.add(room5_pane);
        panes.add(room6_pane);
        try {
            displayDoors();
            setupImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
