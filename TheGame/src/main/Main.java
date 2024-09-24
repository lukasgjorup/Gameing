package main;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gamepanel gamepanel = new Gamepanel();
        frame.add(gamepanel);
        frame.setVisible(true); // Make the frame visible
        frame.pack();

        gamepanel.startGameThread();

    }
}