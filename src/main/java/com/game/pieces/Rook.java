package com.game.pieces;

import com.game.moves.MoveType;
import com.game.moves.StraightMove;

public class Rook extends com.game.pieces.Piece {

    public Rook(String pieceColor) {
        super(pieceColor, "rook");

        MoveType[] moveTypes = { new StraightMove() };
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other rook
     */
    public Rook(Piece other) {
        super(other);
        MoveType[] moveTypes = { new StraightMove() };
        setMoveTypes(moveTypes);
    }
}
