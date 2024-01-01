package com.company;
import com.company.pieces.Piece;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    // Current state of the board
    Chessboard board;
    int size;
    int square_side_length;

    // Colors for the board
    final Color BROWN = new Color(171, 111, 63);
    final Color WHITE = new Color(255, 192, 153);

    Piece[][] array;
    Image img;
    ArrayList<Pair<Integer, Integer>> availableMoves;
    JButton button;

    public Panel(Chessboard board, int size) {
        this.size = size;
        square_side_length = size / 8;
        this.availableMoves = board.getAvailableMoves();
        this.board = board;
        this.setPreferredSize(new Dimension(size, size));


    }

    public void paint(Graphics g) {

        this.availableMoves = board.getAvailableMoves();
        this.array = board.getBoard();
        Graphics2D g2D = (Graphics2D) g;

        int x, y;
        Piece piece;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                piece = array[row][col];
                // Set x coordinates of rectangle
                // by 20 times
                x = col * square_side_length;

                // Set y coordinates of rectangle
                // by 20 times
                y = row * square_side_length;

                // Check whether row and column
                // are in even position
                // If it is true set Black color
                if ((row % 2 == 0) == (col % 2 == 0)) {
                    g2D.setColor(BROWN);
                    g2D.fillRect(x, y, square_side_length, square_side_length);
                } else {
                    g2D.setColor(WHITE);
                    g2D.fillRect(x, y, square_side_length, square_side_length);
                    // Create a rectangle with
                    // length and breadth of 20
                }
                if (piece != null && piece.getValue() != 0) {

                    Image img = new ImageIcon(piece.getValue() + ".png").getImage();
                    img = img.getScaledInstance(square_side_length, square_side_length, Image.SCALE_AREA_AVERAGING);

                    MediaTracker tracker = new MediaTracker(new java.awt.Container());
                    tracker.addImage(img, 0);
                    try {
                        tracker.waitForAll();
                        g2D.drawImage(img, x, y, null);
//                        g2D.setColor(Color.RED);
//                        g2D.drawString("x: " + row + " y: " + col, x, y + 100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException("Image loading interrupted", ex);
                    }
                }
            }
        }
        if (Chess.getInstance().isMated()) {
            System.out.println("MATE: IMAGE SHOULD APPEAR");
            Image img;

            if (Chess.getInstance().getTurn()) {
                img = new ImageIcon("black_wins_checkmate.png").getImage();
            } else {
                img = new ImageIcon("checkmate.png").getImage();
            }
            img = img.getScaledInstance(size, size, Image.SCALE_AREA_AVERAGING);

            MediaTracker tracker = new MediaTracker(new java.awt.Container());
            tracker.addImage(img, 0);
            try {
                tracker.waitForAll();
                g2D.drawImage(img, 0, 0, null);
            } catch (InterruptedException ex) {
                throw new RuntimeException("Image loading interrupted", ex);
            }
        }

        if (availableMoves != null) {
            for (Pair<Integer, Integer> pair : availableMoves) {

                int AX = pair.getKey();
                int AY = pair.getValue();

                // Highlight the squares that are valid moves in this color
                g2D.setColor(new Color(213, 134, 145, 123));
                g2D.fillRect(AY * square_side_length, AX * square_side_length, square_side_length, square_side_length);

            }
        }
    }
}
