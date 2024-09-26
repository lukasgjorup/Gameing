package main;


import javax.swing.*;
import java.io.IOException;

public class  Main {
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gamepanel gamepanel = new Gamepanel();
        frame.add(gamepanel);
        frame.setVisible(true); // Make the frame visible
        frame.pack();

        gamepanel.startGameThread();

    }
}