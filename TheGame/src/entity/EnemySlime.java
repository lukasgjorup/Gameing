package entity;

import Tile.TileManeger;
import main.Map.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EnemySlime {

    private int x;
    private int y;
    private int health;
    private int speed;
    private int damage;
    private final int width = 32;
    private final int height = 32;


    public BufferedImage walk1, walk2, walk3, attack1, attack2, attack3, attack4, attack5;
    public BufferedImage image;
    public EnemySlime(int tileSize) throws IOException {
        this.x = (TileManeger.maxX/2)*tileSize +1;
        this.y = (TileManeger.maxY/2)*tileSize +1;
        this.health = 10;
        this.speed = 3;
        this.damage = 1;
        getSpriteImage();

    }


    public void getSpriteImage() throws IOException {
        walk1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        walk2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        walk3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        attack1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        attack2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        attack3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        attack4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        attack5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Sprite-0002 back1.png")));
        image = walk1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void movementE(Player player){

        int dx = player.getX() - this.x;
        int dy = player.getY() - this.y;

        double distance = Math.sqrt(dx*dx + dy*dy);

        double moveX = dx / distance;
        double moveY = dy / distance;

        this.x = this.x + (int)(moveX * this.speed);
        this.y = this.y + (int)(moveY * this.speed);
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.fillRect(x, y, width, height);
    }



}
