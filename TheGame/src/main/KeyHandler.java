package main;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    KeyHandler() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println("Key pressed: " + code);
        if (code == KeyEvent.VK_S) {
            Player.movingDown = true;
        }
        if (code == KeyEvent.VK_W) {
            Player.movingUp = true;
        }
        if (code == KeyEvent.VK_A) {
            Player.movingLeft = true;
        }
        if (code == KeyEvent.VK_D) {
            Player.movingRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            Player.movingDown = false;
        }
        if (code == KeyEvent.VK_W) {
            Player.movingUp = false;
        }
        if (code == KeyEvent.VK_A) {
            Player.movingLeft = false;
        }
        if (code == KeyEvent.VK_D) {
            Player.movingRight = false;
        }
    }
}
