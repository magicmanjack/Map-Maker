package main;

import input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1000, HEIGHT = 600, FPS = 60;
    private boolean running;
    private JFrame frame;
    private Point mousePoint;
    private SidePanel sp;
    private TileMap map;

    private static Main m;

    public Main() {
        running = false;
        frame = new JFrame("Map maker");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                requestFocus();
            }
        });

        addKeyListener(new InputHandler());
        addMouseListener(new InputHandler());
        addMouseWheelListener(new InputHandler());

        sp = new SidePanel();
        map = new TileMap(10, 10);
    }

    public static void main(String[] args) {
       m = new Main();
       m.start();
    }

    private void start() {
        running = true;
        new Thread(this).start();
    }

    private void stop() {
        if(running) {
            running = false;
        }
    }

    @Override
    public void run() {
        double now = System.nanoTime(), then = now, delta = 0,
                nsPerTick = 1000000000.0d/FPS;
        long lastTime = System.currentTimeMillis();
        int updates = 0, frames = 0, printDelay = 4000;
        boolean canRender = false;
        while(running) {
            canRender = false;
            now = System.nanoTime();
            delta += (now - then) / nsPerTick;
            then = now;

            while(delta > 0) {
                updates++;
                update();
                delta--;
                canRender = true;
            }

            if(canRender) {
                frames++;
                render();
            }

            if(System.currentTimeMillis() - lastTime >= printDelay) {
                lastTime += printDelay;
                System.out.printf("Updates: %d, Frames: %d\n",
                        updates / (printDelay / 1000),
                        frames / (printDelay / 1000));
                updates = 0;
                frames = 0;
            }
        }
    }

    private void update() {
        // Mouse position is passed to the input handler.
        mousePoint = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePoint, this);
        InputHandler.setMouseX(mousePoint.x);
        InputHandler.setMouseY(mousePoint.y);
        // End.
        map.update();
        sp.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        draw(g);
        bs.show();
        g.dispose();
    }

    private void draw(Graphics g) {
        map.draw(g);
        sp.draw(g);
    }

    public SidePanel getSidePanel() {
        return sp;
    }

    public TileMap getMap() {
        return map;
    }

    public static Main getInstance() {
        return m; // Returns instance of main class.
    }

}