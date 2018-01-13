package main;

import input.FileSelection;
import input.TextButton;
import input.TileSelectionPanel;

import java.awt.*;

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
    private TileSelectionPanel tsBtn; //

    public SidePanel() {
        ldMapBtn = new TextButton(X + 15, Y + 10, 150, 30, "Load Map");
        ldMapBtn.setTxtOffsetX(25);
        svMapBtn = new TextButton(X + 15, Y + 50, 150, 30, "Save Map");
        svMapBtn.setTxtOffsetX(25);
        ldTileArrBtn = new TextButton(X + 15, Y + 90, 150, 30, "Load Tile Arr");
        svTileArrBtn = new TextButton(X + 15, Y + 130, 150, 30, "Save Tile Arr");
        tsBtn = new TileSelectionPanel(X + 15, Y + 170, 150, 300, 10); // Change later.
    }

    public void update() {
        ldMapBtn.update();
        svMapBtn.update();
        ldTileArrBtn.update();
        svTileArrBtn.update();

        if(ldMapBtn.isPressed()) {
            FileSelection.getSelection();
        }
        if(ldTileArrBtn.isPressed()) {
            tsBtn.loadTileArr(FileSelection.getSelection());
        }
        tsBtn.setScrollOffset(-2);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(X, Y, W, H, EDGE_ARC, EDGE_ARC); // Draws the panel.
        ldMapBtn.draw(g);
        svMapBtn.draw(g);
        ldTileArrBtn.draw(g);
        svTileArrBtn.draw(g);
        tsBtn.draw(g);
    }

}
