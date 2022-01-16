package JUnitTest;

import com.game.board.Board;

import com.game.pieces.King;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingMoveTest {

    @Test
    void kingMove() {
        new JFXPanel(); // This initializes JavaFX for testing

        // Initialize board
        Board board = new Board();
        // Create a king
        Piece testKing = new King("white");
        // Place it somewhere in the board
        board.getTile(4,4).setPiece(testKing);

        ArrayList<int[]> possibleMoves = board.getPossibleMoves(4, 4);
        int[] expectedMove0 = {3, 4};
        int[] expectedMove5 = {5, 3};

        assertEquals(possibleMoves.size(), 8);

        assertEquals(possibleMoves.get(0)[0], expectedMove0[0]);
        assertEquals(possibleMoves.get(0)[1], expectedMove0[1]);

        assertEquals(possibleMoves.get(5)[0], expectedMove5[0]);
        assertEquals(possibleMoves.get(5)[1], expectedMove5[1]);
    }

}
