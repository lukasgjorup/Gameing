package com.lukas.monster.entity;

import java.awt.*;
import java.io.IOException;

public class EnemyManeger {

    public static EnemySlime[] slimes = new EnemySlime[20];
    public static int[] deadSlimes = new int[20];
    public static int deadCount = 0;
    public static int slimesCount = 0;
    public int tileSize;
    public int spawner = 0;
    public EnemyManeger(int tileSize,Player player) throws IOException {
        this.tileSize = tileSize;
        newEnemy(tileSize,player);


    }

    public void newEnemy(int tileSize,Player player) throws IOException {
        spawner++;
        if(spawner>30 && slimesCount< 5){
            slimes[slimesCount] = new EnemySlime(tileSize, player);
            slimesCount++;
            System.out.println(slimesCount);
        }
        if(spawner==31){
            spawner = 0;
        }

    }

    public void update(Player player) {
        for (EnemySlime slime : slimes) {
            if (slime != null) {
                slime.movementE(player);
            }
        }
    }

    public static void draw(Graphics2D g2d) {
        for (EnemySlime slime : slimes) {
            if (slime != null) {
                slime.draw(g2d);
            }
        }
    }

    public static void slimeAttack(Graphics2D g2d) {
        for (EnemySlime slime : slimes) {
            if (slime != null) {
                slime.slimeAttack(g2d);
            }
        }
    }

    public static void deadUpdate() {
        for (int i = deadCount - 1; i >= 0; i--) {
            slimes[deadSlimes[i]] = null;
            slimesCount--;

            // Shift remaining slimes down to fill the gap
            for (int j = deadSlimes[i]; j < slimes.length - 1; j++) {
                if (slimes[j + 1] == null) {
                    break; // Stop if the next slime is null
                }
                slimes[j] = slimes[j + 1]; // Move slime up one position
                slimes[j + 1] = null; // Clear the last position
            }
        }
        deadCount = 0; // Reset deadCount after processing
    }


}
