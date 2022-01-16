package com.game.pieces;

import com.game.moves.MoveType;
import com.game.moves.PawnMove;

/**
 * Implementation of pawn piece
 */
public class Pawn extends Piece {

    public Pawn(String pieceColor) {
        super(pieceColor, "pawn");
        MoveType[] moveTypes = { new PawnMove()};
        setMoveTypes(moveTypes);
    }

    /**
     * Clone constructor
     * @param other pawn
     */
    public Pawn(Piece other) {
        super(other);
        MoveType[] moveTypes = { new PawnMove() };
        setMoveTypes(moveTypes);
    }

}

