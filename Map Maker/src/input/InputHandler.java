package input;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler extends KeyAdapter {

    private static boolean up, down, left, right;
    private static Point mousePos;

    private static MouseAdapter mA = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);

        }
    };

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
            up = true;
        }
        if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
            down = true;
        }
        if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
            left = true;
        }
        if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
            up = false;
        }
        if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
            down = false;
        }
        if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
            left = false;
        }
        if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    public static MouseAdapter getMouseAdapter() {
        return mA;
    }

    public static void setMousePos(Point p) {
        mousePos = p;
    }

    public static Point getMousePos() {
        return mousePos;
    }

    public static boolean upPr() {
        return up;
    }

    public static boolean downPr() {
        return down;
    }

    public static boolean leftPr() {
        return left;
    }

    public static boolean rightPr() {
        return right;
    }

}
