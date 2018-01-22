package input;

import main.SidePanel;
import main.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private IconButton propBtns[]; // The properties button for the tiles.
    private int scrollOffset;
    private int currentSelection;
    private static BufferedImage tilePropIcon;

    static {
        try {
            tilePropIcon = ImageIO.read(TileSelectionPanel.class.getResourceAsStream("/tile_properties.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TileSelectionPanel(int x, int y, int w, int h, int rows) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
        tileArr = new Tile[1];

        try {
            tileArr[0] = new Tile(new File("")); // Sets the first tile to a void.
        } catch (IOException e) {
            e.printStackTrace();
        }

        numTiles = tileArr.length;
        numRows = rows;
        rowHeight = h / numRows;
        buttons = new TileSelectionButton[numRows];
        propBtns = new IconButton[numRows];
        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new TileSelectionButton(x, y + (rowHeight * i), w, rowHeight);
            propBtns[i] = new IconButton(x + w, y + (rowHeight * i), 15, rowHeight, tilePropIcon);
        }
        refresh(); // Refreshes the tile buttons.
    }

    @Override
    public void update() {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].update(); // Updates the buttons so they can detect presses.
            propBtns[i].update();
            if(buttons[i].isPressed()) {
                currentSelection = i + scrollOffset;
            }
            if(propBtns[i].isPressed() && i + scrollOffset < tileArr.length) {
                TilePropertiesWindow.openWindow(i + scrollOffset); // Opens property window for the current tile selected.
            }
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
                    if(fileReader.next().equals("s")) {
                        tileArr[i].setSolid(true);
                    }else {
                        tileArr[i].setSolid(false);
                    }
                    if(fileReader.next().equals("o")) {
                        tileArr[i].setOverlay(true);
                    }else {
                        tileArr[i].setOverlay(false);
                    }
                    tileArr[i].setUnderlayID(fileReader.nextInt());
                    fileReader.nextLine();
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

    public void addTile(Tile t) {
        Tile original[] = tileArr;
        tileArr = new Tile[original.length + 1]; // Expands the array by one.
        for(int i = 0; i < tileArr.length; i++) {
            if(i >= original.length) {
                tileArr[i] = t; // Sets the new tile to the file specified.
            }else {
                tileArr[i] = original[i]; // Sets the longer array to the old tiles.
            }
        }
        numTiles = tileArr.length;
        refresh();
    }

    public void removeTile() {
        if(tileArr.length > 1) {
            Tile original[] = tileArr;
            tileArr = new Tile[original.length - 1]; // Shortens the array by one.
            for (int i = 0; i < tileArr.length; i++) {
                tileArr[i] = original[i]; // Sets the shorter array to the original values.
            }
            numTiles = tileArr.length;
            refresh();
        }
    }

    public void setScrollOffset(int offset) {
        if(tileArr != null) { // Makes sure a tile array has been loaded.
            if(offset >= 0 && offset <= tileArr.length) { // Sets an upwards scrolling limit.
                scrollOffset = offset;
                refresh();
            }
        }
    }

    public void refresh() {
        for (int i = 0; i < buttons.length; i++) { // For every button slot...
            if (i + scrollOffset < tileArr.length && i + scrollOffset >= 0) {
                buttons[i].setTileDisplayed(tileArr[i + scrollOffset]); // Sets the current button slot to the offset tile.
            }else {
                buttons[i].suspend(); // Buttons that are not being used go invisible.
            }
        }
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public String createContents() {
        // Returns new file contents of the tile arrangement.
        String contents = "";
        StringBuilder sb = new StringBuilder(contents);
        sb.append(numTiles + "\r\n");
        for(int i = 1; i < numTiles; i++) {
            sb.append(tileArr[i].getPath() + "\r\n" + (tileArr[i].isSolid() ? "s" : "-")
                    + (tileArr[i].isOverlay() ? " o " : " - ")
                    + tileArr[i].getUnderlayID() + "\r\n");
        }
        contents = sb.toString();
        return contents;
    }

    public Tile getTileFromID(int id) {
        if(id < tileArr.length) {
            return tileArr[id];
        }
        return null;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }

    public String[] getTileNames() {
        String names[] = new String[tileArr.length];
        for(int i = 0; i < tileArr.length; i++) {
            names[i] = tileArr[i].getName();
        }
        return names;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(x, y, w, h);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].draw(g);
            propBtns[i].draw(g);
        }
    }

}
