package com.company;
import com.company.pieces.Piece;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class jFrame extends JFrame implements MouseListener {

    Panel panel;
    Chessboard board;
    JButton button;
    int size;
    ArrayList<Pair<Integer, Integer>> availableMoves = new ArrayList<>();

    public jFrame(Chessboard board, int size) {
        this.board = board;
        this.size = size;

        panel = new Panel(this.board, size);
        panel.addMouseListener(this);

        this.setSize(size,size);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panel);
        this.pack();


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println("CLICK REGISTERED");
        int current_row = mouseEvent.getY() / (size/8);
        int current_col = mouseEvent.getX() / (size/8);

        Piece currentlyClickedPiece = board.getBoard()[current_row][current_col];

        // Let the turn play out:
        board.playTurn(current_row, current_col);


    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {


    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    // Use paint() method

}