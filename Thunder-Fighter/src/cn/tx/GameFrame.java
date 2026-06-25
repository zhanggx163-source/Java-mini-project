package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
    HeroPlane heroPlane;
    //the set of bullets
    Vector<Bullet> bullets = new Vector<>();
    //the set of enemies
    Vector<EnemyPlane> enemies = new Vector<>();
    GameFrame frame;

    public GameFrame(){
        frame = this;
        //create a plane
        heroPlane = new HeroPlane();
        heroPlane.start();
        //the size of the window
        this.setSize(500, 760);
        //tile
        this.setTitle("Leingtingzhanji");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    repaint();
                    try{
                        Thread.sleep(10);

                    }catch(InterruptedException e){
                        e.printStackTrace();

                    }
                }

            }

        }).start();

        //produce enemy planes
        new Thread(new Runnable(){
            //get a random number as the x-coordinate of enemy plane
            Random r = new Random();

            @Override
            public void run(){
                while(true){
                    EnemyPlane enemyPlane = new EnemyPlane(r.nextInt(500), 0, frame);
                    enemyPlane.start();
                    enemies.add(enemyPlane);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }

    public void paint(Graphics g){
        //System.out.println("make paint board");
        //a paper
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        //a pen
        Graphics bi = image.getGraphics();
        //draw background
        bi.drawImage(new ImageIcon("img/bj_level1.png").getImage(),0,0,null);
        //draw plane
        bi.drawImage(heroPlane.img, heroPlane.x, heroPlane.y, heroPlane.width, heroPlane.height, null);
        //shoot bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            //if the bullet is still within the frame, then draw bullet
            if(bullet.y > 0){
                //shoot three bullets at one time
                bi.drawImage(bullet.image, bullet.x - 25, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
                bi.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
                bi.drawImage(bullet.image, bullet.x + 25, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
            }else{
                bullets.remove(bullet);
            }
        }

        //enemyPlane
        for (int i = 0; i < enemies.size(); i++) {
            EnemyPlane ep = enemies.get(i);
            //if the bullet is still within the frame, then draw bullet
            if(ep.y < 760){
                bi.drawImage(ep.img, ep.x, ep.y += ep.speed, ep.width, ep.height, null);
            }else{
                enemies.remove(ep);
            }
        }

        //put paper on the board
        g.drawImage(image, 0, 0, null);

    }


    public static void main(String[] args){
        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
        /*把 player 注册为窗口的键盘监听器。
        也就是说：
        以后只要用户在 frame 窗口里按下键盘，
        Java 就会自动通知 player 去处理这些按键。
        * */
        frame.addKeyListener(player);
    }


}
