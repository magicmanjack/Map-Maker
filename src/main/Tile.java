package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    private BufferedImage texture;
    private String path;

    public Tile(File texture) throws IOException {
        if(texture.isFile()) {
            this.texture = ImageIO.read(texture);
            path = texture.getPath(); // The path of the image.
        }
    }

    public BufferedImage getTexture() {
        return texture; // Returns the tile texture.
    }

    public String getPath() {
        return path;
    }
}
