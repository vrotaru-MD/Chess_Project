package com.game.moves;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class StraightMove extends MoveType {

    /**
     * Get list of straight moves.
     * @param board game board
     * @param posX initial x position
     * @param posY initial y position
     * @return list of coordinates
     */
    public ArrayList<int[]> getPossibleMoves(Board board, int posX, int posY) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        Piece movingPiece = board.getPiece(posX, posY);

        // Temporary variables
        int x;
        int y;

        // up
        x = posX;
        y = posY - 1;
        while (y >= 0) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                y--;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
        }

        // right
        x = posX + 1;
        y = posY;
        while (x < 8) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x++;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
        }

        // down
        x = posX;
        y = posY + 1;
        while (y < 8) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                y++;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }

        }

        // left
        x = posX - 1;
        y = posY;
        while (x >= 0) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x--;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }
}
