package main;

public class Map {
    final int OriginalSize = 64; // this is how detailed stuff is
    final int scale = 1;  //it's a multiplyer to scale our 2D art

    final int tileSize = OriginalSize * scale; // making 128 tiles.
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Map(){


    }


    public int getScreenWidth(){
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }
    public int getTileSize(){
        return tileSize;
    }

}
