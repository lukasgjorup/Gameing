package com.lukas.monster.entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;

    public BufferedImage[] upAttack = new BufferedImage[3];
    public BufferedImage[] rightAttack = new BufferedImage[3];
    public BufferedImage[] leftAttack = new BufferedImage[3];
    public BufferedImage[] downAttack = new BufferedImage[3];
    public BufferedImage up1, down1, left1, right1, up2, down2, left2, right2,up3, down3, left3, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int attackStage = 0;
    public int sx = 0;
    public int sy = 0;
    public int attackRangeX = 64;
    public int attackRangeY = 64;
}
