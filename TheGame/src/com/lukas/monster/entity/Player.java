package com.lukas.monster.entity;

import com.lukas.monster.main.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // Player attributes

    private int width, height; // Size of the player
    private int attackX, attackY;
    public int damage;
    public static boolean movingUp,movingDown, movingLeft, movingRight, attacking;


    public Player(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.width = 64; // Example player width
        this.height = 64; // Example player height
        this.speed = 5; // Example player speed
        this.damage = 2;
        getPlayerImage();
        direction = "down";
    }

    // Method to update the player's position
    public void update() {
        if(!attacking) {
            if (movingUp || movingDown || movingLeft || movingRight) {
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
                if (spriteCounter % 10 == 0) {
                    spriteNumber++;
                    if (spriteNumber > 2) spriteNumber = 1;
                    if (spriteCounter > 1000) spriteCounter = 0;
                }
            }
        }
    }

    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogB1-min.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogB2-min.png")));
        up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogB3-min.png")));
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogR1-min.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogR2-min.png")));
        right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogR3-min.png")));
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogL1-min.png")));
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogL2-min.png")));
        left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogL3-min.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogFstand-min.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogFwalk1-min.png")));
        down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogFwalk2-min.png")));
        upAttack[0] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackB2-min.png")));
        upAttack[1] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackB3-min.png")));
        upAttack[2] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackB4-min.png")));
        rightAttack[0] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackR2-min.png")));
        rightAttack[1] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackR3-min.png")));
        rightAttack[2] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackR4-min.png")));
        leftAttack[0] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackL2-min.png")));
        leftAttack[1] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackL3-min.png")));
        leftAttack[2] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackL4-min.png")));
        downAttack[0] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackF2-min.png")));
        downAttack[1] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackF3-min.png")));
        downAttack[2] =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FrogattackF4-min.png")));
    }

    public void draw(Graphics2D g2d, Camera camera, Player player){

        BufferedImage image = null;
        // Arrays for each direction's sprites
        BufferedImage[] upSprites = {up1, up2, up3};
        BufferedImage[] downSprites = {down1, down2, down3};
        BufferedImage[] leftSprites = {left1, left2, left3};
        BufferedImage[] rightSprites = {right1, right2, right3};

        BufferedImage[] selectedSprites = null;


        // Select the appropriate sprite array based on direction
        switch (direction) {
            case "up":
                if(attacking)selectedSprites=upAttack;
                else selectedSprites=upSprites;
                break;
            case "down":
                if(attacking)selectedSprites=downAttack;
                else selectedSprites=downSprites;

                break;
            case "left":
                if(attacking)selectedSprites=leftAttack;
                else selectedSprites = leftSprites;
                break;
            case "right":
                if(attacking)selectedSprites=rightAttack;
                else selectedSprites = rightSprites;
                break;
        }

      if(!movingUp && !movingDown && !movingLeft && !movingRight && !attacking){
          if (selectedSprites != null){
              image =selectedSprites[0];
          }
      }else{
          if (selectedSprites != null && spriteNumber >= 0 && spriteNumber <= selectedSprites.length) {
              image = selectedSprites[spriteNumber];
              g2d.drawImage(image, x, y, this.width, this.height, null);
          }
      }
        g2d.drawImage(image, x, y, this.width, this.height, null);
    }

    public void attacking(Graphics2D g2d) throws IOException {
        if (attacking) {
            if (attackStage == 0) {
                sx = this.x;
                sy = this.y;  // Initialize attack position only once at the start
                spriteNumber = 0;
            }

            // Store the initial position before modifying it
            int resetX = sx;
            int resetY = sy;

            atackAdding(g2d);  // Modify sx, sy based on the direction

            if(attackStage == 3 || attackStage == 4){
                g2d.setColor(Color.red);
                g2d.fillRect(sx, sy, attackRangeX, attackRangeY);
            }else{
                g2d.setColor(Color.red);
                g2d.drawRect(sx, sy,attackRangeX ,attackRangeY );
            }


            //dmgChecker();  // Check if any enemies are hit
            if(attackStage != 0 && attackStage%4==0)spriteNumber++;
            // Reset sx, sy back to initial values
            sx = resetX;
            sy = resetY;
            attackStage++;

            if (attackStage == 12) {
                spriteNumber = 0;
                attacking = false;
                attackStage = 0;
            }
        }
    }

    public void dmgChecker(Graphics2D g2d) throws IOException {
            for (int i = 0; i < EnemyManeger.slimesCount; i++) {
                if(EnemyManeger.slimes[i] != null){
                int enemyStartX = EnemyManeger.slimes[i].getX();
                int enemyEndX = EnemyManeger.slimes[i].getX()+EnemyManeger.slimes[i].height;
                int enemyStartY = EnemyManeger.slimes[i].getY()+EnemyManeger.slimes[i].width/2;
                int enemyEndY = EnemyManeger.slimes[i].getY()+EnemyManeger.slimes[i].width;

                // Define the attack area bounds
                int attackStartX = RXorY(sx,sx+attackRangeX);
                int attackEndX = RYorX(sx,sx+attackRangeX);
                int attackStartY = RXorY(sy,sy+attackRangeY);
                int attackEndY = RYorX(sy,sy+attackRangeY);




                if ((enemyStartX >= attackStartX && enemyStartX <= attackEndX || enemyEndX >= attackStartX && enemyEndX <= attackEndX)
                        && (enemyStartY >= attackStartY && enemyStartY <= attackEndY || enemyEndY >= attackStartY && enemyEndY <= attackEndY)) {
                    boolean deadChecker = EnemyManeger.slimes[i].takeDamage();
                    DamageNumberManeger.newDamageNumber(EnemyManeger.slimes[i].getX(),EnemyManeger.slimes[i].getY(),this.damage);
                    if (deadChecker) {
                        EnemyManeger.deadSlimes[EnemyManeger.deadCount]= i;
                        EnemyManeger.deadCount++;
                        System.out.println("slime died");
                    }
                }
                }
            }
    }

    public int RXorY(int a, int b){
        if(a>b)return b;
        return a;
    }
    public int RYorX(int a, int b){
        if(a>b)return a;
        return b;
    }

    public void atackAdding(Graphics2D g2d) throws IOException {
        attackRangeX = 64;
        attackRangeY = 64;
        switch (direction) {
            case "up":
                attackRangeY = 128;
                sy -= 64;
                break;
            case "down":
                attackRangeY = 128;
                break;
            case "left":
                attackRangeX = 128;
                sx -= 64;
                break;
            case "right":
                attackRangeX = 128;
                break;
        }
        if(attackStage == 3)dmgChecker(g2d);
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