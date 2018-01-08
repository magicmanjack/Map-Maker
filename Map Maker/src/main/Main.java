package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1200, HEIGHT = 600, FPS = 60;
    private JFrame frame;
    private MainPanel mp;
    private SidePanel sp;
    private boolean running;

    public Main() {
        frame = new JFrame();
        setSize(new Dimension(WIDTH, HEIGHT));
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();

        mp = new MainPanel();
        sp = new SidePanel();
    }

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        new Thread(this).start();
        running = true;
    }

    private void stop() {
        running = false;
    }

    @Override
    public void run() {
        double now = System.nanoTime(), then = now, delta = 0;
        double nsPerTick = 1000000000.0d / FPS;
        long lastTimer = System.currentTimeMillis();
        boolean canRender = false;
        int updates = 0, frames = 0;

        while(running) {
            now = System.nanoTime();
            delta += (now - then) / nsPerTick;
            then = now;
            canRender = false;

            while(delta > 0) {
                updates++;
                delta--;
                update();
                canRender = true;
            }

            if(canRender) {
                frames++;
                render();
            }

            if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.printf("Updates: %d, Frames: %d\n", updates, frames);
                updates = 0;
                frames = 0;
            }

        }
    }

    private void update() {
        mp.update();
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
        mp.draw(g);
        sp.draw(g);
    }

}
