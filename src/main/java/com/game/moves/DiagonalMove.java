package com.game.moves;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class DiagonalMove extends MoveType {

    /**
     * Get list of possible coordinates for diagonal moves
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

        // up-left
        x = posX - 1;
        y = posY - 1;
        while (x >= 0 && y >= 0) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x--;
                y--;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
        }

        // up-right
        x = posX - 1;
        y = posY + 1;
        while (x >= 0 && y <= 7) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x--;
                y++;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
        }

        // down-left
        x = posX + 1;
        y = posY - 1;
        while (x <= 7 && y >= 0) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x++;
                y--;
            } else if (!tile.getPiece().getColor().equals(movingPiece.getColor())) {
                possibleMoves.add(move);
                break;
            } else {
                break;
            }

        }

        // down-right
        x = posX + 1;
        y = posY + 1;
        while (x <= 7 && y <= 7) {
            Tile tile = board.getTile(x, y);
            int[] move = {x, y};
            if (tile.isEmpty()) {
                possibleMoves.add(move);
                x++;
                y++;
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
