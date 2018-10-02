package game;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Controller {
    public Label playerLabel;
    public GridPane gameGrid;
    public Button startGameButton;

    private boolean isXNext = true;
    private char[][] filledTiles = new char[3][3];    // TRUE == filled tile

    private static final String CLASS_HIDDEN = "hidden";
    private static final int START_ROW = 3;

    public void clickTile(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        int clickedButtonRow = GridPane.getRowIndex(clickedButton) - START_ROW;
        int clickedButtonColumn = GridPane.getColumnIndex(clickedButton);

        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText(isXNext ? "X" : "O");

            char newButtonText = clickedButton.getText().toCharArray()[0];

            fillTile(clickedButtonRow, clickedButtonColumn, newButtonText);

            if (checkForWin()) {
                System.out.println("Congratulations, Player " + newButtonText);
            } else {

                isXNext = !isXNext;

                setPlayerLabel();
            }
        }
    }

    private void fillTile(int x, int y, char symbol) {
        filledTiles[x][y] = symbol;
    }

    private boolean checkForWin() {
        System.out.println("Checking if one of the players has won...");

        return false;
    }

    public void startGame() {
        ArrayList<Button> gameTiles = new ArrayList<Button>();
        Object[] boardTiles = gameGrid.getChildren().toArray();

        startGameButton.setVisible(false);

        setPlayerLabel();

        // Get all game tiles
        for (Object tile: boardTiles) {
            if (tile.getClass().getSimpleName().matches("Button")) {
                gameTiles.add((Button) tile);
            }
        }

        // Unhide game tiles
        for (Button gameTile: gameTiles) {
            gameTile.getStyleClass().remove(CLASS_HIDDEN);
        }
    }

    private void setPlayerLabel() {
        playerLabel.setText("Player " + (isXNext ? "X" : "O") + "'s turn");
    }
}
