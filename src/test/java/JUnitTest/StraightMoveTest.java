package JUnitTest;

import com.game.board.Board;
import com.game.pieces.Rook;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StraightMoveTest {

    @Test
    public void getPossibleMoves() {
        new JFXPanel(); // This initializes JavaFX for testing

        // Initialize board
        Board board = new Board();

        // Create a bishop and place it somewhere in the board
        Piece testRook = new Rook("white");
        board.getTile(4, 4).setPiece(testRook);

        // Get possible moves
        ArrayList<int[]> possibleMoves = board.getPossibleMoves(4, 4);

        int numberOfMoves = possibleMoves.size();

        int[] expectedMove0 = {4, 3};
        int[] expectedMove1 = {4, 2};
        int[] expectedMove2 = {4, 1};
        int[] expectedMove3 = {4, 0};

        assertEquals(possibleMoves.get(0)[0], expectedMove0[0]);
        assertEquals(possibleMoves.get(0)[1], expectedMove0[1]);

        assertEquals(possibleMoves.get(1)[0], expectedMove1[0]);
        assertEquals(possibleMoves.get(1)[1], expectedMove1[1]);

        assertEquals(possibleMoves.get(2)[0], expectedMove2[0]);
        assertEquals(possibleMoves.get(2)[1], expectedMove2[1]);

        assertEquals(possibleMoves.get(3)[0], expectedMove3[0]);
        assertEquals(possibleMoves.get(3)[1], expectedMove3[1]);

        assertEquals(11, numberOfMoves);
    }
}