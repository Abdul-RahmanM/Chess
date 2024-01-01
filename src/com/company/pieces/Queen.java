package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Queen extends Piece {
    private ArrayList<Integer> availableRow = new ArrayList<Integer>();
    private ArrayList<Integer> availableCol = new ArrayList<Integer>();


    public Queen(int x, int y, boolean white, int value) {
        super(x, y, white, value);

    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        Piece[][] board = getBoard();

        int x = getXPos();
        int y = getYPos();

        // TODO: Method to get moves
        // Check the spots above current rook (change x values, keep y same)
        for (int i = x+1; i < 8; i++) {
            Piece piece = getBoard()[i][y];
            // If nothing is on that square add to move list
            if (piece == null) {

                availableMoves.add(new Pair<Integer, Integer>(i,y));
            } else if (piece.isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(i,y));
                break;
            } else if (piece.isWhite() == isWhite()) {
                break;
            }
        }

        // Check the spots under current rook (change x values, keep y same)
        for (int i = x-1; i >= 0; i--) {
            Piece piece = getBoard()[i][y];

            // If nothing is on that square add to move list
            if (piece == null) {
                availableMoves.add(new Pair<Integer, Integer>(i,y));
            } else if (piece.isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(i,y));
                break;
            } else if (piece.isWhite() == isWhite()) {
                break;
            }
        }

        // Check spots to the right of current rook (keep x same, increase y)
        for (int i = y+1; i < 8; i++) {
            Piece piece = getBoard()[x][i];

            // If nothing is on that square add to move list
            if (piece == null) {
                availableMoves.add(new Pair<Integer, Integer>(x,i));
            } else if (piece.isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(x,i));
                break;
            } else if (piece.isWhite() == isWhite()) {
                break;
            }
        }

        // Check spots to the left of current rook (keep x same, increase y)
        for (int i = y-1; i >= 0; i--) {
            Piece piece = getBoard()[x][i];
            // If nothing is on that square add to move list
            if (piece == null) {
                availableMoves.add(new Pair<Integer, Integer>(x,i));
            } else if (piece.isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(x,i));
                break;
            } else if (piece.isWhite() == isWhite()) {
                break;
            }
        }

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



