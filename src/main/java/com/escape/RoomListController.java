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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class RoomListController implements Initializable {
    @FXML private Label chooseroom_label;
    @FXML private GridPane roomlist_grid;
    private GameManager gm;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        user = gm.getUser();
        displayRooms();
    }

    private void displayRooms() {
        ArrayList<Room> rooms = gm.getRoomList();
        for(int i=0; i<rooms.size(); i++) {
            Room room = rooms.get(i);
            VBox vbox = new VBox();
            Label roomName = new Label(room.getName());
            roomName.setFont(new Font(14));
            roomName.setStyle("-fx-text-fill: DarkOrchid");
            vbox.getChildren().add(roomName);
            Image image = new Image(getClass().getResourceAsStream("/images/loadingScreen.png"));
            ImageView image_room = new ImageView(image);
            image_room.setFitWidth(200);
            image_room.setPreserveRatio(true);
            vbox.getChildren().add(image_room);
            //vbox.getStyleClass().add("book-grid-item");

            roomlist_grid.add(vbox, i, 0);

            image_room.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    try {
                        gm.chooseRoom(room);
                        gm.getRoom().setProgress(user.getUsername());
                        gm.getUser().setCurrentRoom(room);
                        if(gm.getRoom().getProgress().isPlay()) {
                            App.setRoot("puzzles");
                        } else {
                            ChooseDifficulty cd = new ChooseDifficulty();
                            cd.showAndWait();
                            gm.getRoom().getProgress().setPlay(true);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
