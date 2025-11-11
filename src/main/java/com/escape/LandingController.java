package com.escape;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class LandingController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
