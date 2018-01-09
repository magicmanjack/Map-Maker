package input;

import com.sun.prism.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Textures {

    public static BufferedImage tileArr[] = new BufferedImage[1];

    public static void ldTileArr(String dir) {
        try {
            Scanner s = new Scanner(new File(dir));
            //
            tileArr = new BufferedImage[Integer.parseInt(s.nextLine())];
            for(int i = 1; s.hasNext(); i++) {
                tileArr[i] = ImageIO.read(Textures.class.getResourceAsStream(s.nextLine()));
            }
            //
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
