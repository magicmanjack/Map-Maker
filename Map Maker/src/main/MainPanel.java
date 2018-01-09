package main;

import input.InputHandler;

import java.awt.*;

public class MainPanel {

    private int offsetX, offsetY, scrollSpeed;
    private TileMap m;

    public MainPanel() {
        m = new TileMap(10, 10);
        offsetX = (Main.WIDTH - 400) / 2 - (m.getWidth() * TileMap.T_WIDTH / 2);
        offsetY = Main.HEIGHT / 2 - (m.getHeight() * TileMap.T_HEIGHT) / 2;
        scrollSpeed = 5;
    }

    public void update() {
        if(InputHandler.upPr()) {
            offsetY += scrollSpeed;
        }
        if(InputHandler.downPr()) {
            offsetY -= scrollSpeed;
        }
        if(InputHandler.leftPr()) {
            offsetX += scrollSpeed;
        }
        if(InputHandler.rightPr()) {
            offsetX -= scrollSpeed;
        }
    }

    public void draw(Graphics g) {
        for(int ix = 0; ix < m.getWidth(); ix++) {
            for(int iy = 0; iy < m.getHeight(); iy++) {
                g.setColor(Color.WHITE);
                g.drawRect((ix * TileMap.T_WIDTH) + offsetX,
                        (iy * TileMap.T_HEIGHT) + offsetY,
                        TileMap.T_WIDTH,
                        TileMap.T_HEIGHT);
            }
        }
    }

}
