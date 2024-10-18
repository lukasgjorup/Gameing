package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DamageNumbers {

    private int pictureFrame = 0;
    private BufferedImage[] damageImages = new BufferedImage[5];
    private int x,y;
    private int width,height;

    public DamageNumbers(int x, int y, int damage) throws IOException {
        this.x = x;
        this.y = y;
        this.width = 30+damage;
        this.height = 30+damage;

        getSpriteImage();
    }

    public void getSpriteImage() throws IOException {
        damageImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/damageNumbers/damageNumber2.png")));
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(damageImages[0], x, y, this.width, this.height, null);

    }

    public boolean update(){
        pictureFrame++;
        this.y -= 5;
        if(pictureFrame > 10){
            pictureFrame = 0;
            return true;
        }
        return false;
    }

}

