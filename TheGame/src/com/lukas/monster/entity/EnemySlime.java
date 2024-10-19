package com.lukas.monster.entity;

import com.lukas.monster.Tile.TileManeger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static com.lukas.monster.Tile.ImageBuffer.getReadImage;

public class EnemySlime {
    private int x;
    private int y;

    private int health;
    private int speed;
    private int damage;
    public final int width = 64;
    public final int height = 64;
    private final BufferedImage[] walkingImagesL = new BufferedImage[3];
    private final BufferedImage[] walkingImagesR= new BufferedImage[3];
    private final BufferedImage[] attackImages = new BufferedImage[5];
    public int spriteCounter = 0;
    public int spriteNumber = 0;
    public int spriteNumber1 = 0;
    private Player player;
    public boolean attackNumber = false;
    private boolean inMiddleOfAttack = false;
    private int attackRange = 128;

    public BufferedImage image;

    public EnemySlime(int tileSize, Player player) throws IOException {
        Random random = new Random();
        this.x = (TileManeger.maxX/2-15)*tileSize +random.nextInt(31)*tileSize;
        this.y = (TileManeger.maxY/2-15)*tileSize +random.nextInt(31)*tileSize;
        this.health = 10;
        this.speed = 3;
        this.damage = 1;
        getSpriteImage();
        this.player = player;

    }


    public void getSpriteImage() throws IOException {
        walkingImagesL[0] = getReadImage("/Slime/SLIMEMOVELEFT1.png");
        walkingImagesL[1] = getReadImage("/Slime/SLIMEMOVELEFT2.png");
        walkingImagesL[2] = getReadImage("/Slime/SLIMEMOVELEFT3.png");
        walkingImagesR[0] = getReadImage("/Slime/SLIMEMOVERIGHT1.png");
        walkingImagesR[1] = getReadImage("/Slime/SLIMEMOVERIGHT2.png");
        walkingImagesR[2] = getReadImage("/Slime/SLIMEMOVERIGHT3.png");
        attackImages[0] = getReadImage("/Slime/SlimeAttack1.png");
        attackImages[1] = getReadImage("/Slime/SlimeAttack2.png");
        attackImages[2] = getReadImage("/Slime/SlimeAttack3.png");
        attackImages[3] = getReadImage("/Slime/SlimeAttack4.png");
        attackImages[4] = getReadImage("/Slime/SlimeAttack5.png");

        image =walkingImagesL[0];
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

    public boolean takeDamage() {
        attackNumber = true;
        this.health -= player.damage;
        if(this.health <= 0) {
            return true;
        }
        return false;
    }

    public void movementE(Player player){
        int pX = player.getX();
        int pY = player.getY();

        int dx = pX - this.x;
        int dy = pY - this.y;



        double distance = Math.sqrt(dx*dx + dy*dy);


        if(distance>50 && distance<300 && !inMiddleOfAttack) {

            if(dx>0){
                image = walkingImagesR[spriteNumber];
            }else{
                image = walkingImagesL[spriteNumber];
            }

            double moveX = dx / distance;
            double moveY = dy / distance;


            this.x = this.x + (int) (moveX * this.speed);
            this.y = this.y + (int) (moveY * this.speed);

            spriteCounter++;
            if (spriteCounter % 10 == 0) {
                spriteNumber++;
                if (spriteNumber > 2) spriteNumber = 0;
                if (spriteCounter > 1000) spriteCounter = 0;
            }
        }else if (distance<=50 || inMiddleOfAttack){
            inMiddleOfAttack = true;

            image = attackImages[spriteNumber1];

            spriteCounter++;
            if (spriteCounter % 10 == 0) {
                spriteNumber1++;
                if (spriteNumber1 > 4) {
                    spriteNumber1 = 0;
                    inMiddleOfAttack = false;
                }
                if (spriteCounter > 1000) spriteCounter = 0;
            }
        }else{
            image = walkingImagesR[0];
        }

    }




    public void draw(Graphics2D g2d){
        if(Math.abs(player.getX()-this.x) < 64*9 && Math.abs(player.getY()-this.y) < 64*9){
            g2d.drawImage(image, x, y, this.width, this.height, null);
        }
    }

    public void slimeAttack(Graphics2D g2d){
        if (inMiddleOfAttack && spriteNumber1 != 4){
            g2d.setColor(Color.blue);
            g2d.drawRect(this.x - this.height / 2, this.y - this.width / 2, attackRange, attackRange);
        }else if(inMiddleOfAttack){
            g2d.setColor(Color.blue);
            g2d.fillRect(this.x - this.height / 2, this.y - this.width / 2, attackRange, attackRange);
        }
    }



}
