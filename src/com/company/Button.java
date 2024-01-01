package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    boolean visible = true;

    public Button() {
        this.setVisble(visible);
        this.setPreferredSize(new Dimension(100,100));
        this.setBounds(400, 400, 100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this) {
            System.out.println("button clicked. It worked somehow...");
        }
    }

    public void setVisble(boolean b) {
        visible = b;
    }
}
