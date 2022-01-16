package com.gui;

import com.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainScreenController implements ControlledScreen {
    ScreensController screensController;
    private Game game;

    @FXML
    private Text errorMessage;
    @FXML
    private TextField playerOne;
    @FXML
    private TextField playerTwo;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @Override
    public void init() {
        //
    }

    @FXML
    private void startGame(ActionEvent event) {
        String playerOneName = playerOne.getText();
        String playerTwoName = playerTwo.getText();

        if (playerOneName.length() > 0 && playerTwoName.length() > 0 && playerOneName.length() <= 12 && playerTwoName.length() <= 12 && !playerOneName.equals(playerTwoName)) {
            game.getWhitePlayer().setName(playerOneName);
            game.getBlackPlayer().setName(playerTwoName);

            screensController.setScreen("table");
            errorMessage.setText("");
        } else if (playerOneName.length() == 0 || playerTwoName.length() == 0) {
            errorMessage.setText("Please enter player names");
        } else if (playerOneName.length() > 12 || playerTwoName.length() > 12) {
            errorMessage.setText("Please enter a shorter name");
        } else {
            errorMessage.setText("Names must be different");
        }
    }
}
