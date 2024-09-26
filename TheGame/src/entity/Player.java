package entity;

import main.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    }

    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-0002 back.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-0002 Looking right.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-0002 Looking Left.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-0002.png"));
    }

    public void draw(Graphics2D g2d, Camera camera, Player player){

        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
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