package com.game;

import com.game.board.Board;
import com.game.board.Tile;
import com.game.miscellaneous.CheckMate;
import com.game.miscellaneous.PawnPromotion;
import com.game.miscellaneous.Player;
import com.game.pieces.Piece;

import java.util.ArrayList;

public class Game {
    private ArrayList<Board> boardHistory = new ArrayList<>();
    PawnPromotion promotion = new PawnPromotion();
    CheckMate checkMate = new CheckMate();

    private Player whitePlayer;
    private Player blackPlayer;
    private Player turn;

    private String state = "SELECT_PIECE";

    private boolean gameEnded = false;

    private int[] selectedTile = new int[2];

    public Game(Board board, Player whitePlayer, Player blackPlayer) {
        this.boardHistory.add(board);
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = whitePlayer;
    }

    /**
     * Sets location of selected tile
     *
     * @return [posX, posY]
     */
    public int[] getSelectedTile() {
        return selectedTile;
    }

    /**
     * Sets location of selected tile
     *
     * @param selectedTile [posX, posY]
     */
    public void setSelectedTile(int[] selectedTile) {
        this.selectedTile = selectedTile;
    }


    /**
     * Moves a piece
     *
     * @param origin      [posX, posY]
     * @param destination [posX, posY]
     */
    public void movePiece(int[] origin, int[] destination) {
        int x0 = origin[0];
        int y0 = origin[1];
        int x1 = destination[0];
        int y1 = destination[1];

        // Next board
        Board nextBoard = new Board(boardHistory.get(boardHistory.size() - 1));

        Tile originTile = nextBoard.getTile(x0, y0);
        Piece originPiece = originTile.getPiece();
        Tile destinationTile = nextBoard.getTile(x1, y1);
        destinationTile.setPiece(originPiece);
        originTile.setPiece(null);

        // Castling black player
        if (originPiece.getType().equals("king") && Math.abs(y0 - y1) == 2 && x0 == 0) {
            // Move black left rook
            if (y1 == 2) {
                Piece rook_00 = nextBoard.getPiece(0, 0);
                nextBoard.getTile(0, 3).setPiece(rook_00);
                nextBoard.getTile(0, 0).setPiece(null);
            }
            // Move black right rook
            if (y1 == 6) {
                Piece rook_07 = nextBoard.getPiece(0, 7);
                nextBoard.getTile(0, 5).setPiece(rook_07);
                nextBoard.getTile(0, 7).setPiece(null);
            }
        }
        // Castling white player
        if (originPiece.getType().equals("king") && Math.abs(y0 - y1) == 2 && x0 == 7) {
            // Move white left rook
            if (y1 == 2) {
                Piece rook_70 = nextBoard.getPiece(7, 0);
                nextBoard.getTile(7, 3).setPiece(rook_70);
                nextBoard.getTile(7, 0).setPiece(null);
            }
            // Move white right rook
            if (y1 == 6) {
                Piece rook_77 = nextBoard.getPiece(7, 7);
                nextBoard.getTile(7, 5).setPiece(rook_77);
                nextBoard.getTile(7, 7).setPiece(null);
            }
        }

        if (!checkMate.checkMate(nextBoard, turn.getColor())) {
            promotion.promotePawn(nextBoard, x1, y1);
            originPiece.setPieceMoved();
            boardHistory.add(nextBoard);
            nextTurn();
        }
    }

    /**
     * Checks if the king is on check
     * @return true if check
     */
    public boolean isCheckMate() {
        return checkMate.checkMate(getBoard(), turn.getColor());
    }

    /**
     * Checks if the game is over
     * @return true if game is over
     */
    public boolean isGameOver() {
        return checkMate.gameOver(getBoard(), turn.getColor());
    }

    /**
     * Get board
     *
     * @return board
     */
    public Board getBoard() {
        return boardHistory.get(boardHistory.size() - 1);
    }

    /**
     * Get boardHistory
     *
     * @return boardHistory
     */
    public ArrayList<Board> getBoardHistory() {
        return boardHistory;
    }

    /**
     * Restart game
     */
    public void restartGame() {
        ArrayList<Board> newHistory = new ArrayList<>();
        newHistory.add(boardHistory.get(0));
        boardHistory = newHistory;
        turn = whitePlayer;
        gameEnded = false;
    }

    /**
     * Get white player
     *
     * @return white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Get game's state (SELECT_PIECE, MOVE_PIECE)
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Set game's state
     *
     * @param state game's state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get black player
     *
     * @return black player
     */
    public Player getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * Get the player whose turn is it
     *
     * @return player who has to move
     */
    public Player getTurn() {
        return turn;
    }

    /**
     * Moves to next turn
     */
    public void nextTurn() {
        if (turn.getColor().equals("white")) {
            turn = blackPlayer;
        } else {
            turn = whitePlayer;
        }
    }

    /**
     * Undo last move
     */
    public void undo() {
        if (boardHistory.size() > 1) {
            boardHistory.remove(boardHistory.size() - 1);
            nextTurn();
        }
    }

    /**
     * Finish game, not allowing further moves
     */
    public void endGame() {
        gameEnded = true;
    }

    /**
     * Checks if game has ended
     * @return true if game has ended
     */
    public boolean isGameEnded() {
        return gameEnded;
    }
}
