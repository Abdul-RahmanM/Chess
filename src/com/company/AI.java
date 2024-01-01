package com.company;

import com.company.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class    AI {
    Piece[][] board;
    Chessboard chessBoard;

    public AI(Chessboard chessBoard) {
        this.chessBoard = chessBoard;
        this.board = chessBoard.getBoard();

    }

    public void playMove()  {
        int availableMovesCount = 0;

        // See how many moves are possible
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null && board[r][c].isWhite() == Chess.getInstance().getTurn()) {
                    availableMovesCount += board[r][c].getAvailableMoves().size();
                }
            }
        }

        int move = (int) (Math.random()*availableMovesCount+1);

        // Index that counts until reach desired move
        int index = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null && board[r][c].isWhite() == Chess.getInstance().getTurn()) {
                    for (Pair<Integer, Integer> pair: board[r][c].getAvailableMoves()) {
                        index++;
                        if (move == index) {
                            chessBoard.setSelected(board[r][c]);
                            if (chessBoard.movePiece(pair.getKey(), pair.getValue())) {
                                break;
                            } else {
                                playMove();
                            }
                        }

                    }

                }
            }
        }



    }




}
