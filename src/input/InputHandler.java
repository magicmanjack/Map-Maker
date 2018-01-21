package input;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseWheelListener {

    private static int mouseX, mouseY; // The X and Y coordinates of the mouse pointer.
    private static boolean mouseDown;
    private static boolean up, down, left, right;
    private static double mWheelChange;

    // Start of keyboard input.

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }

    public static boolean upPressed() {
        return up;
    }

    public static boolean downPressed() {
        return down;
    }

    public static boolean leftPressed() {
        return left;
    }

    public static boolean rightPressed() {
        return right;
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

    public static double getWheelChange() {
        return mWheelChange;
    }

    public static void setWheelChange(double i) {
        mWheelChange = i;
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mWheelChange = e.getPreciseWheelRotation();
    }

    // End of mouse input.

}
