package main;

import Tile.TileManeger;
import entity.EnemyManeger;
import entity.Player;

import java.awt.*;

public class Draw {

    private final int tileSize;

    public Draw(int tileSize) {
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2d, Camera camera, Player player, TileManeger tileManeger,EnemyManeger enemyManeger) {
        // Drawing logic moved from GamePanel
        camera.translate(g2d);
        tileManeger.draw(g2d, player);
        EnemyManeger.draw(g2d);
        player.draw(g2d, camera, player);

    }


}
