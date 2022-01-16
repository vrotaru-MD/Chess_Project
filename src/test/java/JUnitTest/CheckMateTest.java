package JUnitTest;

import com.game.Game;
import com.game.board.Board;
import com.game.board.Tile;
import com.game.miscellaneous.CheckMate;
import com.game.miscellaneous.Player;
import com.game.pieces.Bishop;
import com.game.pieces.Piece;
import javafx.embed.swing.JFXPanel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckMateTest {
    Game game;
    Board board;
    CheckMate checkMate;

    @Test
    public void checkMate() {
        new JFXPanel(); // This initializes JavaFX for testing

        Player whitePlayer = new Player("white");
        Player blackPlayer = new Player("black");

        Board board = new Board();
        game = new Game(board, whitePlayer, blackPlayer);

        // Get black king and place it in the middle of the board
        Tile kingTile = game.getBoard().getTile(0, 4);
        Piece king = kingTile.getPiece();
        game.getBoard().getTile(4, 4).setPiece(king);
        kingTile.setPiece(null);

        // Create a bishop and place it in a check position
        Piece bishop = new Bishop("white");
        game.getBoard().getTile(6, 6).setPiece(bishop);

        // Change turn
        game.nextTurn();

        // Assert that the king is in check
        assertTrue(game.isCheckMate());
    }

    @Test
    public void gameOver() {
        new JFXPanel(); // This initializes JavaFX for testing

        checkMate = new CheckMate();
        board = new Board();

        //check the black king
        board.getTile(5,5).setPiece(board.getTile(0,4).getPiece());
        board.getTile(0,4).setPiece(null);

        //check if game is ended
        assertTrue(checkMate.gameOver(board, "black"));
        assertFalse(checkMate.gameOver(board, "white"));
    }
}