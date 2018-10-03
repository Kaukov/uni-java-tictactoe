package game;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller {
    public Label playerLabel;
    public GridPane gameGrid;
    public Button startGameButton;
    public Button newGameButton;

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

                if (checkForWin(newButtonText)) { endGame("Player " + newButtonText + " wins!"); }
                else if (checkForDraw()) { endGame("DRAW"); }
                else {
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

        boolean leftColumnCondition =
            filledTiles[0][0] == playerSymbol &&
            filledTiles[1][0] == playerSymbol &&
            filledTiles[2][0] == playerSymbol
        ;

        boolean middleColumnCondition =
            filledTiles[0][1] == playerSymbol &&
            filledTiles[1][1] == playerSymbol &&
            filledTiles[2][1] == playerSymbol
        ;

        boolean rightColumnCondition =
            filledTiles[0][2] == playerSymbol &&
            filledTiles[1][2] == playerSymbol &&
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
            leftColumnCondition ||
            middleColumnCondition ||
            rightColumnCondition ||
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

    private boolean isButton(Object object) {
        return object.getClass().getSimpleName().matches("Button");
    }

    public void startGame() {
        Object[] boardTiles = gameGrid.getChildren().toArray();

        startGameButton.getStyleClass().add(CLASS_HIDDEN);

        setPlayerLabel();

        // Get all game tiles and unhide them
        for (Object tile: boardTiles) {
            if (isButton(tile)) {
                Button gameTile = (Button) tile;
                boolean isGameTile = gameTile.getStyleClass().contains("gameTile");

                if (isGameTile) { gameTile.getStyleClass().remove(CLASS_HIDDEN); }
            }
        }
    }

    public void resetGame() {
        isXNext = true;
        isGameOver = false;

        newGameButton.getStyleClass().add(CLASS_HIDDEN);
    }

    public void restartGame(ActionEvent actionEvent) {
        Object[] boardTiles = gameGrid.getChildren().toArray();

        isXNext = true;
        isGameOver = false;

        // Clear filled tiles array
        for (char[] tileRow: filledTiles) {
            Arrays.fill(tileRow, Character.MIN_VALUE);
        }

        // Clear filled tiles
        for (Object tile: boardTiles) {
            if (isButton(tile)) {
                Button gameTile = (Button) tile;
                boolean isGameTile = gameTile.getStyleClass().contains("gameTile");

                if (isGameTile) { gameTile.setText(""); }
            }
        }

        newGameButton.getStyleClass().add(CLASS_HIDDEN);
    }

    private void endGame(String labelText) {
        isGameOver = true;
        setPlayerLabel(labelText);
        newGameButton.getStyleClass().remove(CLASS_HIDDEN);
    }

    private void setPlayerLabel() { playerLabel.setText("Player " + (isXNext ? "X" : "O") + "'s turn"); }

    private void setPlayerLabel(String text) { playerLabel.setText(text); }
}
