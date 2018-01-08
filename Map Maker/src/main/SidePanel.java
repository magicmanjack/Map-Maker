package main;

import java.awt.*;

public class SidePanel {

    private final int X = 800, Y = 0, W = 400, H = 600;

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(X, Y, W, H);
    }

}
