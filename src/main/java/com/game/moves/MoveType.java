package com.game.moves;

import com.game.board.Board;

import java.util.ArrayList;

public abstract class MoveType {

    /**
     * Get list of possible coordinates to move
     * @param board game board
     * @param posX initial x position
     * @param posY initial y position
     * @return list of coordinates
     */
    public abstract ArrayList<int[]> getPossibleMoves(Board board, int posX, int posY);
}
