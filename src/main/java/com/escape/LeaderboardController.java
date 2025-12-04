package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

import com.escape.code.*;

public class LeaderboardController implements Initializable {
    private GameManager gm;
    private Leaderboard lb;
    HashMap<Integer, User> users;
    @FXML private GridPane leaderboard_grid;

    private void addUserToLeaderboard() {
        int count = 1;
        User us = new User();
        for(User user : users.values()) {
            if(user.getScore() < gm.getUser().getScore()) {
                users.remove(count);
                users.put(count, gm.getUser());
                us = user;
                break;
            }
            count++;
        }
        for(int i=count+1; i<users.size(); i++) {
            users.put(i, us);
            User u = users.get(i+1);
            users.remove(i+1);
            users.put(i+1, u);
        }
    }

    @FXML
    private void displayLeaderboard() throws IOException {
        int count = 1;
        for(User user : users.values()) {
            HBox hbox = new HBox();
            hbox.setMinWidth(600);
            hbox.getStyleClass().add("leaderboard-row");
            Label position = new Label(Integer.toString(count));
            position.getStyleClass().add("lb-position");
            position.setFont(new Font(14));
            hbox.getChildren().add(position);
            HBox.setHgrow(position, Priority.ALWAYS);
            position.setPrefWidth(300);

            Label name = new Label(user.getUsername());
            name.setFont(new Font(14));
            name.getStyleClass().add("lb-name");
            hbox.getChildren().add(name);
            HBox.setHgrow(name, Priority.ALWAYS);
            name.setPrefWidth(300);

            Label score = new Label(Double.toString(user.getScore()));
            score.setFont(new Font(14));
            score.getStyleClass().add("lb-score");
            hbox.getChildren().add(score);
            HBox.setHgrow(score, Priority.ALWAYS);
            score.setPrefWidth(300);
            
            leaderboard_grid.add(hbox, 0, count-1);
            count++;
        }
    }

    @FXML
    private void btnCloseClicked() throws IOException {
        App.setRoot("puzzles");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        lb = gm.getLeaderboard();
        users = lb.getPlayers();
        addUserToLeaderboard();
        try {
            displayLeaderboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
