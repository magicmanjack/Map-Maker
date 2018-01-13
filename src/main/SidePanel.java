package main;

import input.FileSelection;
import input.IconButton;
import input.TextButton;
import input.TileSelectionPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SidePanel {

    public static final int X = 800, // Sets the panel position so that it is on the left side of the screen.
            EDGE_SEP = 15, // The distance between the sides of the panel and the edge of the screen.
            EDGE_ARC = 40, // The curvature of the corners.
            Y = EDGE_SEP,
            W = Main.WIDTH - X - EDGE_SEP,
            H = Main.HEIGHT - EDGE_SEP * 2;
    private TextButton ldMapBtn; // The load map button.
    private TextButton svMapBtn; // The save map button.
    private TextButton ldTileArrBtn; // A button to load a tile arrangement.
    private TextButton svTileArrBtn; // A button to save the current tile arrangement.
    private TileSelectionPanel tsBtn;
    private IconButton mvUpBtn; // Button for scrolling up the tile selection.
    private IconButton mvDownBtn; // Button for scrolling down the tile selection.

    private static BufferedImage mvUpIcon;
    private static BufferedImage mvDownIcon;

    static {
        try {
            mvUpIcon = ImageIO.read(SidePanel.class.getResourceAsStream("/move_up.png"));
            mvDownIcon = ImageIO.read(SidePanel.class.getResourceAsStream("/move_down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SidePanel() {
        ldMapBtn = new TextButton(X + EDGE_SEP, Y + 10, 150, 30, "Load Map");
        ldMapBtn.setTxtOffsetX(25);
        svMapBtn = new TextButton(X + EDGE_SEP, Y + 50, 150, 30, "Save Map");
        svMapBtn.setTxtOffsetX(25);
        ldTileArrBtn = new TextButton(X + EDGE_SEP, Y + 90, 150, 30, "Load Tile Arr");
        svTileArrBtn = new TextButton(X + EDGE_SEP, Y + 130, 150, 30, "Save Tile Arr");
        mvUpBtn = new IconButton(X + EDGE_SEP, Y + 180, 150, 20, mvUpIcon);
        tsBtn = new TileSelectionPanel(X + EDGE_SEP, Y + 200, 150, 300, 10);
        mvDownBtn = new IconButton(X + EDGE_SEP, Y + 500, 150, 20, mvDownIcon);
    }

    public void update() {
        ldMapBtn.update();
        svMapBtn.update();
        ldTileArrBtn.update();
        svTileArrBtn.update();
        mvUpBtn.update();
        mvDownBtn.update();

        if(ldMapBtn.isPressed()) {
            FileSelection.getSelection();
        }
        if(ldTileArrBtn.isPressed()) {
            tsBtn.loadTileArr(FileSelection.getSelection());
        }
        if(mvUpBtn.isPressed()) {
            tsBtn.setScrollOffset(tsBtn.getScrollOffset() - 1);
        }
        if(mvDownBtn.isPressed()) {
            tsBtn.setScrollOffset(tsBtn.getScrollOffset() + 1);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(X, Y, W, H, EDGE_ARC, EDGE_ARC); // Draws the panel.
        ldMapBtn.draw(g);
        svMapBtn.draw(g);
        ldTileArrBtn.draw(g);
        svTileArrBtn.draw(g);
        mvUpBtn.draw(g);
        tsBtn.draw(g);
        mvDownBtn.draw(g);
    }

}
