package main;

import entity.Player;

import java.awt.*;

public class Draw {

    private final int tileSize;

    public Draw(int tileSize) {
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2d, Camera camera, Player player) {
        g2d.setColor(Color.BLACK);
        // Drawing logic moved from GamePanel
        camera.translate(g2d);
        g2d.setColor(Color.RED);
        g2d.fillRect(tileSize * 6, tileSize * 6, tileSize, tileSize);


        player.draw(g2d, camera, player);
    }


}
