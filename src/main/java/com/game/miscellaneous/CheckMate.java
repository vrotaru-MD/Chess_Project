package com.game.miscellaneous;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.pieces.Piece;


import java.util.ArrayList;

/**
 * Check if king has check or checkMate
 */
public class CheckMate {
    ArrayList<int[]> allWhitePieces;
    ArrayList<int[]> allBlackPieces;
    int[] kingWhite;
    int[] kingBlack;


    /**
     * Check for checkmate
     *
     * @return return true if king is checked
     */
    public boolean checkMate(Board board, String playerColor) {
        kingWhite = new int[2];
        kingBlack = new int[2];
        allWhitePieces = new ArrayList<>();
        allBlackPieces = new ArrayList<>();

        getAllPiecesFromBoard(board);


        //return true if black player has check
        if (playerColor.equals("black")) {
            for (int[] xY : allWhitePieces) {
                ArrayList<int[]> allWhiteMoves = board.getPossibleMoves(xY[0], xY[1]);
                for (int[] moves : allWhiteMoves) {
                    if (moves[0] == kingBlack[0] && moves[1] == kingBlack[1]) {
                        return true;
                    }
                }
            }
        }

        //return true if white player has check
        if (playerColor.equals("white")) {
            for (int[] xY : allBlackPieces) {
                ArrayList<int[]> allBlackMoves = board.getPossibleMoves(xY[0], xY[1]);
                for (int[] moves : allBlackMoves) {
                    if (moves[0] == kingWhite[0] && moves[1] == kingWhite[1]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Check if player is left with valid moves
     *
     * @param board       current board
     * @param playerColor player color
     * @return true if there are no valid moves
     */
    public boolean gameOver(Board board, String playerColor) {
        ArrayList<Integer> hasValidMoveBlack = new ArrayList<>();
        ArrayList<Integer> hasValidMoveWhite = new ArrayList<>();

        Board newBoard; //make all moves in a copy of board

        kingWhite = new int[2];
        kingBlack = new int[2];
        allWhitePieces = new ArrayList<>();
        allBlackPieces = new ArrayList<>();

        getAllPiecesFromBoard(board);

        //return true if all black player moves will have check
        if (playerColor.equals("black")) {
            for (int[] xY : allBlackPieces) {

                ArrayList<int[]> allBlackMoves = board.getPossibleMoves(xY[0], xY[1]);
                Piece currentPiece = board.getTile(xY[0], xY[1]).getPiece();


                for (int[] moves : allBlackMoves) {
                    newBoard = new Board(board); //clear the board;
                    newBoard.getTile(moves[0], moves[1]).setPiece(currentPiece);
                    newBoard.getTile(xY[0], xY[1]).setPiece(null);

                    if (checkMate(newBoard, "black")) {
                        hasValidMoveBlack.add(0);
                    } else {
                        hasValidMoveBlack.add(1);
                    }
                }
            }
            return !hasValidMoveBlack.contains(1);
        }

        //return true if all white player moves will have check
        if (playerColor.equals("white")) {
            for (int[] xY : allWhitePieces) {

                ArrayList<int[]> allWhiteMoves = board.getPossibleMoves(xY[0], xY[1]);
                Piece currentPiece = board.getTile(xY[0], xY[1]).getPiece();


                for (int[] moves : allWhiteMoves) {
                    newBoard = new Board(board); //clear the board
                    newBoard.getTile(moves[0], moves[1]).setPiece(currentPiece);
                    newBoard.getTile(xY[0], xY[1]).setPiece(null);

                    if (checkMate(newBoard, "white")) {
                        hasValidMoveWhite.add(0);
                    } else {
                        hasValidMoveWhite.add(1);
                    }
                }
            }
            return !hasValidMoveWhite.contains(1);
        }
        return false;
    }

    /**
     * Scans all tiles for chess pieces.
     *
     * @param board current board
     */
    private void getAllPiecesFromBoard(Board board) {
        for (int posX = 0; posX < 8; posX++) {
            for (int posY = 0; posY < 8; posY++) {

                Tile tile = board.getTile(posX, posY);
                int[] coordinates = {posX, posY};


                //scan board for black pieces and get their coordinates
                if (!tile.isEmpty()) {
                    if (tile.getPiece().getColor().equals("black")) {
                        if (tile.getPiece().getType().equals("king")) {
                            kingBlack[0] = posX;
                            kingBlack[1] = posY;
                        } else {
                            allBlackPieces.add(coordinates);
                        }
                    }

                    //scan board for white pieces and get their coordinates
                    if (tile.getPiece().getColor().equals("white")) {
                        if (tile.getPiece().getType().equals("king")) {
                            kingWhite[0] = posX;
                            kingWhite[1] = posY;
                        } else {
                            allWhitePieces.add(coordinates);
                        }
                    }
                }
            }
        }
    }
}
