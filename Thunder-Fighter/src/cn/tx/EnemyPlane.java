package cn.tx;

import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends Thread{
    int x, y;
    int width = 50, height = 50;
    int speed = 2;
    Image image = new ImageIcon("img/10024.png").getImage();
    GameFrame frame;

    public EnemyPlane() {
    }

    public EnemyPlane(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public EnemyPlane(int x, int y, GameFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    @Override
    public void run(){
        while(true){
            if(hit()){
                speed = 0;
                image = new ImageIcon("img/MAP_YUN02.png").getImage();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                frame.enemies.remove(this);
                break;//get out of loop when hit
            }
            if(y > frame.getHeight()) break;

            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //detect if an enemy plane hit by a bullet
    public boolean hit(){
        Rectangle enemyRect = new Rectangle(x, y, width, height);
        Rectangle bulletRect;
        for (int i = 0; i < frame.bullets.size(); i++) {
            Bullet bullet = frame.bullets.get(i);
            bulletRect = new Rectangle(bullet.x, bullet.y, bullet.width, bullet.height);
            if(enemyRect.intersects(bulletRect)) return true;
        }
        return false;
    }

}
