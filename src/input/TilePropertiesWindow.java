package input;

import main.Main;
import main.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TilePropertiesWindow {

    private static JFrame frame;
    private static JPanel panel;
    private static JCheckBox tileIsSolid, tileIsOverlay;
    private static JComboBox setUnderlay;
    private static final Dimension DIM = new Dimension(200, 150);
    private static int tileID;
    private static String[] tileNames;

    public static void openWindow(int i) {
        tileNames = Main.getInstance().getSidePanel().getTileSelectionPanel().getTileNames();
        tileID = i;
        frame = new JFrame();
        frame.setSize(DIM);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        panel = new JPanel();
        tileIsSolid = new JCheckBox("Solid",
                Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).isSolid());
        tileIsSolid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1) {
                    Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).setSolid(true);
                }else {
                    Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).setSolid(false);
                }
            }
        });
        panel.add(tileIsSolid);
        tileIsOverlay = new JCheckBox("Overlay",
                Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).isOverlay());
        tileIsOverlay.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1) {
                    Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).setOverlay(true);
                }else {
                    Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).setOverlay(false);
                }
            }
        });
        panel.add(tileIsOverlay);
        setUnderlay = new JComboBox(tileNames);
        setUnderlay.setSelectedIndex(Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).getUnderlayID());
        setUnderlay.setMaximumRowCount(5);
        setUnderlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                Main.getInstance().getSidePanel().getTileSelectionPanel().getTileFromID(tileID).setUnderlayID(cb.getSelectedIndex());
            }
        });
        panel.add(new JLabel("Choose underlay:"));
        panel.add(setUnderlay);
        frame.add(panel);
        frame.setVisible(true);
    }

}
