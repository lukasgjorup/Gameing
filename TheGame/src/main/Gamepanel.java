package main;


import Tile.TileManeger;
import entity.EnemyManeger;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gamepanel extends JPanel implements Runnable {
    private final int FPS = 60;
    private final long targetTime = 1000 / FPS; // Target time for each frame in milliseconds

    Player player;
    Map map;
    Draw draw;
    Thread gameThread;
    Camera camera;
    KeyHandler keyHandler;
    Update update;
    TileManeger tileManeger;
    EnemyManeger enemyManeger;

    public Gamepanel() throws IOException {
        map = new Map();
        player = new Player((TileManeger.maxX/2)*map.tileSize, (tileManeger.maxY/2)*map.tileSize);
        camera = new Camera(map,player,this,map);
        update = new Update();
        tileManeger = new TileManeger(this, map);
        enemyManeger = new EnemyManeger(map.getTileSize());
        this.setPreferredSize(new Dimension(map.getScreenWidth(), map.getScreenHeight()));
        this.setDoubleBuffered(true);


        // Initialize the KeyHandler
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler); // Add KeyHandler as a key listener
        this.setFocusable(true);         // Make sure the panel can focus on keyboard events
        this.requestFocusInWindow();     // Request focus to ensure the panel is focused when displayed



        draw = new Draw(map.tileSize);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        this.requestFocusInWindow();

    }

    @Override
    public void run() {
        long startTime;
        long elapsedTime;
        long waitTime;

        while (gameThread.isAlive()) {
            startTime = System.nanoTime();

            // Update game state and render
            update();
            repaint();



            // Calculate the time it took to update and render
            elapsedTime = (System.nanoTime() - startTime) / 1_000_000; // Convert nanoseconds to milliseconds

            // Calculate how much time to wait to maintain consistent FPS
            waitTime = targetTime - elapsedTime;


            // testing how long left System.out.println(waitTime);
            // Sleep for the remaining time to achieve the target FPS
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // If the game is running too slow, don't sleep (but log if necessary)
            else {
                System.out.println("Frame took longer than expected!");
                //we need it to adjust if it takes longer and report it.
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);//delete everything from last drawing.
        Graphics2D g2d = (Graphics2D) g;

        draw.draw(g2d,camera,player,tileManeger,enemyManeger);

        g2d.dispose();
    }

    public void update(){
        update.updateGame(player,camera,map,enemyManeger);


    }



}
