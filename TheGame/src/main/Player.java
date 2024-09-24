package main;

public class Player {

    // Player attributes
    private int x, y; // Position of the player
    private int width, height; // Size of the player
    private float speed; // Player movement speed

    public boolean movingUp,movingDown, movingLeft, movingRight;


    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50; // Example player width
        this.height = 50; // Example player height
        this.speed = 5.0f; // Example player speed
    }

    // Method to update the player's position
    public void update() {
        if (movingUp) {
            y -= speed;
        }
        if (movingDown) {
            y += speed;
        }
        if (movingLeft) {
            x -= speed;
        }
        if (movingRight) {
            x += speed;
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



}