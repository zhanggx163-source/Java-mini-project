package cn.tx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MyImage extends JPanel {
    BufferedImage bgImage;//pictured displayed on the window

    static void main() throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setTitle("Album");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);

        MyImage myImage = new MyImage();
        frame.add(myImage);
        myImage.initImgs();//read pictures

        myImage.repaint();

        myImage.begin();

        frame.setVisible(true);
    }

    int num = 0;

    public void begin(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    bgImage = images[num];
                    num++;
                    if(num == 4){
                        num = 0;
                    }

                 while(true){
                     if(ff < 100f){
                         ff += 2f;
                         repaint();
                     }else{
                         ff = 0f;
                         break;
                     }
                     try {
                         Thread.sleep(50);
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 }
                }
            }
        }).start();
    }
    //显示图片的比列
    float ff = 0f;

    @Override
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;

        if(bgImage != null){
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(ff/100f));
            graphics2D.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    BufferedImage[] images = new BufferedImage[4];

    //load pictures
    public void initImgs() throws IOException {
        try {
            for (int i = 1; i <= 4; i++) {
                BufferedImage image = ImageIO.read(MyImage.class.getResource("/images/" + i + ".jpeg"));
                images[i - 1] = image;
            }
            //bgImage = images[1];
        } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
