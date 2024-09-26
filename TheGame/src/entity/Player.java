package entity;

import main.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // Player attributes

    private int width, height; // Size of the player


    public static boolean movingUp,movingDown, movingLeft, movingRight;


    public Player(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.width = 50; // Example player width
        this.height = 50; // Example player height
        this.speed = 5; // Example player speed
        getPlayerImage();
        direction = "down";
    }

    // Method to update the player's position
    public void update() {
        if(movingUp || movingDown || movingLeft || movingRight){
        if (movingUp) {
            direction = "up";
            y -= speed;
        }
        if (movingDown) {
            direction = "down";
            y += speed;
        }
        if (movingLeft) {
            direction = "left";
            x -= speed;
        }
        if (movingRight) {
            direction = "right";
            x += speed;
        }

        spriteCounter++;
        if(spriteCounter%10==0){
            spriteNumber++;
            if(spriteNumber>6)spriteNumber=1;
            if(spriteCounter>1000)spriteCounter=0;
        }

        }
    }

    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back2.png")));
        up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back3.png")));
        up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back4.png")));
        up5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back5.png")));
        up6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back6.png")));
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right1.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right2.png")));
        right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right3.png")));
        right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right4.png")));
        right5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right5.png")));
        right6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking right6.png")));
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left1.png")));
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left2.png")));
        left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left3.png")));
        left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left4.png")));
        left5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left5.png")));
        left6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left6.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward1.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward2.png")));
        down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward3.png")));
        down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward4.png")));
        down5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward5.png")));
        down6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 forward6.png")));
    }

    public void draw(Graphics2D g2d, Camera camera, Player player){

        BufferedImage image = null;
        // Arrays for each direction's sprites
        BufferedImage[] upSprites = {up1, up2, up3, up4, up5, up6};
        BufferedImage[] downSprites = {down1, down2, down3, down4, down5, down6};
        BufferedImage[] leftSprites = {left1, left2, left3, left4, left5, left6};
        BufferedImage[] rightSprites = {right1, right2, right3, right4, right5, right6};

        BufferedImage[] selectedSprites = null;

// Select the appropriate sprite array based on direction
        switch (direction) {
            case "up":
                selectedSprites = upSprites;
                break;
            case "down":
                selectedSprites = downSprites;
                break;
            case "left":
                selectedSprites = leftSprites;
                break;
            case "right":
                selectedSprites = rightSprites;
                break;
        }

      if(!movingUp && !movingDown && !movingLeft && !movingRight){
          if (selectedSprites != null){
              image =selectedSprites[0];
          }
      }else{
          if (selectedSprites != null && spriteNumber >= 1 && spriteNumber <= selectedSprites.length) {
              image = selectedSprites[spriteNumber - 1];
              g2d.drawImage(image, x, y, this.width, this.height, null);
          }
      }
        g2d.drawImage(image, x, y, this.width, this.height, null);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



}