package game;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Controller {
    public Label testLabel;
    public GridPane gameGrid;
    public Button startGameButton;
    private boolean isXNext = true;
    private boolean[][] filledTiles = new boolean[3][3];    // TRUE == filled tile
    private static final String CLASS_HIDDEN = "hidden";

    public void clickTile(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText(isXNext ? "X" : "O");
            isXNext = !isXNext;
        }
        testLabel.setText("Tile clicked");
    }

    private void initTiles() {
        // Clear all tiles
        for (boolean[] row: filledTiles) {
            Arrays.fill(row, Boolean.FALSE);
        }
    }

    public void startGame() {
        Button[] gameTiles = new Button[9];
        Object[] boardTiles = gameGrid.getChildren().toArray();

        startGameButton.setVisible(false);

        initTiles();
    }
}
