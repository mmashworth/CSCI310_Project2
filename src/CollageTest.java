import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Random;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;

import googleTesting.Searching;

class CollageTest extends Component {
    private BufferedImage[] images;
    private URL[] imageSrcs;
    private int width, height;
    
    public CollageTest () {
    		width = 800;
		height = 600;
    		Searching test = new Searching();
		List<String> printDogs;
		try {
			printDogs = test.searchQuery("cat");
			printDogs.forEach(System.out::println);
	    		images = new BufferedImage[30];
	    		imageSrcs = new URL[30];
	    	
	    		for(int i = 0; i < 30; i++) {
	    			imageSrcs[i] = new URL(printDogs.get(i)); 

	    			images[i] = ImageIO.read(imageSrcs[i]);
        			images[i] = getScaledImage(images[i], width/5, height/5); //scale the image to make it smaller
	    		}
	         
	    } catch (MalformedURLException e) {
	    	}
        catch (IOException e) {
            System.out.println("Image could not be read");
        }
	    	catch (Exception e1) {
	    		e1.printStackTrace();
	    	}
    }


    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth  = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double)width/imageWidth;
        double scaleY = (double)height/imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
            image,
            new BufferedImage(width, height, image.getType()));
    }
    
    @Override
    public void paint(Graphics g) {
		int counter = 0;
        for(int i = 0; i < 5; i++) {
        		for(int j = 0; j < 6; j++) {
        			AffineTransform identity = new AffineTransform();

        			Graphics2D g2d = (Graphics2D)g;
        			AffineTransform trans = new AffineTransform();
        			trans.setTransform(identity);
        			trans.translate(j*width/6, i*height/5); //position
        			
        			int angle = getRandomAngle();
        			
        			trans.rotate( Math.toRadians(angle) );
        			g2d.drawImage(images[counter], trans, this);
        			//g.drawImage(images[counter], j*width/6, i*height/5, (1+j)*width/6, (1+i)*height/5, 0, 0, width/6, height/5, null);
//the parameters are (source image, destination upper-left corner x, destination upper-left corner y, destination lower-right corner x, destination lower-right corner y, source upper-left corner x, source upper-left corner y, source lower-right corner x, source lower-right corner y, just-put-null)
        			++counter;
        		}
        }
    }
    
    public int getRandomAngle() {
    		Random rand = new Random();
    		return rand.nextInt(91) - 45;
    }
}

