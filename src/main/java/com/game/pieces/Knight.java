package com.game.pieces;

import com.game.moves.KnightMove;
import com.game.moves.MoveType;

public class Knight extends Piece {

    public Knight(String pieceColor) {
        super(pieceColor, "knight");
        MoveType[] moveTypes = { new KnightMove() };
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other knight
     */
    public Knight(Piece other) {
        super(other);
        MoveType[] moveTypes = { new KnightMove() };
        setMoveTypes(moveTypes);
    }
}

