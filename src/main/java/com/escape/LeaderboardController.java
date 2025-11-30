package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.escape.code.*;

public class LeaderboardController implements Initializable {
    private GameManager gm;
    private Leaderboard lb;

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        lb = gm.getLeaderboard();
    }
    
}
