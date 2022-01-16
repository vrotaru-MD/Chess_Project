package com.game.pieces;

import com.game.moves.MoveType;
import javafx.scene.image.Image;

public abstract class Piece{
    private String color;
    private String type;
    private MoveType[] moveTypes;
    private String imageUrl;
    private Image image;
    private boolean pieceMoved = false;

    public Piece(String color, String type) {
        this.color = color;
        this.type = type;
        this.imageUrl = "img/" + type + "-" + color + ".png";
        this.image = new Image(imageUrl, 50.0, 50.0, true, true);
    }

    /**
     * Clone constructor
     * @param other piece
     */
    public Piece(Piece other) {
        this.color = other.color;
        this.type = other.type;
        this.moveTypes = other.moveTypes;
        this.imageUrl = other.imageUrl;
        this.image = other.image;
        this.pieceMoved = other.pieceMoved;
    }

    /**
     * Checks if piece has previously moved
     * @return true if piece has been moved
     */
    public boolean isPieceMoved() {
        return pieceMoved;
    }

    /**
     * Set pieceMoved status to true.
     */
    public void setPieceMoved() {
        this.pieceMoved = true;
    }

    /**
     * Gets piece image
     * @return piece image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets move types
     * @return move types
     */
    public MoveType[] getMoveTypes() {
        return moveTypes;
    }

    /**
     * Set move types
     * @param moveTypes array of move types
     */
    public void setMoveTypes(MoveType[] moveTypes) {
        this.moveTypes = moveTypes;
    }

    /**
     * Return color of the piece
     * @return color of the piece
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Return type of the piece
     * @return type of the piece
     */
    public String getType() {
        return this.type;
    }

}


