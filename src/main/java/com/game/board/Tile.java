package com.game.board;

import com.game.pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
    private String type;
    private Boolean isValidDestination = false;
    private Boolean selected = false;
    private Piece piece;

    public Tile(String type) {
        this.type = type;
    }

    /**
     * Checks if the tile has no piece
     * @return true if there is no piece in this tile
     */
    public boolean isEmpty() {
        return piece == null;
    }

    /**
     * Gets tile's type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets pane for using in UI
     * @return pane
     */
    public Pane getPane() {
        String styleClass = type + "-tile";
        Pane pane = new Pane();
        pane.getStyleClass().add(styleClass);

        if (isValidDestination) {
            pane.getStyleClass().add("selectable-tile");
        }

        if (selected) {
            pane.getStyleClass().add("selected-tile");
        }

        if (piece != null) {
            Image image = piece.getImage();
            pane.getChildren().add(new ImageView(image));
        }

        return pane;
    }

    /**
     * Gets piece in the tile
     * @return tile
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the piece of this tile
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Set validity of destination
     * @param isValidDestination
     */
    public void setIsValidDestination(Boolean isValidDestination) {
        this.isValidDestination = isValidDestination;
    }

    /**
     * Checks if tile is a valid destination
     * @return true if valid
     */
    public boolean getIsValidDestination() {
        return isValidDestination;
    }

    /**
     * Toggle state for selected state
     */
    public void toggleSelected() {
        boolean selected = this.selected;
        this.selected = !selected;
    }

    /**
     * Set state for selected state
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
