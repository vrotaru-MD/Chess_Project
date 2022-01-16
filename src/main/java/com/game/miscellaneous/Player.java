package com.game.miscellaneous;

public class Player {
    private String name;
    private String color;

    // Constructor
    public Player(String color) {
        this.color = color;
    }

    /**
     * Gets player color
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets player name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets player name
     * @return player name
     */
    public String getName() {
        return name;
    }
}

