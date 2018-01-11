package input;

import main.SidePanel;

import java.awt.*;

public class TextButton extends Button {

    private String text; // The text that is shown on the button.
    private Font font;
    private int txtOffsetX; // The offset of the text (x axis) inside the button.

    public TextButton(int x, int y, int w, int h, String text) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
        this.text = text;
        font = new Font("Arial", Font.BOLD, 20);
        txtOffsetX = 10;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(font);
        g.drawRoundRect(x, y, w, h, SidePanel.EDGE_ARC / 2, SidePanel.EDGE_ARC / 2);
        if(isPressed()) {
            g.setColor(Color.DARK_GRAY); // If the button is clicked then it gets highlighted red.
            g.fillRoundRect(x, y, w, h, SidePanel.EDGE_ARC / 2, SidePanel.EDGE_ARC / 2);
        }
        g.drawString(text, x + txtOffsetX, y + (h / 3) * 2); // A string is drawn and centered in the middle of the button.
    }

    public void setTxtOffsetX(int offset) {
        txtOffsetX = offset;
    }
}
