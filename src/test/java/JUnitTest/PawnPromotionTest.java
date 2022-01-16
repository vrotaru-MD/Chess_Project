package JUnitTest;

import com.game.board.Board;
import com.game.miscellaneous.PawnPromotion;
import com.game.pieces.Pawn;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnPromotionTest {

    @Test
    public void promotePawn() {
        new JFXPanel(); // This initializes JavaFX for testing
        PawnPromotion pawnPromotion = new PawnPromotion();

        // Initialize board
        Board board = new Board();

        // Create pawns
        Piece testPawn = new Pawn("white");
        Piece testPawnBlack = new Pawn("black");

        //set chess piece on board
        board.getTile(0,1).setPiece(testPawn);

        String pieceType = testPawn.getType();
        String pieceColor =testPawn.getColor();

        assertEquals(pieceType, "pawn");
        assertEquals(pieceColor, "white");

        //promote pawn to queen
        pawnPromotion.promotePawn(board, 0,1);


        pieceType = board.getTile(0,1).getPiece().getType();
        assertEquals(pieceType, "queen");
        assertEquals(pieceColor, "white");



        //set chess piece on board
        board.getTile(0,1).setPiece(testPawnBlack);

        pieceType = testPawnBlack.getType();
        pieceColor = testPawnBlack.getColor();
        assertEquals(pieceType, "pawn");
        assertEquals(pieceColor, "black");

        //promote pawn black to queen
        pawnPromotion.promotePawn(board, 0,1);

        pieceType = board.getTile(0,1).getPiece().getType();
        pieceColor = board.getTile(0,1).getPiece().getColor();
        assertEquals(pieceType, "pawn");
        assertEquals(pieceColor, "black");

        //set chess piece on board
        board.getTile(1,1).setPiece(testPawnBlack);

        //promote to queen
        pawnPromotion.promotePawn(board, 1,1);

        pieceType = board.getTile(1,1).getPiece().getType();
        pieceColor = board.getTile(1,1).getPiece().getColor();
        assertEquals(pieceType, "pawn");
        assertEquals(pieceColor, "black");


    }
}