package cn.tx;

import cn.tx.GameFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter {
    GameFrame frame;

    public Player() {
    }

    public Player(GameFrame frame) {
        this.frame = frame;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37 -> this.frame.heroPlane.left = true;
            case 38 -> this.frame.heroPlane.up = true;
            case 39 -> this.frame.heroPlane.right = true;
            case 40 -> this.frame.heroPlane.down = true;
            case 66 -> this.addBullet();
        }

    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37 -> this.frame.heroPlane.left = false;
            case 38 -> this.frame.heroPlane.up = false;
            case 39 -> this.frame.heroPlane.right = false;
            case 40 -> this.frame.heroPlane.down = false;
        }

    }

    public void addBullet() {
        this.frame.bullets.add(new Bullet(this.frame.heroPlane.x + 15, this.frame.heroPlane.y - 20));
    }
}
