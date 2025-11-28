package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.GameManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController implements Initializable {
    private GameManager gm;
    @FXML private TextField username_txt;
    @FXML private PasswordField password_txt;

    @FXML
    private void btnCreateAccountClicked() throws IOException {
        String username = username_txt.getText();
        String password = password_txt.getText();

        if (!gm.createAccount(username, password)) {
            return;
        }
    
        App.setRoot("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = new GameManager();
    }

    
}
