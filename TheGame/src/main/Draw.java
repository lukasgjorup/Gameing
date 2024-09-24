package main;

import java.awt.*;

public class Draw {

    private final int tileSize;

    public Draw(int tileSize) {
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2d,Camera camera,Player player) {
        // Drawing logic moved from GamePanel
        camera.translate(g2d);
        g2d.setColor(Color.RED);
        g2d.fillRect(tileSize * 6, tileSize * 6, tileSize, tileSize);


       drawPlayer(g2d, camera, player);
    }



    public void drawPlayer(Graphics2D g2d,Camera camera,Player player){
        g2d.setColor(Color.BLUE); // Example color for the player
        g2d.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }


}
