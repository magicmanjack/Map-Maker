package main;

import input.InputHandler;
import input.TileSelectionPanel;

import java.awt.*;

public class TileMap {

    private int tiles[][];
    private int width, height;
    private static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
    private boolean gridEnabled;
    private int offsetX, offsetY, scrollSpeed;
    private float scaleFactor;

    public TileMap(int width, int height) {
        tiles = new int[width][height]; // Creates a two dimensional array of tile ID.
        this.width = width;
        this.height = height;
        gridEnabled = true; // Enables the grid by default.
        scaleFactor = 1;
        offsetX = (SidePanel.X / 2) - scale((width * TILE_WIDTH) / 2);
        offsetY = (Main.HEIGHT / 2) - scale((height * TILE_HEIGHT) / 2);
        scrollSpeed = 5;
    }

    public void toggleGrid() {
        gridEnabled = !gridEnabled; // Disables or enables the grid.
    }

    public void update() {
        if(InputHandler.upPressed()) {
            offsetY += scrollSpeed;
        }
        if(InputHandler.downPressed()) {
            offsetY -= scrollSpeed;
        }
        if(InputHandler.leftPressed()) {
            offsetX += scrollSpeed;
        }
        if(InputHandler.rightPressed()) {
            offsetX -= scrollSpeed;
        }
        if(InputHandler.getMouseDown() && mouseHovering()) {
            tiles[(InputHandler.getMouseX() - offsetX) / TILE_WIDTH][(InputHandler.getMouseY() - offsetY) / TILE_HEIGHT] = Main.getInstance()
                    .getSidePanel().getTileSelectionPanel().getCurrentSelection(); // Sets the clicked tile to the ID of the current tile selected.
        }
    }

    public boolean mouseHovering() {
        int mX = InputHandler.getMouseX();
        int mY = InputHandler.getMouseY();
        return(mX > 0 + offsetX && mX < (width * TILE_WIDTH) + offsetX && mY > 0 + offsetY && mY < (height * TILE_HEIGHT) + offsetY); // Returns true if the mouse is on the tile map.
    }

    public void draw(Graphics g) {
        for(int ix = 0; ix < width; ix++) {
           for(int iy = 0; iy < height; iy++) {
               TileSelectionPanel t = Main.getInstance().getSidePanel().getTileSelectionPanel();
               if(t.getTileFromID(tiles[ix][iy]) != null) {
                   g.drawImage(t.getTileFromID(tiles[ix][iy]).getTexture(),
                           (ix * TILE_WIDTH) + offsetX,
                           (iy * TILE_HEIGHT) + offsetY,
                           TILE_WIDTH,
                           TILE_HEIGHT,
                           null); // Draws the tile texture.
               }
               if(gridEnabled) {
                   g.setColor(Color.WHITE);
                   g.drawRect((ix * TILE_WIDTH) + offsetX, (iy * TILE_HEIGHT) + offsetY, TILE_WIDTH, TILE_HEIGHT); // Draws a white grid.
               }
           }
        }
    }

    public int scale(int num) {
        return Math.round(num * scaleFactor);
    }
}
