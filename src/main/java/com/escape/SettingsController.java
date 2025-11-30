package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.GameManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

public class SettingsController implements Initializable {
    @FXML private Slider volume_slider; 
    private GameManager gm;

    @FXML
    private void btnEasyClicked() throws IOException {
        gm.setDifficulty(1);
    }

    @FXML
    private void btnMediumClicked() throws IOException {
        gm.setDifficulty(2);
    }

    @FXML
    private void btnHardClicked() throws IOException {
        gm.setDifficulty(3);
    }

    @FXML
    private void btnLogoutClicked() throws IOException {
        gm.logout();
        System.exit(0);
    }

    @FXML
    private void btnBackClicked() throws IOException {
        App.setRoot("puzzles");
        gm.startTimer();
    }

    @FXML
    private void changeVolume() throws IOException {
        int volume;
        if(volume_slider == null) {
            volume = 0;
        } else {
            volume = (int)volume_slider.getValue();
        }
        gm.setVolume(volume);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
    }
    
}
