import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CollageTestMain {
	public static void main(String s[]) {
        JFrame f = new JFrame("Collage Test");
        
        CollageTest ct = new CollageTest();
        f.add("Center", ct);
        f.pack();
        f.setVisible(true);
        
        BufferedImage bimage = (BufferedImage) f.createImage(800, 600);
        
        File outputfile = new File("image.jpg");
        try {
			ImageIO.write(bimage, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
