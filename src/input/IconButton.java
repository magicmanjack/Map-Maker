package input;

import main.SidePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IconButton extends Button {

    private static final int ICON_WIDTH = 20, ICON_HEIGHT = 20;
    private BufferedImage icon;

    public IconButton(int x, int y, int w, int h, BufferedImage icon) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
        this.icon = icon;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, w, h, SidePanel.EDGE_ARC / 2, SidePanel.EDGE_ARC / 2);
        g.drawImage(icon, x + (w / 2) - (ICON_WIDTH / 2), y + (h / 2) - (ICON_HEIGHT / 2), ICON_WIDTH, ICON_HEIGHT, null);
        if(isPressed()) {
            g.fillRoundRect(x, y, w, h, SidePanel.EDGE_ARC / 2, SidePanel.EDGE_ARC / 2);
        }

    }

}
