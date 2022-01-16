package com.gui;

import com.game.Game;

public interface ControlledScreen {
    void setScreenParent(ScreensController screenPage);

    void setGame(Game game);

    void init();
}
