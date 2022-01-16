package com.gui;

import com.game.Game;
import com.game.board.Tile;
import com.game.miscellaneous.Player;
import com.game.pieces.Piece;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TableScreenController implements ControlledScreen {
    ScreensController screensController;
    private Game game;

    public GridPane gridPane = new GridPane();

    @FXML
    Text turnLabel;

    @FXML
    Text turn;

    @FXML
    Text check;

    @FXML
    Pane turnPane;

    @FXML
    Button undoButton;

    @Override
    public void setGame(Game game) {
        this.game = game;
        init();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @Override
    public void init() {
        paint();
        turn.setText("WHITE PLAYER STARTS!");
    }

    /**
     * Paint board
     */
    private void paint() {
        turn.setText(game.getTurn().getName());
        if (game.isCheckMate()) {
            check.setText("CHECK");
        } else {
            check.setText("");
        }
        if (game.isGameOver()) {
            game.nextTurn();
            check.setText("Player " + game.getTurn().getName() + " wins.");
            game.endGame();
            check.setFill(Color.rgb(73, 156, 84));
            check.setLayoutX(33);
        }
        undoButton.setVisible(game.getBoardHistory().size() > 1);
        if (game.isGameEnded()) {
            turnLabel.setText("");
            turn.setFill(Color.rgb(255, 255, 255));
            turn.setText("");
            undoButton.setVisible(false);
            gridPane.setOpacity(0.4);
        } else {
            turnLabel.setText("Turn:");
        }
        if (game.getTurn().getColor().equals("white")) {
            turn.setFill(Color.rgb(0, 0, 0));
            turnPane.setStyle("-fx-background-color: white");
        } else {
            turn.setFill(Color.rgb(255, 255, 255));
            turnPane.setStyle("-fx-background-color: black");
        }
        gridPane.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile tile = game.getBoard().getTile(row, col);
                Pane pane = tile.getPane();
                int finalRow = row;
                int finalCol = col;
                pane.setOnMouseClicked(e -> {
                    if (!game.isGameEnded()) {
                        handleTileClick(finalCol, finalRow);
                    }
                });

                gridPane.add(pane, col, row);
            }
        }
    }

    /**
     * Set a true/false valid state for a list of destinations
     * @param destinations list of destinations
     * @param valid        true if a piece can be moved to destination
     */
    private void setDestinationsValid(ArrayList<int[]> destinations, boolean valid) {
        for (int[] destination : destinations) {
            int destinationRow = destination[0];
            int destinationCol = destination[1];
            Tile tile = game.getBoard().getTile(destinationRow, destinationCol);
            tile.setIsValidDestination(valid);
        }
    }

    /**
     * When clicking a tile, either set it as selected or move the selected piece
     * @param row clicked row
     * @param col clicked col
     */
    private void handleTileClick(int row, int col) {
        Player turnPlayer = game.getTurn();

        // Set current tile as selected
        Tile clickedTile = game.getBoard().getTile(col, row);
        Piece clickedPiece = clickedTile.getPiece();

        int[] originTilePosition = game.getSelectedTile();
        Tile originTile = game.getBoard().getTile(originTilePosition[0], originTilePosition[1]);

        if (game.getState().equals("SELECT_PIECE")) {
            if (clickedPiece != null && clickedPiece.getColor().equals(turnPlayer.getColor())) {
                originTile = clickedTile;
                originTilePosition[0] = col;
                originTilePosition[1] = row;
                originTile.setSelected(true);
                game.setSelectedTile(originTilePosition);
                game.setState("MOVE_PIECE");

                // Set valid destination tiles
                ArrayList<int[]> validDestinations = game.getBoard().getPossibleMoves(col, row);
                setDestinationsValid(validDestinations, true);
            }
        } else if (game.getState().equals("MOVE_PIECE")) {
            if (clickedTile.getIsValidDestination()) {
                // Move piece
                int[] destination = {col, row};
                ArrayList<int[]> validDestinations = game.getBoard().getPossibleMoves(originTilePosition[0], originTilePosition[1]);
                setDestinationsValid(validDestinations, false);
                game.movePiece(originTilePosition, destination);
            }
            originTile.setSelected(false);
            ArrayList<int[]> validDestinations = game.getBoard().getPossibleMoves(originTilePosition[0], originTilePosition[1]);
            setDestinationsValid(validDestinations, false);
            game.setState("SELECT_PIECE");
        }

        paint();
    }

    @FXML
    private void restartGame() {
        game.restartGame();
        paint();
        gridPane.setOpacity(1);
    }

    @FXML
    private void quitGame(ActionEvent event) {
        restartGame();
        screensController.setScreen("main");
    }

    @FXML
    private void undo() {
        game.undo();
        paint();
    }
}
