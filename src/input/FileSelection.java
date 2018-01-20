package input;

import javax.swing.*;
import java.io.*;

public class FileSelection {

    private static JFileChooser fc;

    public static File getSelection() {
        fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null); // Opens up a dialogue window for file selection.
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile(); // Returns the file that was selected.
        }
        return null; // Returns null if the operation was cancelled.
    }

    public static void saveFile(String contents) {
        fc = new JFileChooser();
        FileWriter writer;
        int returnVal = fc.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File svdFile = fc.getSelectedFile();
            try {
               writer = new FileWriter(svdFile);
               writer.write(contents);
               writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
