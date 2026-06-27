package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
    HeroPlane heroPlane;
    //store bullets
    Vector<Bullet> bullets = new Vector<>();
    Vector<EnemyPlane> enemies = new Vector<>();

    GameFrame frame;

    public GameFrame(){
        frame = this;
        this.setSize(500, 760);
        this.setTitle("Thunder Fighter");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        heroPlane = new HeroPlane(this.getWidth()/2 - 50, this.getHeight() - 100, this);
        heroPlane.start();

        //Keep updating the background
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Produce enemies
        new Thread(new Runnable(){
            Random r = new Random();
            @Override
            public void run(){
                while(true){
                    EnemyPlane enemyPlane = new EnemyPlane(r.nextInt(getWidth() - 50), 0, frame);
                    enemyPlane.start();
                    enemies.add(enemyPlane);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }).start();

    }


    public void paint(Graphics g){
        BufferedImage paper = (BufferedImage)this.createImage(this.getWidth(), this.getHeight());
        Graphics pen = paper.getGraphics();
        pen.drawImage(new ImageIcon("img/bj_level1.png").getImage(), 0, 0, null);
        pen.drawImage(heroPlane.image, heroPlane.x, heroPlane.y, heroPlane.width, heroPlane.height,null);

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            //draw bullet within the frame
            if(bullet.y > 0) {
                pen.drawImage(bullet.image, bullet.x - 5, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
                pen.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
                pen.drawImage(bullet.image, bullet.x + 5, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
            }else
                bullets.remove(bullet);
        }

        for (int i = 0; i < enemies.size(); i++) {
            EnemyPlane enemyPlane = enemies.get(i);
            if(enemyPlane.y > 0 || enemyPlane.y < getHeight())
                pen.drawImage(enemyPlane.image, enemyPlane.x, enemyPlane.y += enemyPlane.speed, enemyPlane.width, enemyPlane.height, null);
            else
                enemies.remove(enemyPlane);
        }

        g.drawImage(paper,0, 0, null);
    }

    static void main() {
        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
        frame.addKeyListener(player);
    }
}
