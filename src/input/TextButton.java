package input;

import java.awt.*;

public class TextButton extends Button {

    private String text; // The text that is shown on the button.
    private Font font;

    public TextButton(String text) {
        this.text = text;
        font = new Font("Arial", Font.PLAIN, 20);
    }

    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(font);
    }
}
