package JUnitTest;

import com.game.board.Board;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @BeforeEach
    public void init() {
        new JFXPanel(); // This initializes JavaFX for testing
    }

    @Test
    public void populateBoard() {
        Board board = new Board();

        // Empty board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getTile(i, j).setPiece(null);
            }
        }

        // Should be empty
        assertNull(board.getPiece(0, 0));
        assertNull(board.getPiece(1, 1));
        assertNull(board.getPiece(6, 6));
        assertNull(board.getPiece(7, 7));

        // Populate
        board.populateBoard();

        // Should have right pieces
        assertEquals(board.getPiece(0, 0).getType(), "rook");
        assertEquals(board.getPiece(1, 1).getType(), "pawn");
        assertEquals(board.getPiece(6, 6).getType(), "pawn");
        assertEquals(board.getPiece(7, 7).getType(), "rook");
    }

    @Test
    public void getPossibleMoves() {
        Board board = new Board();

        // Black left knight
        ArrayList<int[]> knightMoves =  board.getPossibleMoves(0, 1);
        assertEquals(knightMoves.size(), 2);
        assertEquals(knightMoves.get(0)[0], 2);
        assertEquals(knightMoves.get(0)[1], 2);
        assertEquals(knightMoves.get(1)[0], 2);
        assertEquals(knightMoves.get(1)[1], 0);

        // Some white pawn
        ArrayList<int[]> pawnMoves =  board.getPossibleMoves(6, 4);
        assertEquals(pawnMoves.size(), 2);
        assertEquals(pawnMoves.get(0)[0], 5);
        assertEquals(pawnMoves.get(0)[1], 4);
        assertEquals(pawnMoves.get(1)[0], 4);
        assertEquals(pawnMoves.get(1)[1], 4);

        // Empty tile
        ArrayList<int[]> emptyMoves = board.getPossibleMoves(4, 4);
        assertEquals(emptyMoves.size(), 0);
    }

    @Test
    public void cloneConstructor() {
        // Create board
        Board board1 = new Board();

        // Move piece
        board1.getTile(4, 4).setPiece(board1.getPiece(7, 2));
        board1.getTile(7, 2).setPiece(null);

        // Create copy of board1
        Board board2 = new Board(board1);

        // Board 2 should have moved the same piece
        assertEquals(board1.getPiece(4, 4).getType(), board2.getPiece(4, 4).getType());
        assertNull(board2.getPiece(7, 2));
    }
}
