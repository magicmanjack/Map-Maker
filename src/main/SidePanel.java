package main;

import java.awt.*;

public class SidePanel {

    private static final int X = 800, // Sets the panel position so that it is on the left side of the screen.
            EDGE_SEP = 15, // The distance between the sides of the panel and the edge of the screen.
            EDGE_ARC = 40, // The curvature of the corners.
            Y = EDGE_SEP,
            W = Main.WIDTH - X - EDGE_SEP,
            H = Main.HEIGHT - EDGE_SEP * 2;

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(X, Y, W, H, EDGE_ARC, EDGE_ARC); // Draws the panel.
    }

}
