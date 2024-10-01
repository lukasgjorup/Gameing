package entity;

import java.awt.*;
import java.io.IOException;

public class EnemyManeger {

    public static EnemySlime[] slimes = new EnemySlime[20];
    public int tileSize;
    public EnemyManeger(int tileSize) throws IOException {
        this.tileSize = tileSize;
        newEnemy(tileSize);


    }

    public void newEnemy(int tileSize) throws IOException {
        slimes[0] = new EnemySlime(tileSize);


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


}
