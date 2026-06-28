import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.Font.BOLD;

public class ImageCode {
    static String[] strs ={"a","b","c","d","e","f","g","h","i","j","k","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "2","3","4","5","6","7","8","9"};

    static void main() throws IOException {
        int width = 150;
        int height = 90;
        int imageType = BufferedImage.TYPE_INT_RGB;

        BufferedImage image = new BufferedImage(width, height, imageType);
        Graphics pen = image.getGraphics();
        pen.setColor(Color.white);
        pen.fillRect(0, 0, width, height);

        //write numbers on the picture
        pen.setFont(new Font("Arial", BOLD, 30));
        Random r = new Random();
        int x = 25;
        int y = 50;
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(strs.length);
            String str = strs[index];
            pen.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            pen.drawString(str, x, y);
            x += 25;
        }

        pen.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        for (int i = 0; i < 5; i++) {
            int x1 = r.nextInt(30);
            int y1 = r.nextInt(60);
            int x2 = 120 + r.nextInt(30);
            int y2 = 40 + r.nextInt(40);
            pen.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image, "jpg", new File("c.jpg"));
    }
}
