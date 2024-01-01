package com.company.pieces;

import com.company.Chessboard;
import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Pawn extends Piece {
    boolean canPromote;

    public Pawn(int x, int y, boolean white, int value) {
        super(x, y, white, value);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        Piece[][] board = getBoard();
        ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();
        int xMod;
        int x = getXPos();
        int y = getYPos();

        if (isWhite()) {
            xMod = -1;
        } else {
            xMod = 1;
        }

        if (Math.abs(x+xMod) < 8 && x+xMod >= 0) {

            if (board[x + xMod][y] == null) {
                availableMoves.add(new Pair<Integer, Integer>(x + xMod, y));
            }

            // Check if the piece is in the starting position
            if (isWhite() && getXPos() == 6 || !isWhite() && getXPos() == 1) {
                if (board[x + 2*xMod][y] == null && board[x + xMod][y] == null) {
                    availableMoves.add(new Pair<Integer, Integer>(x + 2*xMod, y));
                }
            }
            // If you can take diagonally to the right
            if (y+1 < 8 && board[x + xMod][y + 1] != null && board[x+xMod][y+1].isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(x+xMod, y+1));
            }
            // If you can take diagonally to the left
            if (y-1 >= 0 && board[x + xMod][y - 1] != null && board[x+xMod][y-1].isWhite() != isWhite()) {
                availableMoves.add(new Pair<Integer, Integer>(x+xMod, y-1));
            }
        }
        return availableMoves;
    }



}



