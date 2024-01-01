package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int x, int y, boolean white, int value) {
        super(x, y, white, value);
    }


    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        Piece[][] board = getBoard();
        int x = getXPos();
        int y = getYPos();

        // TODO: Method to get moves
        // Horse moves by going up or down 2 rows from current spot and right or left one

        // This will start 2 below the horse
        for (int i = x+2; i >= x-2; i--) {
            if (i != x && i < 8 && i >= 0) {
                if ((i - x) % 2 == 0) {
                    // If two down or up from horse
                    if (onBoard(y+1)) {
                        if (getBoard()[i][y + 1] == null || getBoard()[i][y + 1].isWhite() != isWhite()) {
                            availableMoves.add(new Pair<Integer, Integer>(i, y + 1));
                        }
                    }

                    if (onBoard(y-1)) {
                        if (getBoard()[i][y - 1] == null || getBoard()[i][y - 1].isWhite() != isWhite()) {
                        availableMoves.add(new Pair<Integer, Integer>(i, y - 1));
                        }
                    }

                } else {
                    // If one down or up from horse
                    if (y+2 < 8) {
                        if (getBoard()[i][y + 2] == null || getBoard()[i][y + 2].isWhite() != isWhite()) {
                            availableMoves.add(new Pair<Integer, Integer>(i, y + 2));
                        }
                    }
                    if (y-2 >= 0) {
                        if (getBoard()[i][y - 2] == null || getBoard()[i][y - 2].isWhite() != isWhite()) {
                            availableMoves.add(new Pair<Integer, Integer>(i, y - 2));
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



