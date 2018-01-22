package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    private BufferedImage texture;
    private String path;
    private String name;
    private boolean solid; // Is true if the tile acts like a solid.
    private boolean overlay; // Is true if the tile goes the top layer.
    private int underlayID;

    public Tile(File texture) throws IOException {
        if(texture.isFile()) {
            this.texture = ImageIO.read(texture);
            path = texture.getPath(); // The path of the image.
            name = texture.getName(); // The name of the image.
        }
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setOverlay(boolean overlay) {
        this.overlay = overlay;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isOverlay() {
        return overlay;
    }

    public void setUnderlayID(int id) {
        this.underlayID = id; // Sets the underlay image id.
    }

    public int getUnderlayID() {
        return underlayID; // Returns the underlay image id.
    }

    public BufferedImage getTexture() {
        return texture; // Returns the tile texture.
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
