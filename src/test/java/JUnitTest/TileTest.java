package JUnitTest;

import com.game.board.Tile;
import com.game.pieces.Rook;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void isEmpty() {
        new JFXPanel(); // This initializes JavaFX for testing
        Tile tile = new Tile("light");
        Rook rook = new Rook("black");
        // Should be empty
        assertTrue(tile.isEmpty());
        // Rook is placed on tile
        tile.setPiece(rook);
        // Should not be empty
        assertFalse(tile.isEmpty());
    }
}