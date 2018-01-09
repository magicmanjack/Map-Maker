package main;

public class TileMap {

    public static final int T_WIDTH = 50, T_HEIGHT = 50;

    private int w, h;
    private int tiles[][];

    public TileMap(int width, int height) {
        tiles = new int[w][h];
        w = width;
        h = height;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getTileAt(int tX, int tY) {
        if(tX >= 0 && tX < w && tY >= 0 && tY < h) {
            return tiles[tX][tY];
        }
        return 0;
    }

    public void writeTileAt(int tX, int tY, int id) {
        if(tX >= 0 && tX < w && tY >= 0 && tY < h) {
            tiles[tX][tY] = id;
        }
    }

}
