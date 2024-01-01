package com.company;
import com.company.pieces.*;
import javafx.scene.effect.Light;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class Chessboard {
    // General
    private final Piece[][] board;

    // Current piece
    private Piece selectedPiece;
    private ArrayList<Pair<Integer, Integer>> availableMoves;

    // Create a custom position
    public Chessboard(String fen) {
        board = new Piece[8][8];
        readFen(fen);
    }

    // Default chess board
    public Chessboard() {
        board = new Piece[8][8];
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        readFen(fen);
    }

    public void changeBoard(String fen) {
        readFen(fen);
    }

    private void readFen(String fen) {

        int index;
        String row;
        Character piece;

        String[] fen_s = fen.split("/");

        for (int x = 0; x < fen_s.length; x++) {
            row = fen_s[x]; // String
            index = 0;

            for (int i = 0; i < row.length(); i++) { //p4p2
                piece = row.charAt(i);
                if (piece.equals('p')) {
                    board[x][index] = new Pawn(x, index,false, 1);
                } else if (piece.equals('r')) {
                    board[x][index] = new Rook(x, index,false, 2);;
                } else if (piece.equals('n')) {
                    board[x][index] = new Knight(x, index,false, 3);;
                } else if (piece.equals('b')) {
                    board[x][index] = new Bishop(x, index,false, 4);;
                } else if (piece.equals('q')) {
                    board[x][index] = new Queen(x, index,false, 5);;
                } else if (piece.equals('k')) {
                    board[x][index] = new King(x, index,false, 6);;
                } else if (piece.equals('P')) {
                    board[x][index] = new Pawn(x, index,true, 11);;
                } else if (piece.equals('R')) {
                    board[x][index] = new Rook(x, index, true, 12);
                } else if (piece.equals('N')) {
                    board[x][index] = new Knight(x, index,true, 13);;
                } else if (piece.equals('B')) {
                    board[x][index] = new Bishop(x, index, true, 14);
                } else if (piece.equals('Q')) {
                    board[x][index] = new Queen(x, index,true, 15);;
                } else if (piece.equals('K')) {
                    board[x][index] = new King(x, index, true, 16);
                }else {
                    int val = index;
                    for (int z = val; z < Character.getNumericValue(piece)+val; z++) {
                        board[x][z] = null;
                        index++;
                    }
                    continue;
                }
                index++;
            }
        }

    }

    /**
     * Shows available moves of the piece at the co-ords that are passed
     * Highlights the moves on the board
     * @param current_row: Current row of the piece
     * @param current_col: Current col of the piece
     **/

    public void select(int current_row, int current_col) {
        selectedPiece = board[current_row][current_col];

        if (selectedPiece != null && selectedPiece.isWhite() == Chess.getInstance().getTurn()) {
            availableMoves = selectedPiece.getAvailableMoves();
        } else {
            availableMoves = null;
        }

        // Update the chessboard
        Chess.getInstance().drawBoard();

    }

    /**
     * Processes wether a move is legal and makes it.
     * @param moved_row: Moved row of the piece
     * @param moved_col: Moved col of the piece
     **/


    public boolean movePiece(int moved_row, int moved_col) {
        boolean hasMoved = false;

        // Make sure there is a selected piece as precondition
        if (selectedPiece != null) {
            //System.out.println("phase 1");
            // Last position
            int currentX = selectedPiece.getXPos();
            int currentY = selectedPiece.getYPos();
            // Piece at the desired position
            Piece piece = board[moved_row][moved_col];

             // Check for if king was previously selected piece and current piece is a rook on the same team
             if (piece != null && ((selectedPiece.getValue() == 6 && piece.getValue() == 2) || (selectedPiece.getValue() == 16 && piece.getValue() == 12))) {
                 System.out.println("second phase");
                 String castleType = null;
                 // Check if both the rook and king haven't moved in the game
                 if (!selectedPiece.hasPieceMoved() && !piece.hasPieceMoved()) {

                     // if the rook is on the left side: long castle
                     if (piece.getYPos() == 0) {
                         castleType = "long";
                     } else // If the rook is on the right: short castle
                         if (piece.getYPos() == 7) {
                             castleType = "short";
                         }
                 }
                 if (!(castleType == null) && canCastle(castleType)) {
                     System.out.println("CASTLE");
                     castle(castleType);
                     hasMoved = true;
                 }


             }
            else {
                 //System.out.println(availableMoves);
                for (Pair<Integer, Integer> pair : selectedPiece.getAvailableMoves()) {
                    int AX = pair.getKey();
                    int AY = pair.getValue();


                    if (AX == moved_row && AY == moved_col) {
                        if (piece != null)
                            piece = board[moved_row][moved_col];

                        moveTo(selectedPiece, moved_row, moved_col);

                        // Check if you get checked after the move (if so, undo)
                        if (isInCheck()) {
                            moveTo(selectedPiece, currentX, currentY);
                            if (piece != null) {
                                System.out.println("Piece that was taken: " + piece.getValue());
                                board[pair.getKey()][pair.getValue()] = piece;
                            }

                        } else {
                            hasMoved = true;
                            break;
                        }
                    }
                }
            }
        }
        if (hasMoved) {
            selectedPiece.moved(true);
            Chess.getInstance().nextTurn();
        }
        selectedPiece = null;
        availableMoves = null;
        Chess.getInstance().drawBoard();
        return hasMoved;
    }

    /**
     * @param x: The x position of where the piece is supposed to be moved to
     * @param y: the y position of where the pieces is supposed to be moved to
     * Moves a piece from currentX, currentY to movedX, movedY in board. Does not account for legality
     * NOTE: Only to be used as a utility command. Not on its own
     */
    public void moveTo(Piece piece, int x, int y) {
        board[x][y] = piece; // set new pos to selected piece
        board[piece.getXPos()][piece.getYPos()] = null; // set old position to null

        // Update pos variables of the piece
        piece.setXPos(x);
        piece.setYPos(y);
    }

    public boolean isInCheck() {
        for (Piece[] row : board) {
            for (Piece piece: row){
                // Skip empty spaces
                if (piece != null) {
                    availableMoves = piece.getAvailableMoves();

                    // Only check pieces of the opposition
                    if (piece.isWhite() != Chess.getInstance().getTurn())
                        for (Pair<Integer, Integer> availableMove : availableMoves) {
                            // Check if available moving space has a king in it
                            if (board[availableMove.getKey()][availableMove.getValue()] != null && (board[availableMove.getKey()][availableMove.getValue()].getValue() == 6 || board[availableMove.getKey()][availableMove.getValue()].getValue() == 16)) {
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }

    /**
     * @precondition: Player can legally castle: there are no obstructions or checks in the way.
     * @param castleType: Determines wether to castle "long" or "short"
     * @postcond: Current player castles. King and Rook are moved to appropraite squares.
     */

    public void castle(String castleType) {
        int row = 0;

        // Check if it's white or black's turn and adjust the row appropriately
        if (Chess.getInstance().getTurn()) {
            row = 7;
        }

        if (castleType.equals("long")) {
            System.out.println("LONG");

            // Rook to the correct square
            moveTo(board[row][0], row, 3);

            // King to the correct square
            moveTo(board[row][4], row, 2);

            board[row][3].moved(true); // specifically update that the rook and king have moved
            board[row][2].moved(true); // specifically update that the rook and king have moved

        } else {
            System.out.println("SHORT");
            // King to the correct square
            moveTo(board[row][4], row, 6);

            // Rook to the correct square
            moveTo(board[row][7], row, 5);

            board[row][5].moved(true); // specifically update that the rook and king have moved
            board[row][6].moved(true); // specifically update that the rook and king have moved

        }
    }

    /**
     * Checks wether or not the player can castle
     * TODO: 5/23/2023: CSA finish the can castle method and make the movePiece method sophisticated.
     */

    public boolean canCastle(String castleType) {
        // Can't castle in check
        if (isInCheck()) {
            return false;
        }

        // Can't castle if King has already moved
        if (selectedPiece.hasPieceMoved()) {
            return false;
        }

        boolean canCastle = true;
        int row = 0;

        // LongCastle default params
        int startVal = 1;
        int limitVal = 4;

        // Changes the loop parameters: checks different spots in the row depending on short castle
        if (castleType.equals("short")) {
            startVal = 5;
            limitVal = 7;
        }

        // Change the rank where the castle is taking place
        if (Chess.getInstance().getTurn()) {
            row = 7;
        }

        // Checks the spots between the rook and king to see if anything is obstructing it
        for (int i = startVal; i < limitVal; i++) {

            if (board[row][i] != null) {
                canCastle = false;
                break;
            }
            // Move king to spot to check for Checks
            moveTo(selectedPiece, row, i);

            if (isInCheck()) {
                canCastle = false;
            }
            moveTo(selectedPiece, row, 4);
        }
        return canCastle;
    }

    public boolean isSelected() {
        return selectedPiece != null;
    }

    public ArrayList<Pair<Integer,Integer>> getAvailableMoves() {
        if (selectedPiece != null) {
            return selectedPiece.getAvailableMoves();
        }
        return null;
    }

    /**
     * @precond: Current player's king is in check
     * @return: Wether or not the current player has been checkmated.
     */

    public boolean isInCheckMate() {
        boolean mated = true;
        System.out.println("CHECKING IF MATED!");

        if (!isInCheck()) {
            return false;
        }

        // Loop through every piece on the board
        for (int r = 0; r < 8; r++) {
            for (int c =0; c < 8; c++) {


                // Check if any of the current players pieces can block/escape the check
                if (board[r][c] != null && board[r][c].isWhite() == Chess.getInstance().getTurn()) {

                    Piece currentPiece = board[r][c]; // current piece's moves being tested

                    for (Pair<Integer,Integer> spot: currentPiece.getAvailableMoves()) {

                        // Save the position of the piece being tested
                        int currentX = currentPiece.getXPos();
                        int currentY = currentPiece.getYPos();

                        // Save the piece at the spot
                        Piece spotPiece = board[spot.getKey()][spot.getValue()];

                        // Move the thing there
                        moveTo(currentPiece, spot.getKey(), spot.getValue());

                        // If so, there is no mate!
                        if (!isInCheck()) {
                            mated = false;
                        }
                        // Move the current piece back
                        moveTo(currentPiece, currentX, currentY);

                        // Put the spotpiece back where it belongs and the current piece
                        if (spotPiece != null) {
                            System.out.println("SPOT PIECE VALUE: " + spotPiece.getValue());
                            System.out.println("PUT BACK");
                            board[spot.getKey()][spot.getValue()] = spotPiece;
                        }
                    }
                }
            }
        }

        return mated;
    }


    /**
     *
     * @param x: the row where the mouse was clicked
     * @param y: the column where the mouse was clicked
     */
    public void playTurn(int x, int y) {
        // Piece at the spot that was just clicked
        Piece currentlyClickedPiece = board[x][y];
        if (!isInCheck() || !isInCheckMate()) {
            System.out.println(Chess.getInstance().isMated());
            // Case 1: wants to castle
            if (currentlyClickedPiece != null && isSelected() && ((getSelectedPiece().getValue() == 6 && currentlyClickedPiece.getValue() == 2) || (getSelectedPiece().getValue() == 16 && Objects.requireNonNull(currentlyClickedPiece).getValue() == 12))) {
                movePiece(x, y);
            }
            // Second Case: Click your own piece
            else if (currentlyClickedPiece != null && currentlyClickedPiece.isWhite() == Chess.getInstance().getTurn()) {
                select(x, y);
            }
            // Normal movement of piece
            else if (isSelected()) {

                movePiece(x, y);
            }
            // Chess.getInstance().getAI().playMove();


        } else {
            Chess.getInstance().checkMate();
            Chess.getInstance().drawBoard();
        }
        System.out.println(Chess.getInstance().isMated());

        // Check if a pawn is at the end of the board and promote to a queen
        int r = 0;
        for (int c = 0; c < 8; c++) {
            if (board[r][c] != null && (board[r][c].getValue() == 1 || board[r][c].getValue() == 11)) {

                // White queen or black queen
                if (board[r][c].isWhite()) {
                    board[r][c] = new Queen(r, c, true, 15);
                } else {
                    board[r][c] = new Queen(r, c, false, 5);
                }
            }
            if (c==7 && r != 7) {
                r = 7;
                c=-1;
            }
        }



    }


    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelected(Piece piece) {
        selectedPiece = piece;
    }

    public Piece[][] getBoard() {
        return board;
    }





}
