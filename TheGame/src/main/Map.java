package main;

public class Map {
    static final int OriginalSize = 32; // this is how detailed stuff is
    static final int scale = 2;  //it's a multiplyer to scale our 2D art

    final static int tileSize = OriginalSize * scale; // making 128 tiles.
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Map(){


    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }
    public int getMaxScreenRow() {
        return maxScreenRow;
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
