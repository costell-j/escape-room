package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.escape.code.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * @author Costell Johnson
 */
public class LoginController implements Initializable {
    @FXML
    private TextField username_textfield;
    @FXML
    private PasswordField password_textfield;
    @FXML
    private Label lbl_error;
    private GameManager gm;

    @FXML
    private void btnLoginClicked() throws IOException {
        String username = username_textfield.getText();
        String password = password_textfield.getText();

        if (!gm.login(username, password)) {
            lbl_error.setText("Invalid username or password.");
            return;
        }
    
        App.setRoot("roomlist");
    }

    @FXML
    private void btnCreateAccountClicked() throws IOException {
        App.setRoot("CreateAcoount");
    }

    @FXML
    private void btnGuestClicked() throws IOException {
        gm.createGuest();
        App.setRoot("roomlist");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = new GameManager();
    }

}