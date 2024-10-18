package Tile;



import entity.Player;
import main.Gamepanel;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.lowest;

public class TileManeger {

    Gamepanel gp;
    Map map;
    Tile[][] tile;
    short[][] check;
    public static int maxX = 100;
    public static int maxY = 100;
    Random rand;
    public static BufferedImage grassImage;
    public static BufferedImage waterImage;
    public static BufferedImage stoneImage;
    public TileManeger(Gamepanel gp, Map map) throws IOException {
        this.gp = gp;
        this.map = map;
        tile = new Tile[maxX][maxY];
        check = new short[maxX][maxY];
        rand = new Random();
        getTileImage();
    }

    public void getTileImage() throws IOException {
        // Load tile images
        grassImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/GRASS.png")));
        waterImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/WATER.png")));
        stoneImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/stone2.png")));
        int radius = (int)(((double) Math.min(maxX, maxY) /2)*0.75);
        int radius1 = (int)(((double) Math.min(maxX, maxY) /2)*0.65);
        int radius2 = (int)(((double) Math.min(maxX, maxY) /2)*0.25);
        //create map
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                tile[x][y] = new Tile();
                int randomTile = rand.nextInt(10);
                int distance = (int) Math.sqrt(Math.pow(x - (double) maxX /2, 2) + Math.pow(y - (double) maxY /2, 2));
                if (distance < radius2) {
                    if(randomTile < 3){
                        tile[x][y].image = stoneImage;
                    }else{
                        tile[x][y].image = grassImage;
                    }
                }else if (distance < radius1) {
                    tile[x][y].image = grassImage;
                }else if (distance < radius) {
                    if(randomTile < 3){
                        tile[x][y].image = waterImage;
                    }else{
                        tile[x][y].image = grassImage;
                    }
                }else{
                    tile[x][y].image = waterImage;
                }
            }
        }

        //make map edge
        int[][] directions = {
                {-1, -1, 1}, // top-left -> water1
                {0, -1, 2}, // top -> water2
                {1, -1, 3}, // top-right -> water3
                {-1, 0, 4}, // left -> water4
                {1, 0, 6}, // right -> water6
                {-1, 1, 7}, // bottom-left -> water7
                {0, 1, 8}, // bottom -> water8
                {1, 1, 9}, // bottom-right -> water9
        };

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int distance = (int) Math.sqrt(Math.pow(x - (double) maxX /2, 2) + Math.pow(y - (double) maxY /2, 2));
                if(distance < radius && distance >= radius1-3){
                    if (tile[x][y].image == grassImage) {
                        // Check the surrounding tiles
                        for (int[] direction : directions) {
                            int x1 = x + direction[0];
                            int y1 = y + direction[1];

                            // Ensure we don't go out of bounds
                            if (x1 >= 0 && x1 < maxX && y1 >= 0 && y1 < maxY) {
                                // If the neighboring tile is water, set the corresponding flag
                                if (tile[x1][y1].image == waterImage) {
                                    switch (direction[2]) {
                                        case 1: tile[x][y].water1 = true; break;
                                        case 2: tile[x][y].water2 = true; break;
                                        case 3: tile[x][y].water3 = true; break;
                                        case 4: tile[x][y].water4 = true; break;
                                        case 6: tile[x][y].water6 = true; break;
                                        case 7: tile[x][y].water7 = true; break;
                                        case 8: tile[x][y].water8 = true; break;
                                        case 9: tile[x][y].water9 = true; break;
                                    }
                                }
                            }
                        }

                        assignWaterEdgeSprite(tile[x][y]);


                    }
                }
            }
        }




    }


    void assignWaterEdgeSprite(Tile tile) throws IOException {
        // Example logic to assign sprites based on water positions
        if (tile.water2 && tile.water4 && tile.water6 && tile.water8){
         tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W1.png")));
        } else if (tile.water2 && tile.water4 && tile.water6) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W2.png")));
        } else if (tile.water2 && tile.water4 && tile.water8) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W3.png")));
        } else if (tile.water2 && tile.water6 && tile.water8) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W4.png")));
        } else if (tile.water4 && tile.water6 && tile.water8) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W5.png")));
        } else if (tile.water2 && tile.water4 && tile.water9) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wrt9.png")));
        }else if (tile.water6 && tile.water2 && tile.water7) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wlt7.png")));
        }else if (tile.water8 && tile.water4 && tile.water3) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wlb3.png")));
        }else if (tile.water8 && tile.water6 && tile.water1) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wrb1.png")));
        }else if (tile.water8 && tile.water4) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wrb.png")));
        } else if (tile.water2 && tile.water4) {
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wrt.png")));
        }else if (tile.water2 && tile.water6){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wlt.png")));
        } else if (tile.water8 && tile.water6){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wlb.png")));
        }else if (tile.water8 && tile.water2){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wtb.png")));
        }else if (tile.water4 && tile.water6){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wlr.png")));
        }else if (tile.water8 && tile.water1 && tile.water3){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W138.png")));
        }else if (tile.water2 && tile.water7 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W279.png")));
        }else if (tile.water4 && tile.water3 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W349.png")));
        }else if (tile.water1 && tile.water6 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W167.png")));
        }else if (tile.water2 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W27.png")));
        }else if (tile.water1 && tile.water8){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W18.png")));
        }else if (tile.water3 && tile.water8){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W38.png")));
        }else if (tile.water2 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W29.png")));
        }else if (tile.water3 && tile.water4){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W34.png")));
        }else if (tile.water4 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W49.png")));
        }else if (tile.water1 && tile.water6){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W16.png")));
        }else if (tile.water6 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W67.png")));
        }else if (tile.water8){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wb.png")));
        }else if (tile.water6){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wr.png")));
        }else if (tile.water4){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wl.png")));
        }else if (tile.water2){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Wt.png")));
        }else if (tile.water1 && tile.water3 && tile.water7 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W1379.png")));
        }else if (tile.water1 && tile.water3 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W137.png")));
        }else if (tile.water1 && tile.water3 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W139.png")));
        }else if (tile.water1 && tile.water7 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W179.png")));
        }else if (tile.water3 && tile.water7 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W379.png")));
        }else if (tile.water1 && tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W19.png")));
        }else if (tile.water3 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W37.png")));
        }else if (tile.water1 && tile.water3){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W13.png")));
        }else if (tile.water9 && tile.water3){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W39.png")));
        }else if (tile.water9 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W79.png")));
        }else if (tile.water1 && tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W17.png")));
        }else if (tile.water1){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W1111.png")));
        }else if (tile.water3){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W3333.png")));
        }else if (tile.water7){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W7777.png")));
        }else if (tile.water9){
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/W9999.png")));
        }

        // Add more conditions depending on the flags, determining which sprite to use
        // Example:
        // - tile.water1 && tile.water4: could indicate a corner water sprite
        // - Multiple water flags true could indicate an edge or corner type

    }

    public void StoneReplacer(){
        int radius2 = (int)(((double) Math.min(maxX, maxY) /2)*0.25);
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int distance = (int) Math.sqrt(Math.pow(x - (double) maxX /2, 2) + Math.pow(y - (double) maxY /2, 2));
                if(distance < radius2+3){
                    if (tile[x][y].image == stoneImage) {
                        //place stone
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2, Player p) {
        int playerX = p.getX();
        int playerY = p.getY();

        int tileSize = map.getTileSize();

        int playerTileX = findTile(p.getX(), tileSize);
        int playerTileY = findTile(p.getY(), tileSize);

        // Player's tile index on the map

        // Player's offset within the current tile
        int offsetX = playerX % tileSize;
        int offsetY = playerY % tileSize;

        // Adjust screen position so that the player appears to move smoothly within the tile
        for (int i = (-map.getMaxScreenCol() / 2) - 1; i <= map.getMaxScreenCol() / 2 + 1; i++) {
            for (int j = -(map.getMaxScreenRow() / 2) - 1; j <= map.getMaxScreenRow() / 2 + 1; j++) {
                int tileX = playerTileX + i;
                int tileY = playerTileY + j;

                // Only draw if the tile is within bounds
                if (tileX >= 0 && tileX < maxX - 1 && tileY >= 0 && tileY < maxY - 1) {
                    // Calculate where to draw the tile relative to the player's position and screen offset
                    int drawX = (i * tileSize) - offsetX + playerX;
                    int drawY = (j * tileSize) - offsetY + playerY;


                    // Adjust tile size by 1 pixel to prevent gaps
                    g2.drawImage(tile[tileX][tileY].image, drawX, drawY, tileSize + 1, tileSize + 1, null);
                }
            }
        }
    }
    public void stupThing(){



    }





    public int findTile(int x, int y){
        return x /y; /*(int)(Math.ceil((double) x /y));*/
    }

}
