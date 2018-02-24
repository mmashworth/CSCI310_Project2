import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

public class CollageTestMain {
	public static void main(String s[]) {
        JFrame f = new JFrame("Collage Test");
        
        CollageTest ct = new CollageTest();
        f.add("Center", ct);
        f.pack();
        f.setVisible(true);
    }
}
