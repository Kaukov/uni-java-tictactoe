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
    private boolean isGameOver = false;
    private char[][] filledTiles = new char[3][3];

    private static final String CLASS_HIDDEN = "hidden";
    private static final int START_ROW = 3;

    public void clickTile(ActionEvent actionEvent) {
        if (!isGameOver) {
            Button clickedButton = (Button) actionEvent.getSource();
            int clickedButtonRow = GridPane.getRowIndex(clickedButton) - START_ROW;
            int clickedButtonColumn = GridPane.getColumnIndex(clickedButton);

            if (clickedButton.getText().isEmpty()) {
                clickedButton.setText(isXNext ? "X" : "O");

                char newButtonText = clickedButton.getText().toCharArray()[0];

                fillTile(clickedButtonRow, clickedButtonColumn, newButtonText);

                if (checkForWin(newButtonText)) {
                    isGameOver = true;

                    setPlayerLabel("Player " + newButtonText + " wins!");
                } else if (checkForDraw()) {
                    isGameOver = true;

                    setPlayerLabel("DRAW");
                } else {
                    isXNext = !isXNext;

                    setPlayerLabel();
                }
            }
        }
    }

    private void fillTile(int x, int y, char symbol) {
        filledTiles[x][y] = symbol;
    }

    private boolean checkForWin(char playerSymbol) {
        boolean topRowCondition =
            filledTiles[0][0] == playerSymbol &&
            filledTiles[0][1] == playerSymbol &&
            filledTiles[0][2] == playerSymbol
        ;

        boolean middleRowCondition =
            filledTiles[1][0] == playerSymbol &&
            filledTiles[1][1] == playerSymbol &&
            filledTiles[1][2] == playerSymbol
        ;

        boolean bottomRowCondition =
            filledTiles[2][0] == playerSymbol &&
            filledTiles[2][1] == playerSymbol &&
            filledTiles[2][2] == playerSymbol
        ;

        boolean leftDiagonalCondition =
            filledTiles[0][0] == playerSymbol &&
            filledTiles[1][1] == playerSymbol &&
            filledTiles[2][2] == playerSymbol
        ;

        boolean rightDiagonalCondition =
            filledTiles[0][2] == playerSymbol &&
            filledTiles[1][1] == playerSymbol &&
            filledTiles[2][0] == playerSymbol
        ;


        return
            topRowCondition ||
            middleRowCondition ||
            bottomRowCondition ||
            leftDiagonalCondition ||
            rightDiagonalCondition
        ;

    }

    private boolean checkForDraw() {
        for (char[] row: filledTiles) {
            for (char symbol: row) {
                if (symbol == 0) return false;
            }
        }

        return true;
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

    private void setPlayerLabel(String text) {
        playerLabel.setText(text);
    }
}
