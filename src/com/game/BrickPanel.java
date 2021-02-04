package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BrickPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks;
    Block ball;
    Block paddle;
    JFrame startFrame;
    JFrame brickFrame;
    Thread thread;
    Integer destroys = 0;
    Integer countEnter = 0;

    public BrickPanel(JFrame startFrame, JFrame brickFrame) {
        this.startFrame = startFrame;
        this.brickFrame = brickFrame;
        reset();
    }

    public void reset() {
        blocks = new ArrayList<Block>();
        ball = new Block(320, 600, 35, 25, "ball.png");
        paddle = new Block(320,650, 1000, 50,"paddle.png");
        for (int i = 0;i<8;i++) {
            blocks.add(new Block((i*60 + 100),0,60,60, "blue.png"));
        }
        for (int i = 0;i<8;i++) {
            blocks.add(new Block((i*60 + 100),50,60,60, "green.png"));
        }
        for (int i = 0;i<8;i++) {
            blocks.add(new Block((i*60 + 100),100,60,60, "red.png"));
        }
        destroys = 0;
        countEnter = 0;
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void update() {
        ball.x += ball.movX;

        if (ball.x > (getWidth() - 25) || ball.x < 0) {
            ball.movX *= -1;
        }
        if (ball.y < 0 || ball.intersects(paddle)) {
            ball.movY *= -1;
        }
        ball.y += ball.movY;

        if (ball.y > getHeight() || destroys == blocks.size()) {
            thread = null;
            reset();
            this.brickFrame.setVisible(false);
            this.startFrame.setVisible(true);
        }

        blocks.forEach(block -> {
            if (ball.intersects(block) && !block.destroyed) {
                block.destroyed = true;
                ball.movY *= -1;
                destroys++;
            }
        });
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && countEnter == 0) {
            countEnter++;
            thread = new Thread(() -> {
                while (true) {
                    update();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 15;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i< blocks.size(); i++) {
            blocks.get(i).draw(g, this);
        }
        ball.draw(g, this);
        paddle.draw(g, this);
    }
}
