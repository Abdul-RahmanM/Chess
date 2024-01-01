package com.company.pieces;


import com.company.Chess;
import com.company.Chessboard;
import javafx.util.Pair;

import java.util.ArrayList;

public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;
    private int xPos;
    private int yPos;
    private int value;

    private boolean hasMoved = false;

    private ArrayList<Pair> availableMoves;

    public Piece(int x, int y, boolean white, int value)
    {
        this.value = value;
        this.xPos = x;
        this.yPos = y;
        this.setWhite(white);
    }

    public boolean isWhite()
    {
        return this.white;
    }

    public void setWhite(boolean white)
    {
        this.white = white;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setXPos(int x) {
        xPos = x;
    }
    public void setYPos(int y) {
        yPos = y;
    }

    // Check if the piece
    public boolean hasPieceMoved() {
        return hasMoved;
    }

    // Set it to moved
    public void moved(boolean b) {
        hasMoved = b;
    }

    public int getValue() {
        return value;
    }

    public Piece[][] getBoard() {
        return Chess.getInstance().getBoard();
    }

    public boolean onBoard(int val) {
        return val >= 0 && val < 8;
    }



    public abstract ArrayList<Pair<Integer, Integer>> getAvailableMoves();

}
