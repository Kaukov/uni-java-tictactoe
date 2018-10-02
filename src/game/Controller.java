package game;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label testLabel;

    public void clickTile(ActionEvent actionEvent) {
        testLabel.setText("Tile clicked");
        System.out.println(actionEvent.toString());
    }
}
