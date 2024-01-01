package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean white, int value) {
        super(x, y, white, value);

    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        Piece[][] board = getBoard();

        int x = getXPos();
        int y = getYPos();
        int count = 0;
        // TODO: Method to get moves
        // Bottom Right (x goes up) (y goes up)
        for (int i = x+1; i < 8; i++) {
            count += 1;
            if (onBoard(count + y)) {
                Piece piece = getBoard()[i][y + count];
                if (piece == null) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y + count));
                } else if (piece.isWhite() != isWhite()) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y + count));
                    break;
                } else if (piece.isWhite() == isWhite()) {
                    break;
                }
            } else {
                break;
            }
        }
        count = 0;

        // Bottom Left (X goes up) (y goes down)
        for (int i = x+1; i < 8; i++) {
            count += 1;
            if (onBoard(y - count)) {
                Piece piece = getBoard()[i][y - count];
                if (piece == null) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y - count));
                } else if (piece.isWhite() != isWhite()) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y - count));
                    break;
                } else if (piece.isWhite() == isWhite()) {
                    break;
                }
            } else {
                break;
            }
        }
        count = 0;
        // Top Right (X goes down) (y goes up)
        for (int i = x-1; i >= 0; i--) {
            count += 1;
            if (onBoard(y + count)) {
                Piece piece = getBoard()[i][y+count];
                if (piece == null) {
                    availableMoves.add(new Pair<Integer, Integer>(i,y+count));
                } else if (piece.isWhite() != isWhite()) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y+count));
                    break;
                } else if (piece.isWhite() == isWhite()) {
                    break;
                }
            } else {
                break;
            }
        }
        count = 0;
        // Top Left (X goes down) (y goes down)
        for (int i = x-1; i >= 0; i--) {
            count += 1;
            if (onBoard(y - count)) {
                Piece piece = getBoard()[i][y - count];
                if (piece == null) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y - count));
                } else if (piece.isWhite() != isWhite()) {
                    availableMoves.add(new Pair<Integer, Integer>(i, y - count));
                    break;
                } else if (piece.isWhite() == isWhite()) {
                    break;
                }
            } else {
                break;
            }
        }

        return availableMoves;
    }

}

    // Click a pawn
    // Check if it can take something or move forward one
    // Only let you do those moves



