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
import javafx.scene.text.Font;

public class InventoryController implements Initializable {
    private GameManager gm;
    private User user;
    @FXML private GridPane inventory_grid;

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @FXML
    private void populateInventory() throws IOException {
        ArrayList<Item> items = gm.getRoom().getProgress().getItems();
        for(int i=0; i<items.size(); i++) {
            Item item = items.get(i);
            VBox vbox = new VBox();
            Image image = new Image(getClass().getResourceAsStream("/images/keyIcon.png"));
            ImageView image_room = new ImageView(image);
            image_room.setFitWidth(100);
            image_room.setPreserveRatio(true);
            vbox.getChildren().add(image_room);
            //vbox.getStyleClass().add("book-grid-item");
            Label itemName = new Label(item.getName());
            itemName.setFont(new Font(14));
            itemName.setStyle("-fx-text-fill: DarkOrchid");
            vbox.getChildren().add(itemName);

            inventory_grid.add(vbox, i, 0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        user = gm.getUser();
        try {
            populateInventory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
