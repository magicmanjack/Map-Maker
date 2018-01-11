package main;

import input.TextButton;

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

    public SidePanel() {
        ldMapBtn = new TextButton(X + 15, Y + 10, 150, 30, "Load Map");
        ldMapBtn.setTxtOffsetX(25);
        svMapBtn = new TextButton(X + 15, Y + 50, 150, 30, "Save Map");
        svMapBtn.setTxtOffsetX(25);
    }

    public void update() {
        ldMapBtn.update();
        svMapBtn.update();
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(X, Y, W, H, EDGE_ARC, EDGE_ARC); // Draws the panel.
        ldMapBtn.draw(g);
        svMapBtn.draw(g);
    }

}
