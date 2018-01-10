package input;

import java.awt.*;

public abstract class Button {

    protected int x, y, w, h;
    protected boolean pressed;

    public void update() {

    }

    public void draw(Graphics g) {

    }

    public boolean isPressed() {
        return pressed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

}
