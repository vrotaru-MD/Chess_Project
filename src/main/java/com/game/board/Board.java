package com.game.board;

import com.game.moves.MoveType;
import com.game.pieces.*;


import java.util.ArrayList;

/**
 * An implementation of chess board class
 */
public class Board implements Cloneable {
    public Tile[][] board = new Tile[8][8];

    // Constructor
    public Board() {
        // Create board tiles
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int rest = (row + col) % 2;
                if (rest % 2 == 1) {
                    board[row][col] = new Tile("light");
                } else {
                    board[row][col] = new Tile("dark");
                }
            }
        }
        populateBoard();
    }

    /**
     * Clones board
     * @param other board to clone
     */
    public Board(Board other) {
        this.board = new Tile[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece prevPiece = other.getPiece(col, row);
                Piece nextPiece = null;
                if (prevPiece != null) {
                    String pieceType = prevPiece.getType();
                    if ("bishop".equals(pieceType)) {
                        nextPiece = new Bishop(prevPiece);
                    } else if ("king".equals(pieceType)) {
                        nextPiece = new King(prevPiece);
                    } else if ("knight".equals(pieceType)) {
                        nextPiece = new Knight(prevPiece);
                    } else if ("pawn".equals(pieceType)) {
                        nextPiece = new Pawn(prevPiece);
                    } else if ("queen".equals(pieceType)) {
                        nextPiece = new Queen(prevPiece);
                    } else if ("rook".equals(pieceType)) {
                        nextPiece = new Rook(prevPiece);
                    }
                }

                Tile nextTile = new Tile(other.getTile(row, col).getType());
                nextTile.setPiece(nextPiece);
                this.board[row][col] = nextTile;
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Get current board.
     *
     * @return current board
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Gets tile in a given position
     *
     * @param posX row?
     * @param posY col?
     * @return Tile
     */
    public Tile getTile(int posX, int posY) {
        return board[posY][posX];
    }

    /**
     * Gets piece in a given position
     *
     * @param posX row?
     * @param posY col?
     * @return Piece
     */
    public Piece getPiece(int posX, int posY) {
        return getTile(posX, posY).getPiece();
    }

    /**
     * Populate current board with pieces.
     */
    public void populateBoard() {
        getTile(0, 0).setPiece(new Rook("black"));
        getTile(0, 1).setPiece(new Knight("black"));
        getTile(0, 2).setPiece(new Bishop("black"));
        getTile(0, 3).setPiece(new Queen("black"));
        getTile(0, 4).setPiece(new King("black"));
        getTile(0, 5).setPiece(new Bishop("black"));
        getTile(0, 6).setPiece(new Knight("black"));
        getTile(0, 7).setPiece(new Rook("black"));

        for (int i = 0; i < 8; ++i) {
            getTile(1, i).setPiece(new Pawn("black"));
        }

        for (int i = 0; i < 8; ++i) {
            getTile(6, i).setPiece(new Pawn("white"));
        }

        getTile(7, 0).setPiece(new Rook("white"));
        getTile(7, 1).setPiece(new Knight("white"));
        getTile(7, 2).setPiece(new Bishop("white"));
        getTile(7, 3).setPiece(new Queen("white"));
        getTile(7, 4).setPiece(new King("white"));
        getTile(7, 5).setPiece(new Bishop("white"));
        getTile(7, 6).setPiece(new Knight("white"));
        getTile(7, 7).setPiece(new Rook("white"));
    }

    /**
     * Get list of possible positions to move the piece in a given position
     *
     * @param posX initial col
     * @param posY initial row
     * @return list of positions
     */
    public ArrayList<int[]> getPossibleMoves(int posX, int posY) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        Piece piece = getTile(posX, posY).getPiece();
        if (piece == null) {
            return possibleMoves;
        }
        MoveType[] moveTypes = piece.getMoveTypes();
        for (MoveType moveType : moveTypes) {
            ArrayList<int[]> tempMoves = moveType.getPossibleMoves(this, posX, posY);
            possibleMoves.addAll(tempMoves);
        }

        return possibleMoves;
    }
}