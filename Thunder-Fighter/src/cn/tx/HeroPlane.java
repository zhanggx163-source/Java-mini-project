package cn.tx;

import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread {
    int x, y;
    int width = 50, height = 50;
    int speed = 5;
    Image image = new ImageIcon("img/10031.png").getImage();
    boolean up, down, left, right;
    GameFrame gf;

    public HeroPlane(){}

    public HeroPlane(int x, int y, GameFrame gf) {
        this.x = x;
        this.y = y;
        this.gf = gf;
    }

    @Override
    public void run(){
        while(true){
            if(up) y -= speed;
            if(down) y += speed;
            if(left) x -= speed;
            if(right) x+= speed;

            if(x < 0) x = 0;
            if(x > gf.getWidth() - width) x = gf.getWidth() - width;
            if(y < 30) y = 30;
            if(y > gf.getHeight() - height) y = gf.getHeight() - height;

            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(hit()){
                speed = 0;
                image = new ImageIcon("img/MAP_YUN02.png").getImage();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public boolean hit(){
        Rectangle enemyRect;
        Rectangle heroRect = new Rectangle(x, y, width, height);

        for(int i = 0; i < gf.enemies.size(); i++){
            EnemyPlane enemyPlane = gf.enemies.get(i);
            enemyRect = new Rectangle(enemyPlane.x, enemyPlane.y, enemyPlane.width, enemyPlane.height);
            if(heroRect.intersects(enemyRect)) return true;
        }
        return false;
    }
}
