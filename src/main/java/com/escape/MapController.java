package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MapController implements Initializable {
    private GameManager gm;
    

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
    }
}
