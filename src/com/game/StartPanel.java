package com.game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartPanel extends JPanel implements KeyListener {

    JFrame brickFrame;
    JFrame startFrame;
    public StartPanel(JFrame startFrame, JFrame brickFrame) {
        this.brickFrame = brickFrame;
        this.startFrame = startFrame;
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.startFrame.setVisible(false);
            this.brickFrame.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
