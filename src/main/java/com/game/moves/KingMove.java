package com.game.moves;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.miscellaneous.CheckMate;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class KingMove extends MoveType {
    /**
     * Get list of king moves
     *
     * @param board game board
     * @param posX  initial x position
     * @param posY  initial y position
     * @return list of coordinates
     */
    public ArrayList<int[]> getPossibleMoves(Board board, int posX, int posY) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        CheckMate checkMate = new CheckMate();

        Piece movingPiece = board.getPiece(posX, posY);

        // Temporary variables
        int x;
        int y;

        // Moves to check
        int[][] moves = new int[10][2]; //added 2 new moves for castling
        int[] move0 = {-1, 0};
        int[] move1 = {-1, 1};
        int[] move2 = {0, 1};
        int[] move3 = {1, 1};
        int[] move4 = {1, 0};
        int[] move5 = {1, -1};
        int[] move6 = {0, -1};
        int[] move7 = {-1, -1};
        int[] move8 = {0, 2}; //castling right
        int[] move9 = {0, -2}; //castling left
        moves[0] = move0;
        moves[1] = move1;
        moves[2] = move2;
        moves[3] = move3;
        moves[4] = move4;
        moves[5] = move5;
        moves[6] = move6;
        moves[7] = move7;

        //variables
        boolean kingHasNotBeenMoved = movingPiece.isPieceMoved();
        String pieceColor = movingPiece.getColor();

        //left side
        Tile rook_00 = board.getTile(0, 0);
        Tile rook_07 = board.getTile(0, 7);
        boolean tile_01 = board.getTile(0,1).isEmpty(); // check if tiles are empty
        boolean tile_02 = board.getTile(0,2).isEmpty();
        boolean tile_03 = board.getTile(0,3).isEmpty();
        boolean tile_05 = board.getTile(0,5).isEmpty();
        boolean tile_06 = board.getTile(0,6).isEmpty();

        //right side
        Tile rook_70 = board.getTile(7, 0);
        Tile rook_77 = board.getTile(7, 7);
        boolean tile_71 = board.getTile(7,1).isEmpty();
        boolean tile_72 = board.getTile(7,2).isEmpty();
        boolean tile_73 = board.getTile(7,3).isEmpty();
        boolean tile_75 = board.getTile(7,5).isEmpty();
        boolean tile_76 = board.getTile(7,6).isEmpty();

        //add castling moves if the piece has not been moved

        if (!kingHasNotBeenMoved && !checkMate.checkMate(board, movingPiece.getColor())) {
            //castling left black player
            if (!rook_00.isEmpty()) {
                if (!rook_00.getPiece().isPieceMoved() && pieceColor.equals("black")
                        && tile_01 && tile_02 && tile_03) moves[9] = move9;
            }
            //castling right black player
            if (!rook_07.isEmpty()) {
                if (!rook_07.getPiece().isPieceMoved() && pieceColor.equals("black")
                && tile_05 && tile_06) moves[8] = move8;
            }
            //castling left white player
            if (!rook_70.isEmpty()) {
                if (!rook_70.getPiece().isPieceMoved() && pieceColor.equals("white")
                && tile_71 && tile_72 && tile_73) moves[9] = move9;
            }
            //castling right white player
            if (!rook_77.isEmpty()) {
                if (!rook_77.getPiece().isPieceMoved() && pieceColor.equals("white")
                && tile_75 && tile_76) moves[8] = move8;
            }
        }

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
