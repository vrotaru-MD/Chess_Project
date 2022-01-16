package JUnitTest;

import com.game.board.Board;
import com.game.pieces.Knight;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightMoveTest {

    @Test
    void knightMove() {
        new JFXPanel(); // This initializes JavaFX for testing

        // Initialize board
        Board board = new Board();

        // Create a knight and lace it somewhere in the board
        Piece knight = new Knight("white");
        board.getTile(3,3).setPiece(knight);

        // Get possible moves
        ArrayList<int[]> possibleMoves = board.getPossibleMoves(3, 3);
        int[] expectedMove0 = {1, 4};
        int[] expectedMove5 = {2, 1};

        assertEquals(possibleMoves.size(), 8);

        assertEquals(possibleMoves.get(0)[0], expectedMove0[0]);
        assertEquals(possibleMoves.get(0)[1], expectedMove0[1]);

        assertEquals(possibleMoves.get(5)[0], expectedMove5[0]);
        assertEquals(possibleMoves.get(5)[1], expectedMove5[1]);
    }

}
