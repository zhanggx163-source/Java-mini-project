package cn.tx;

import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread {
    int x, y;
    int width = 50, height = 50;
    Image image = new ImageIcon("img/10031.png").getImage();
    int speed = 5;
    boolean up, down, left, right;
    GameFrame frame;

    public HeroPlane() {}

    public HeroPlane(int x, int y, GameFrame frame) {
        this.y = y;
        this.x = x;
        this.frame = frame;
    }


    @Override
    public void run() {
        while(true){
            if(up) y -= speed;
            if(down) y += speed;
            if(left) x -= speed;
            if(right) x += speed;

            if(x < 0) x = 0;
            if(x > frame.getWidth() - width) x = frame.getWidth() - width;

            if(y < 30) y = 30;
            if(y > frame.getHeight() - height) y = frame.getHeight() - height;


            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
