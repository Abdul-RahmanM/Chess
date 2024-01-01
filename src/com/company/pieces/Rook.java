package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int x, int y, boolean white, int value) {
        super(x, y, white, value);

    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        Piece[][] board = getBoard();

        int x = getXPos();
        int y = getYPos();
        // Check the spots above current rook (change x values, keep y same)
        for (int i = x+1; i < 8; i++) {
            Piece piece = getBoard()[i][y];
            // If no piece is on that square add to move list
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

        return availableMoves;
    }
}




