package JUnitTest;

import com.game.board.Board;
import com.game.pieces.Bishop;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DiagonalMoveTest {

    @Test
    public void diagonalMove() {
        new JFXPanel(); // This initializes JavaFX for testing

        // Initialize board
        Board board = new Board();

        // Create a bishop and place it somewhere in the board
        Piece testBishop = new Bishop("white");
        board.getTile(4,4).setPiece(testBishop);

        // Get possible moves
        ArrayList<int[]> possibleMoves = board.getPossibleMoves(4, 4);
        int numberOfMoves = possibleMoves.size();

        int[] expectedMove0 = {3, 3};
        int[] expectedMove1 = {2, 2};

        assertEquals(possibleMoves.get(0)[0], expectedMove0[0]);
        assertEquals(possibleMoves.get(0)[1], expectedMove0[1]);

        assertEquals(possibleMoves.get(1)[0], expectedMove1[0]);
        assertEquals(possibleMoves.get(1)[1], expectedMove1[1]);

        assertEquals(8, numberOfMoves);
    }
}
