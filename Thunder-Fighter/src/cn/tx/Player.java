package cn.tx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter {

    HeroPlane heroPlane;
    GameFrame frame;

    public Player(GameFrame frame){
        this.frame = frame;
    }

    public Player(HeroPlane heroPlane){
        this.heroPlane = heroPlane;
    }
    /**
     * Invoked when a key has been pressed.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //38， 40， 37， 39
        switch(keyCode){
            case 38:
                frame.heroPlane.up = true;
                break;
            case 40:
                frame.heroPlane.down = true;
                break;
            case 37:
                frame.heroPlane.left = true;
                break;
            case 39:
                frame.heroPlane.right = true;
                break;
            case 66:
                addBullet();
                break;
        }
        //System.out.println(e.getKeyCode());
    }

    /**
     * Invoked when a key has been released.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case 38:
                frame.heroPlane.up = false;
                break;
            case 40:
                frame.heroPlane.down = false;
                break;
            case 37:
                frame.heroPlane.left = false;
                break;
            case 39:
                frame.heroPlane.right = false;
                break;
        }
    }

    public void addBullet(){
        frame.bullets.add(new Bullet(frame.heroPlane.x + 5, frame.heroPlane.y - 20));
    }


}
