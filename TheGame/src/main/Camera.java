package main;

import javax.swing.*;
import java.awt.*;

public class Camera {
    private int xOffset, yOffset;

    public Camera(Map map, Player player, JPanel panel) {
        this.xOffset = player.getX();
        this.yOffset = player.getY();
    }

    // Center the camera on the player
    public void centerOnPlayer(Player player) {

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
