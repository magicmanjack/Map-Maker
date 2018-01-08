package input;

import java.awt.*;

public class Button {

    private int x, y, w, h;
    private boolean p; // Button pressed.
    private String t; // Button text.

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        p = false;
        t = text;
    }

    public boolean isPressed() {
        return p;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, w, h);
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString(t, x + 2, y + 2);
    }

}
