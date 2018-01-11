package input;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener {

    private static int mouseX, mouseY; // The X and Y coordinates of the mouse pointer.
    private static boolean mouseDown;

    // Start of keyboard input.

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // End of keyboard input.
    // Start of mouse input.

    public static void setMouseX(int xPos) {
        mouseX = xPos;
    }

    public static void setMouseY(int yPos) {
        mouseY = yPos;
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static boolean getMouseDown() {
        return mouseDown;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // End of mouse input.

}
