package main;

import input.InputHandler;
import input.TileSelectionPanel;

import java.awt.*;

public class TileMap {

    private int tiles[][];
    private int width, height;
    private static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
    private boolean gridEnabled;
    private int offsetX, offsetY, scrollSpeed, focusX, focusY;
    private float scaleFactor;

    public TileMap(int width, int height) {
        tiles = new int[width][height]; // Creates a two dimensional array of tile ID.
        this.width = width;
        this.height = height;
        gridEnabled = true; // Enables the grid by default.
        scaleFactor = 1;
        focusX = (width * TILE_WIDTH) / 2;
        focusY = (height * TILE_HEIGHT) / 2;
        offsetX = (SidePanel.X / 2) - scale(focusX);
        offsetY = (Main.HEIGHT / 2) - scale(focusY);
        scrollSpeed = 5;
    }

    public void toggleGrid() {
        gridEnabled = !gridEnabled; // Disables or enables the grid.
    }

    public void update() {

        zoom();

        if(InputHandler.upPressed()) {
            focusY -= scrollSpeed;
        }
        if(InputHandler.downPressed()) {
            focusY += scrollSpeed;
        }
        if(InputHandler.leftPressed()) {
            focusX -= scrollSpeed;
        }
        if(InputHandler.rightPressed()) {
            focusX += scrollSpeed;
        }

        offsetX = (SidePanel.X / 2) - scale(focusX);
        offsetY = (Main.HEIGHT / 2) - scale(focusY);

        if(InputHandler.getMouseDown() && mouseHovering()) {
            tiles[(InputHandler.getMouseX() - offsetX) / scale(TILE_WIDTH)][(InputHandler.getMouseY() - offsetY) / scale(TILE_HEIGHT)] = Main.getInstance()
                    .getSidePanel().getTileSelectionPanel().getCurrentSelection(); // Sets the clicked tile to the ID of the current tile selected.
        }
    }

    public void zoom() {
        scaleFactor -= scaleFactor * (0.5 * InputHandler.getWheelChange());
        InputHandler.setWheelChange(0);
    }

    public boolean mouseHovering() {
        int mX = InputHandler.getMouseX();
        int mY = InputHandler.getMouseY();
        return(mX > scale(0) + offsetX && mX < (width * scale(TILE_WIDTH)) + offsetX && mY > scale(0) + offsetY && mY < (height * scale(TILE_HEIGHT)) + offsetY); // Returns true if the mouse is on the tile map.
    }

    public void draw(Graphics g) {
        for(int ix = 0; ix < width; ix++) {
           for(int iy = 0; iy < height; iy++) {
               TileSelectionPanel t = Main.getInstance().getSidePanel().getTileSelectionPanel();
               if(t.getTileFromID(tiles[ix][iy]) != null) {
                   g.drawImage(t.getTileFromID(tiles[ix][iy]).getTexture(),
                           (ix * scale(TILE_WIDTH)) + offsetX,
                           (iy * scale(TILE_HEIGHT)) + offsetY,
                           scale(TILE_WIDTH),
                           scale(TILE_HEIGHT),
                           null); // Draws the tile texture.
               }
               if(gridEnabled) {
                   g.setColor(Color.WHITE);
                   g.drawRect((ix * scale(TILE_WIDTH)) + offsetX,
                           (iy * scale(TILE_HEIGHT)) + offsetY,
                           scale(TILE_WIDTH),
                           scale(TILE_HEIGHT)); // Draws a white grid.
               }
           }
        }
    }

    public int scale(int num) {
        return Math.round(num * scaleFactor);
    }
}
