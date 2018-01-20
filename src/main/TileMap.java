package main;

import input.TileSelectionPanel;

import java.awt.*;

public class TileMap {

    private int tiles[][];
    private int width, height;
    private static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
    private boolean gridEnabled;

    public TileMap(int width, int height) {
        tiles = new int[width][height]; // Creates a two dimensional array of tile ID.
        this.width = width;
        this.height = height;
        gridEnabled = false;
    }

    public void toggleGrid() {
        gridEnabled = !gridEnabled;
    }

    public void draw(Graphics g) {
        for(int ix = 0; ix < width; ix++) {
           for(int iy = 0; iy < height; iy++) {
               TileSelectionPanel t = Main.getInstance().getSidePanel().getTileSelectionPanel();
               if(t.getTileFromID(tiles[ix][iy]) != null) {
                   g.drawImage(t.getTileFromID(tiles[ix][iy]).getTexture(),
                           ix * TILE_WIDTH,
                           iy * TILE_HEIGHT,
                           TILE_WIDTH,
                           TILE_HEIGHT,
                           null); // Draws the tile texture.
               }
               if(gridEnabled) {
                   g.setColor(Color.WHITE);
                   g.drawRect(ix * TILE_WIDTH, iy * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT); // Draws a white grid.
               }
           }
        }
    }
}
