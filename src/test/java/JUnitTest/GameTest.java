package JUnitTest;

import com.game.Game;
import com.game.board.Board;
import com.game.miscellaneous.Player;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void init() {
        new JFXPanel(); // This initializes JavaFX for testing

        // Initialize game
        Player whitePlayer = new Player("white");
        Player blackPlayer = new Player("black");
        Board board = new Board();
        game = new Game(board, whitePlayer, blackPlayer);
    }

    @Test
    public void movePiece() {
        int[] origin = {1, 1};
        int[] destination = {2, 1};
        // Before moving
        assertEquals(game.getBoard().getPiece(1, 1).getType(), "pawn");
        assertNull(game.getBoard().getPiece(2, 1));
        assertEquals(game.getTurn().getColor(), "white");
        // Move
        game.movePiece(origin, destination);
        // After moving
        assertNull(game.getBoard().getPiece(1, 1));
        assertEquals(game.getBoard().getPiece(2, 1).getType(), "pawn");
        assertEquals(game.getTurn().getColor(), "black");
    }

    @Test
    public void nextMove() {
        assertEquals(game.getTurn().getColor(), "white");
        game.nextTurn();
        assertEquals(game.getTurn().getColor(), "black");
    }

    @Test
    public void restartGame() {
        // Before moving
        assertEquals(game.getBoard().getPiece(6, 3).getType(), "pawn");
        assertEquals(game.getBoard().getPiece(1, 1).getType(), "pawn");
        assertEquals(game.getBoardHistory().size(), 1);

        // Make some moves
        int[] origin = {6, 3};
        int[] destination = {5, 3};
        game.movePiece(origin, destination);
        origin[0] = 1;
        origin[1] = 1;
        destination[0] = 2;
        destination[1] = 1;
        game.movePiece(origin, destination);

        // After moving
        assertNull(game.getBoard().getPiece(6, 3));
        assertNull(game.getBoard().getPiece(1, 1));
        assertEquals(game.getBoardHistory().size(), 3);

        // Restart
        game.restartGame();

        // After restarting
        assertEquals(game.getBoard().getPiece(6, 3).getType(), "pawn");
        assertEquals(game.getBoard().getPiece(1, 1).getType(), "pawn");
        assertEquals(game.getBoardHistory().size(), 1);
    }

    @Test
    public void undo() {
        // Before moving
        assertEquals(game.getBoard().getPiece(6, 0).getType(), "pawn");
        assertEquals(game.getTurn().getColor(), "white");

        // Move
        int[] origin = {6, 0};
        int[] destination = {5, 0};
        game.movePiece(origin, destination);

        // After moving
        assertEquals(game.getBoard().getPiece(5, 0).getType(), "pawn");
        assertEquals(game.getTurn().getColor(), "black");

        // Undo
        game.undo();

        // After undoing
        assertEquals(game.getBoard().getPiece(6, 0).getType(), "pawn");
        assertEquals(game.getTurn().getColor(), "white");
    }
}
