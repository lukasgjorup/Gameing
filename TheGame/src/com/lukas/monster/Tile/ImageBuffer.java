package com.lukas.monster.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ImageBuffer {

    private static final HashMap<String, BufferedImage> stringImageHashMap = new HashMap<>();

    public ImageBuffer() {




    }



    public static BufferedImage getReadImage(String imageTiles) throws IOException {
        BufferedImage bufferedImage = stringImageHashMap.get(imageTiles);
        if (bufferedImage == null) {
            bufferedImage = ImageIO.read(Objects.requireNonNull(ImageBuffer.class.getResourceAsStream(imageTiles)));
            stringImageHashMap.put(imageTiles, bufferedImage);
        }
        return bufferedImage;
    }



}
