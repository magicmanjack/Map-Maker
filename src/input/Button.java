package input;

import java.awt.*;

public abstract class Button {

    protected int x, y, w, h;
    protected boolean pressed; // If this the mouse has pressed this button.
    protected boolean lastMouseDown; // Keeps track of the mousePressed from the previous update.

    public void update() {
        if(pressed) {
            pressed = false;
        }
        if(lastMouseDown && !InputHandler.getMouseDown() && mouseHovering()) {
            pressed = true;
        }
        lastMouseDown = InputHandler.getMouseDown();
    }

    public boolean mouseHovering() {
        int mX = InputHandler.getMouseX();
        int mY = InputHandler.getMouseY();
        return (mX >= x && mX <= x + w && mY >= y && mY <= y + h);
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
