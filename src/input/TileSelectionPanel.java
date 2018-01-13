package input;

import main.SidePanel;
import main.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TileSelectionPanel extends Button {

    private int numTiles;
    private int numRows;
    private int rowHeight;
    private Tile tileArr[];
    private TileSelectionButton buttons[];
    private int scrollOffset;

    public TileSelectionPanel(int x, int y, int w, int h, int rows) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
        numRows = rows;
        rowHeight = h / numRows;
        buttons = new TileSelectionButton[numRows];
        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new TileSelectionButton(x, y + (rowHeight * i), w, rowHeight);
        }
    }

    public void loadTileArr(File f) {
        try {
            if(f != null) {
                Scanner fileReader = new Scanner(f);

                numTiles = fileReader.nextInt();
                tileArr = new Tile[numTiles];

                tileArr[0] = new Tile(new File(fileReader.nextLine())); // Sets tileArr[0] to empty.
                int i = 1;
                while (fileReader.hasNext()) {
                    tileArr[i] = new Tile(new File(fileReader.nextLine())); // Creates a new tile for every path specified in the file.
                    i++;
                }

                fileReader.close();
                setScrollOffset(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScrollOffset(int offset) {
        if(tileArr != null) { // Makes sure a tile array has been loaded.
            if(offset >= 0 && offset <= tileArr.length) { // Sets an upwards scrolling limit.
                scrollOffset = offset;
                for (int i = 0; i < buttons.length; i++) { // For every button slot...
                    if (i + offset < tileArr.length && i + offset >= 0) {
                        buttons[i].setTileDisplayed(tileArr[i + offset]); // Sets the current button slot to the offset tile.
                    }else {
                        buttons[i].suspend(); // Buttons that are not being used go invisible.
                    }
                }
            }
        }
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(x, y, w, h);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].draw(g);
        }
    }

}
