package com.escape;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("landing");
    }
}