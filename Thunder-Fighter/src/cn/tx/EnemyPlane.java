package cn.tx;

import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends Thread{
    public GameFrame gf;
    public int x,y;
    public int width = 50;
    public int height = 50;
    public int speed = 2;

    public Image img = new ImageIcon("img/10021.png").getImage();

    public EnemyPlane(int x, int y, GameFrame gf) {
        super();
        this.x = x;
        this.y = y;
        this.gf = gf;
    }

    public EnemyPlane(GameFrame gf, int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gf = gf;
    }

    public void run(){
        while(true){
            //if a bullet hits the enemy plane
            if(hit()){
                System.out.println("hit.....");
                this.speed = 0;//speed of an enemy plane
                this.img = new ImageIcon("img/30024.png").getImage();
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gf.enemies.remove(this);
                //got hit by a bullet
                break;
            }
            if(this.y >= 760){
                break;
            }
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //detect if the enemy plane hit by a bullet
    public boolean hit(){
        //the rectangle of an enemy plane
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle rect = null;
        //get all the bullets in the frame right now
        for(int i = 0; i < gf.bullets.size(); i++){
            Bullet bullet = gf.bullets.get(i);
            System.out.println("test hit");
            //the rectangle of a bullet
            rect = new Rectangle(bullet.x, bullet.y - 1, bullet.width, bullet.height);
            //if an enemy plane and a bullet hit
            if(myrect.intersects(rect)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "EnemyPlane{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height= " + height +
                '}';
    }
}
