package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class King extends Piece {
    boolean hasCastled = false;

    public King(int x, int y, boolean white, int value) {
        super(x, y, white, value);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        Piece[][] board = getBoard();

        int x = getXPos();
        int y = getYPos();

        // TODO: Method to get moves
        for (int i = x-1; i <= x+1; i++) {
            if (onBoard(i)) {
                for (int z = y - 1; z <= y + 1; z++) {
                    if (onBoard(z)) {
                        Piece piece = getBoard()[i][z];
                        if (piece == null) {
                            availableMoves.add(new Pair<Integer, Integer>(i, z));
                        } else if (piece.isWhite() != isWhite()) {
                            availableMoves.add(new Pair<Integer, Integer>(i, z));
                        }
                    }
                }
            }
        }


        return availableMoves;
    }
}

    // Click a pawn
    // Check if it can take something or move forward one
    // Only let you do those moves



