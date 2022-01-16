package com.game.moves;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class KnightMove extends MoveType {

    /**
     * Get list of knight moves.
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

        // Moves to check
        int[][] moves = new int[8][2];
        int[] move0 = {-2, 1};
        int[] move1 = {-1, 2};
        int[] move2 = {1, 2};
        int[] move3 = {2, 1};
        int[] move4 = {-2, -1};
        int[] move5 = {-1, -2};
        int[] move6 = {1, -2};
        int[] move7 = {2, -1};
        moves[0] = move0;
        moves[1] = move1;
        moves[2] = move2;
        moves[3] = move3;
        moves[4] = move4;
        moves[5] = move5;
        moves[6] = move6;
        moves[7] = move7;

        // Check each move
        for (int[] temp : moves) {
            x = posX + temp[0];
            y = posY + temp[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Tile tile = board.getTile(x, y);
                int[] move = {x, y};
                if (tile.isEmpty() || !tile.getPiece().getColor().equals(movingPiece.getColor())) {
                    possibleMoves.add(move);
                }
            }
        }

        return possibleMoves;
    }
}
