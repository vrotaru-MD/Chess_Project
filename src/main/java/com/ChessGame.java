package com;

import com.game.Game;
import com.game.board.Board;
import com.game.miscellaneous.Player;

import com.gui.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChessGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        Player whitePlayer = new Player("white");
        Player blackPlayer = new Player("black");

        Board board = new Board();

        Game game = new Game(board, whitePlayer, blackPlayer);

        primaryStage.setTitle("Chess Game");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("img/king-black.png"));

        String mainScreenId = "main";
        String mainScreenFile = "gui/MainScreen.fxml";
        String tableScreenId = "table";
        String tableScreenFile = "gui/TableScreen.fxml";

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(mainScreenId, mainScreenFile, game);
        mainContainer.loadScreen(tableScreenId, tableScreenFile, game);

        mainContainer.setScreen(mainScreenId);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void play() {
        ChessGame.launch();
    }
}