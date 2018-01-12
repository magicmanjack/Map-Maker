package input;

import main.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TileSelectionPanel extends Button {

    private int numTiles;
    private Tile tileArr[];
    private Tile tilesShown[];

    public TileSelectionPanel(int x, int y, int w, int h) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
    }

    public void loadTileArr(File f) {
        try {
            Scanner fileReader = new Scanner(f);

            numTiles = fileReader.nextInt();
            tileArr = new Tile[numTiles];

            tileArr[0] = new Tile(new File(fileReader.nextLine())); // Sets tileArr[0] to empty.
            int i = 1;
            while(fileReader.hasNext()) {
                tileArr[i] = new Tile(new File(fileReader.nextLine()));
                i++;
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {

    }

}
