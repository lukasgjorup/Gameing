package main;

import javax.swing.*;
import java.awt.*;

public class Camera {
    private int xOffset, yOffset;

    public Camera(Map map, Player player, JPanel panel, Map map1) {
        this.xOffset = player.getX();
        this.yOffset = player.getY();
    }

    // Center the camera on the player
    public void centerOnPlayer(Player player,Map map) {
        this.xOffset = player.getX() - map.getScreenWidth() / 2;
        this.yOffset = player.getY() - map.getScreenHeight() / 2;
    }

    // Translate the graphics based on camera offset
    public void translate(Graphics g) {
        g.translate(-xOffset, -yOffset);
    }

    // Getters for camera offsets
    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
