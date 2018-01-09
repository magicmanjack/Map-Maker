package main;

import com.sun.prism.Texture;
import input.Textures;

import java.awt.*;

public class SidePanel {

    private final int X = 800, Y = 0, W = 400, H = 600;
    private int tileButtonW = 50;
    private int tileButtonH = 50;

    public SidePanel() {
        Textures.ldTileArr("res/test_tile_arrangements.ta"); // CHANGE LATER!!!
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, W, H);
        for(int i = 0; i < Textures.tileArr.length; i++) {
            g.drawImage(Textures.tileArr[i], X + 10, i * (tileButtonH + 10), tileButtonW, tileButtonH, null);
        }
    }

}
