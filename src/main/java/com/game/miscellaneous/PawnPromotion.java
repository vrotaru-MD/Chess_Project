package com.game.miscellaneous;

import com.game.board.Board;
import com.game.pieces.Piece;
import com.game.pieces.Queen;

/**
 * Promotes pawn to queen
 */
public class PawnPromotion {

    /**
     * Replace pawn with Queen if pawn reaches
     * opposite of the board
     * @param board current board
     * @param posX move location X
     * @param posY move location Y
     */
    public void promotePawn(Board board, int posX, int posY) {
        Piece piece = board.getPiece(posX, posY);
        if (piece.getType().equals("pawn")) {
            if (piece.getColor().equals("black") && posX == 7) {
                board.getTile(posX, posY).setPiece(new Queen("black"));
            }
            if (piece.getColor().equals("white") && posX == 0) {
                board.getTile(posX, posY).setPiece(new Queen("white"));
            }
        }
    }
}
