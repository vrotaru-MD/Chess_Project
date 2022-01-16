package com.gui;

import com.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class ScreensController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<>();

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public void loadScreen(String name, String resource, Game game) {
        try {
            FXMLLoader screenLoader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
            Parent loadScreen = screenLoader.load();
            ControlledScreen controlledScreen = screenLoader.getController();
            controlledScreen.setScreenParent(this);
            controlledScreen.setGame(game);
            addScreen(name, loadScreen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setScreen(final String name) {
        if (screens.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, getScreen(name));
            } else {
                getChildren().add(getScreen(name));
            }
        }
    }
}
