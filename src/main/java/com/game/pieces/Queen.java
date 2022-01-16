package com.game.pieces;

import com.game.moves.DiagonalMove;
import com.game.moves.MoveType;
import com.game.moves.StraightMove;

public class Queen extends Piece {

    public Queen(String pieceColor) {
        super(pieceColor, "queen");

        MoveType[] moveTypes = { new DiagonalMove(), new StraightMove()};
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other queen
     */
    public Queen(Piece other) {
        super(other);
        MoveType[] moveTypes = { new DiagonalMove(), new StraightMove()};
        setMoveTypes(moveTypes);
    }
}