package com.company;

import com.company.pieces.Piece;
import javafx.util.Pair;
import com.company.AI;
import javax.swing.*;
import java.util.ArrayList;

public class Chess {
    // Components
    private static Chess chess = null;
    private final Chessboard board;
    private final jFrame frame;
    private final AI ai;
    // Game variables
    private int moves;
    private boolean isMated = false;

    private Chess() {
        // Create default board
        board = new Chessboard();
        // Frame
        frame = new jFrame(board, 1000);
        // AI
        ai = new AI(board);
    }

    // Singleton
    public static synchronized Chess getInstance() {
        if (chess == null) {
            chess = new Chess();
        }

        return chess;
    }

    // Useful commands
    public boolean getTurn() {
        return (moves % 2 == 0);
    }

    public void nextTurn() {
        moves++;
    }

    /**
     *
     * @return wether or not a king is in check currently
     */

    public boolean isInCheck() {
        for (Piece[] row : board.getBoard()) {
            for (Piece piece: row){
                // Skip empty spaces
                if (piece != null) {
                    ArrayList<Pair<Integer,Integer>> availableMoves = piece.getAvailableMoves();

                    // Only check pieces of the opposition
                    if (piece.isWhite() != Chess.getInstance().getTurn())
                        for (Pair<Integer, Integer> availableMove : availableMoves) {
                            // Check if available moving space has a king in it
                            if (board.getBoard()[availableMove.getKey()][availableMove.getValue()] != null && (board.getBoard()[availableMove.getKey()][availableMove.getValue()].getValue() == 6 || board.getBoard()[availableMove.getKey()][availableMove.getValue()].getValue() == 16)) {
                                System.out.println("PIECE THAT CAN TAKE KING: " + piece.getValue());
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }


    // Only command that updates the board
    public void drawBoard() {
        frame.panel.board = board;
        frame.panel.revalidate();
        frame.panel.repaint();
    }

    public boolean isMated() {
        return isMated;
    }

    public void checkMate() {
        isMated = true;
    }

    // Get board
    public Piece[][] getBoard() {
        return board.getBoard();
    }

    // Get AI instance
    public AI getAI() {
        return ai;
    }

}
