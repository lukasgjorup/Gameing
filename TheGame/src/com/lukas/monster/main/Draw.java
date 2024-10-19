package com.lukas.monster.main;

import com.lukas.monster.Tile.TileManeger;
import com.lukas.monster.entity.DamageNumberManeger;
import com.lukas.monster.entity.EnemyManeger;
import com.lukas.monster.entity.Player;

import java.awt.*;
import java.io.IOException;

public class Draw {

    private final int tileSize;

    public Draw(int tileSize) {
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2d, Camera camera, Player player, TileManeger tileManeger,EnemyManeger enemyManeger) throws IOException {
        // Drawing logic moved from GamePanel
        camera.translate(g2d);
        tileManeger.draw(g2d, player);
        EnemyManeger.slimeAttack(g2d);
        player.attacking(g2d);
        EnemyManeger.draw(g2d);
        DamageNumberManeger.draw(g2d);
        player.draw(g2d, camera, player);

    }


}
