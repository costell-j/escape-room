package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;
import com.escape.code.Leaderboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class InventoryController implements Initializable {
    private GameManager gm;
    private User user;

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        user = gm.getUser();
    }
}
