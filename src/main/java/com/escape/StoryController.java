package com.escape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.escape.code.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class StoryController implements Initializable {
    private GameManager gm;
    private Room room;
    private int current_slide = 0;
    @FXML private TextArea story_lines;

    @FXML
    private void advanceStory() throws IOException {
        if(current_slide < room.getStory().size()-1) {
            story_lines.setText(room.getStory().get(current_slide).getDescription());
            current_slide++;
        } else {
            App.setRoot("puzzles");
            gm.startTimer();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gm = GameManager.getInstance();
        room = gm.getRoom();
        story_lines.setText(room.getStory().get(current_slide).getDescription());
        current_slide++;
    }
}
