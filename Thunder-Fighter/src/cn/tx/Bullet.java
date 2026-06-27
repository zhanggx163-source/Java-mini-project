package cn.tx;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    int x, y;
    int width = 15, height = 10;
    int speed = 5;
    Image image = new ImageIcon("img/30021.png").getImage();

    public Bullet(){}

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
