/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.io.*;
import java.net.*;
import java.util.Random;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;

class CollageTest extends Component {
    private BufferedImage[] images;
    private URL[] imageSrcs;
    private int width, height;
    
    public CollageTest () {
    		images = new BufferedImage[30];
    		imageSrcs = new URL[30];
	    	try {
	    		for(int i = 0; i < 30; i++) {
	    			imageSrcs[i] = new URL("https://i.ytimg.com/vi/SfLV8hD7zX4/maxresdefault.jpg"); //in real case the URL will be different in every iteration of the loop
	    			images[i] = ImageIO.read(imageSrcs[i]);
	    			width = 800;
	    			height = 600;
        			images[i] = getScaledImage(images[i], width/5, height/5); //scale the image to make it smaller
	    		}
	         
	    } catch (MalformedURLException e) {
	    }
        catch (IOException e) {
            System.out.println("Image could not be read");
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
    
    public void paint(Graphics g) {
        for(int i = 0; i < 5; i++) {
        		for(int j = 0; j < 6; j++) {
        			AffineTransform identity = new AffineTransform();

        			Graphics2D g2d = (Graphics2D)g;
        			AffineTransform trans = new AffineTransform();
        			trans.setTransform(identity);
        			trans.translate(j*width/6, i*height/5); //position
        			
        			Random rand = new Random(); 
        			int angles = rand.nextInt(91) - 45; 
        			
        			trans.rotate( Math.toRadians(angles) );
        			g2d.drawImage(images[i], trans, this);
        			
        			
        			//g.drawImage(images[i], j*width/6, i*height/5, (1+j)*width/6, (1+i)*height/5, 0, 0, width/6, height/5, null);
//the parameters are (source image, destination upper-left corner x, destination upper-left corner y, destination lower-right corner x, destination lower-right corner y, source upper-left corner x, source upper-left corner y, source lower-right corner x, source lower-right corner y, just-put-null)
        		}
        }
    }
}

