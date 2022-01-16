package com.game.pieces;

import com.game.moves.KingMove;
import com.game.moves.MoveType;

public class King extends Piece {

    /**
     * Super constructor for piece class
     * @param pieceColor piece color
     */
    public King(String pieceColor) {
        super(pieceColor, "king");
        MoveType[] moveTypes = { new KingMove()};
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other king
     */
    public King(Piece other) {
        super(other);
        MoveType[] moveTypes = { new KingMove() };
        setMoveTypes(moveTypes);
    }
}

