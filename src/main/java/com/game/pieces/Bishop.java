package com.game.pieces;

import com.game.moves.DiagonalMove;
import com.game.moves.MoveType;

/**
 * The bishop moves any number of vacant squares diagonally in a straight line.
 * Consequently, a bishop stays on squares of the same color throughout a game.
 * The two bishops each player starts with move on squares of opposite colors.
 */
public class Bishop extends Piece {

    /**
     * Super constructor for piece class
     * @param pieceColor color as string
     */
    public Bishop(String pieceColor) {
        super(pieceColor, "bishop");

        MoveType[] moveTypes = { new DiagonalMove() };
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other bishop
     */
    public Bishop(Piece other) {
        super(other);
        MoveType[] moveTypes = { new DiagonalMove() };
        setMoveTypes(moveTypes);
    }
}