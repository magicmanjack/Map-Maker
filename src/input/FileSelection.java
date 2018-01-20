package input;

import javax.swing.*;
import java.io.*;
import java.util.Formatter;

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
        // Gets the user to select or create a file to save into.
        fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File svdFile = fc.getSelectedFile();
            try {
               Formatter fmt = new Formatter(svdFile);
               fmt.format(contents); // Writes the file contents to the file.
               fmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
