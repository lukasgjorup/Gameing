package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    KeyHandler(Player p) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println("Key pressed: " + code);
        if (code == KeyEvent.VK_S) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
