package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.escape.code.*;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileController implements Initializable {
    private GameManager gm;
    private User user;
    @FXML private TextField username_txt;
    @FXML private TextField difficulty_txt;
    @FXML private TextField currentroom_txt;

    @FXML
    private void displayInfo() throws IOException {
        username_txt.setText(user.getUsername());
        difficulty_txt.setText(Integer.toString(gm.getRoom().getDifficulty()));
        currentroom_txt.setText(gm.getUser().getCurrentRoom().getName());
        currentroom_txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("roomprogress");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        user = gm.getUser();
        try {
            displayInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
