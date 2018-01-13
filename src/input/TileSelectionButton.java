package input;

import main.SidePanel;
import main.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileSelectionButton extends Button {

    private BufferedImage tileTexure;
    private String name = "";
    private Font font;
    private boolean suspended = false;

    public TileSelectionButton(int x, int y, int w, int h) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
        font = new Font("Arial", Font.ITALIC, 15);
        suspended = true;
    }

    public void setTileDisplayed(Tile t) {
        suspended = false;
        tileTexure = t.getTexture(); // Sets the button image to the tile texture.
        if(t.getPath() != null) {
            name = t.getName(); // Sets the text on the button to the path of the texture.
        }else {
            name = "void"; // Sets an empty tile to a void tile.
        }
    }

    public void suspend() {
        suspended = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(x, y, w, h);
        if(!suspended) {
            g.drawImage(tileTexure, x, y, h, h, null); // Draws an icon that is the height of the button.
            if (name == "void") {
                g.setColor(Color.BLACK);
                g.fillRect(x, y, h, h); // Draws a black tile if the tile is void.
            }
            g.drawString(name, x + h + SidePanel.EDGE_SEP, y + (h / 3) * 2); // Draws the file path next to the tile image.
            if(isPressed()) {
                g.setColor(Color.GRAY);
                g.fillRect(x, y, w, h);
            }
        }
    }

}
