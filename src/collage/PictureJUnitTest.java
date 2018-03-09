package collage;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

public class PictureJUnitTest {

	@Test
	//test setTopic() and getTopic() in Picture class
	public void testSetTopicAndGetTopic() {
		Picture p = new Picture(100, 100, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		p.setTopic("god");
		
		assertTrue(p.getTopic().equals("god"));
	}
	
	@Test
	//Test getHeight() and getWidth() in Picture class
	public void testGetHeightAndGetWidth() {
		Picture p = new Picture(100,100);
		assertTrue(p.getHeight() == 100);
		assertTrue(p.getWidth() == 100);
	}
	
	@Test
	//Test constructor that takes 2 ints
	public void testConstructor() {
		Picture p = new Picture(100,100);
		assertTrue(p.getImage().getWidth() == 100);
		assertTrue(p.getImage().getHeight() == 100);
	}
	
	@Test
	//Test getPixel()
	public void testSetPixel() {
		Picture p = new Picture(100, 100);
		p.setPixel(50, 50, 25);
		assertTrue(p.getPixel(50, 50) == 25);
		p.setPixel(-1, 50, 25);
		p.setPixel(50, -1, 25);
		p.setPixel(1, 200, 25);
		p.setPixel(200, 1, 25);
	}
	
	@Test
	//Test setPixel()
	public void testGetPixel()
	{
		Picture p = new Picture(100, 100);
		p.setPixel(50, 50, 25);
		assertTrue(p.getPixel(50, 50) == 25);
		p.getPixel(-1, 1);
		p.getPixel(1, -1);
		p.getPixel(200, 1);
		p.getPixel(1, 200);
	}
	
	@Test
	//Test getImage() no resize
	public void testGetImageNotResized()
	{
		try {
			URL image_url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
			BufferedImage img = ImageIO.read(image_url);
			int height = img.getHeight();
			int width = img.getWidth();
		
			Picture p1 = new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
			assertTrue(p1.getImage().getHeight() == height);
			assertTrue(p1.getImage().getWidth() == width);
		} catch (IOException e) {
			
		}
	}

	@Test
	//Test getImage() resized
	public void testGetImageResized()
	{
		Picture p1 = new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		assertTrue(p1.getImage(50, 50).getHeight() == 50);
	}
	
	@Test
	//Test setImage()
	public void testSetImage() {
		try {
			URL image_url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
			BufferedImage img = ImageIO.read(image_url);
			int height = img.getHeight();
			int width = img.getWidth();
		
			Picture p1 = new Picture(1,1);
			p1.setImage(img);
			assertTrue(p1.getImage().getHeight() == height);
			assertTrue(p1.getImage().getWidth() == width);
		} catch (IOException e) {
			
		}
	}
	
	@Test
	//Test rotate()
	public void testRotatePositiveAngel() {
		Picture p = new Picture(100, 100, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		int xPos = p.getXPos();
		int yPos = p.getYPos();
		p.rotateImage(20);
		assertTrue(xPos != p.getXPos());
		assertTrue(yPos != p.getYPos());
	}
	
	@Test
	//Test rotate()
	public void testRotateNegativeAngel() {
		Picture p = new Picture(100, 100, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		int xPos = p.getXPos();
		int yPos = p.getYPos();
		p.rotateImage(-20);
		assertTrue(xPos != p.getXPos());
		assertTrue(yPos != p.getYPos());
	}
	
	@Test
	//Test addFrame()
	public void testAddFrame() {
		Picture p = new Picture(100, 100, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		int xPos = p.getXPos();
		int yPos = p.getYPos();
		int height = p.getHeight();
		int width = p.getWidth();
		p.addFrame();
		assertTrue(xPos == p.getXPos());
		assertTrue(yPos == p.getYPos());
		assertTrue(height == p.getHeight());
		assertTrue(width == p.getWidth());
	}
	
	@Test
	//Test writeImage()
	public void testWriteImage() {
		Picture p = new Picture(100, 100, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg/220px-Sabaoth_icon_%28Russia%2C_19_c.%29_2.jpeg");
		p.writeImage("test_image", "png");
		File shouldExist = new File("test_image");
	    assertTrue(shouldExist.exists());
	}
}
