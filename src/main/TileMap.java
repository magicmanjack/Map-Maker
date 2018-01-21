package main;

import input.IconButton;
import input.InputHandler;
import input.TileSelectionPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileMap {

    private int tiles[][];
    private int width, height;
    private static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
    private boolean gridEnabled;
    private int offsetX, offsetY, scrollSpeed, focusX, focusY;
    private float scaleFactor;

    private IconButton addLeft, addRight, addUp, addDown; // The buttons for resizing the tilemap.

    private static BufferedImage addLeftIcon;
    private static BufferedImage addRightIcon;
    private static BufferedImage addUpIcon;
    private static BufferedImage addDownIcon;

    static {
        try {
            addLeftIcon = ImageIO.read(TileMap.class.getResourceAsStream("/add_left.png"));
            addRightIcon = ImageIO.read(TileMap.class.getResourceAsStream("/add_right.png"));
            addUpIcon = ImageIO.read(TileMap.class.getResourceAsStream("/add_up.png"));
            addDownIcon = ImageIO.read(TileMap.class.getResourceAsStream("/add_down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        addLeft = new IconButton(scale(-30) + offsetX,
                scale(((height * TILE_HEIGHT) / 2) - 15) + offsetY,
                scale(30),
                scale(30),
                addLeftIcon);
        addRight = new IconButton(scale(width * TILE_WIDTH) + offsetX,
                scale(((height * TILE_HEIGHT) / 2) - 15) + offsetY,
                scale(30),
                scale(30),
                addRightIcon);
        addUp = new IconButton(scale(((width * TILE_WIDTH) / 2) - 15) + offsetX,
                scale(-30) + offsetY,
                scale(30),
                scale(30),
                addUpIcon);
        addDown = new IconButton(scale(((width * TILE_WIDTH) / 2) - 15) + offsetX,
                scale(height * TILE_HEIGHT) + offsetY,
                scale(30),
                scale(30),
                addDownIcon);
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

        // Buttons start.
        addLeft.update();
        addRight.update();
        addUp.update();
        addDown.update();
        updateButtonsPos();

        if(addLeft.isPressed()) {

        }
        if(addRight.isPressed()) {
            resizeRight(1);
        }
        if(addUp.isPressed()) {

        }
        if(addDown.isPressed()) {

        }
        // Buttons done.
    }

    public void zoom() {
        scaleFactor -= scaleFactor * (0.5 * InputHandler.getWheelChange());
        InputHandler.setWheelChange(0);
    }

    public void updateButtonsPos() {
        // Updates the button to scale and offset.
        addLeft.setX(scale(-30) + offsetX);
        addLeft.setY(scale(((height * TILE_HEIGHT) / 2) - 15) + offsetY);
        addLeft.setW(scale(30));
        addLeft.setH(scale(30));
        addRight.setX(scale(width * TILE_WIDTH) + offsetX);
        addRight.setY(scale(((height * TILE_HEIGHT) / 2) - 15) + offsetY);
        addRight.setW(scale(30));
        addRight.setH(scale(30));
        addUp.setX(scale(((width * TILE_WIDTH) / 2) - 15) + offsetX);
        addUp.setY(scale(-30) + offsetY);
        addUp.setW(scale(30));
        addUp.setH(scale(30));
        addDown.setX(scale(((width * TILE_WIDTH) / 2) - 15) + offsetX);
        addDown.setY(scale(height * TILE_HEIGHT) + offsetY);
        addDown.setW(scale(30));
        addDown.setH(scale(30));
    }

    public boolean mouseHovering() {
        int mX = InputHandler.getMouseX();
        int mY = InputHandler.getMouseY();
        return(mX > scale(0) + offsetX &&
                mX < (width * scale(TILE_WIDTH)) + offsetX && mY > scale(0) + offsetY &&
                mY < (height * scale(TILE_HEIGHT)) + offsetY); // Returns true if the mouse is on the tile map.
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

        // Buttons are drawn after all other tilemap components.
        addLeft.draw(g);
        addRight.draw(g);
        addUp.draw(g);
        addDown.draw(g);
    }

    public void resizeLeft(int i) {

    }

    public void resizeRight(int i) {
        int original[][] = tiles;
        tiles = new int[width + i][height];
        for(int ix = 0; ix < width; ix++) {
            for(int iy = 0; iy < height; iy++) {
                if(ix < tiles.length && iy < tiles[0].length) {
                    tiles[ix][iy] = original[ix][iy];
                }
            }
        }
        System.out.println("!");
        width = tiles.length;
    }

    public void resizeUp(int i) {

    }

    public void resizeDown(int i) {

    }

    public int scale(int num) {
        return Math.round(num * scaleFactor);
    }
}
