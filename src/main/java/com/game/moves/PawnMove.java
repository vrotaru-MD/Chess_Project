package com.game.moves;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class PawnMove extends MoveType {

    /**
     * Get list of possible coordinates for the pawn to move
     * @param board game board
     * @param posX initial x position
     * @param posY initial y position
     * @return list of coordinates
     */
    public ArrayList<int[]> getPossibleMoves(Board board, int posX, int posY) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        Piece movingPiece = board.getPiece(posX, posY);

        String color = movingPiece.getColor();
        String oppositeColor;
        if (color.equals("white")) {
            oppositeColor = "black";
        } else {
            oppositeColor = "white";
        }

        // Temporary variables
        int x;
        int y;
        Tile nextTile;

        // Move 1 forward
        int[] move0 = new int[2];
        if (color.equals("white")) {
            move0[0] = -1;
        } else {
            move0[0] = 1;
        }
        // Check if space to move is empty
        x = posX + move0[0];
        y = posY + move0[1];
        if (x > 0 && x < 7) {
            nextTile = board.getTile(x, y);
            if (nextTile.isEmpty()) {
                int[] move = {x, y};
                possibleMoves.add(move);
            }
        }

        // Move 2 forward on first move
        int[] move1 = new int[2];
        if (color.equals("white")) {
            move1[0] = -2;
            x = posX + move1[0];
            y = posY + move1[1];
            if (posX == 6) {
                nextTile = board.getTile(x, y);
                if (nextTile.isEmpty()) {
                    int[] move = {x, y};
                    possibleMoves.add(move);
                }
            }
        } else {
            move1[0] = 2;
            x = posX + move1[0];
            y = posY + move1[1];
            if (posX == 1) {
                nextTile = board.getTile(x, y);
                if (nextTile.isEmpty()) {
                    int[] move = {x, y};
                    possibleMoves.add(move);
                }
            }
        }

        // Move 1 forward diagonally if pawn can kill some piece
        int [] move2 = new int[2];
        int [] move3 = new int[2];
        if (color.equals("white")) {
            move2[0] = -1;
            move2[1] = -1;
            move3[0] = 1;
            move3[1] = -1;
        } else {
            move2[0] = -1;
            move2[1] = 1;
            move3[0] = 1;
            move3[1] = 1;
        }

        int[][] moves = {move2, move3};

        // Check each move and add if there is an opposite piece
        for (int[] temp : moves) {
            x = posX + temp[1];
            y = posY + temp[0];
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
                nextTile = board.getTile(x, y);
                int[] move = {x, y};
                if (!nextTile.isEmpty() && nextTile.getPiece().getColor().equals(oppositeColor)) {
                    possibleMoves.add(move);
                }
            }
        }

        return possibleMoves;
    }
}
