package collage;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import collage.Collage;
import googleTesting.Searching;

public class Test extends JPanel {
	private static int p_width = 600;
	private static int p_height = 600;

	   public void paint(Graphics g) {
		   try {
		   example3(g);
		   }
		   catch (Exception e) {
			   
		   }
	   }
	   
	   public void example1(Graphics g) {
		   // show img1
		   Picture pic1 = new Picture("https://res.cloudinary.com/demo/image/upload/sample.jpg");
		   BufferedImage img1 = pic1.getImage(200, 200);
		   g.drawImage(img1, 100, 100,this);

		   // show img2
		   Picture pic2 = new Picture("https://upload.wikimedia.org/wikipedia/commons/9/92/2008-05-04_at_18-26-44-Forgetmenot-Flower.jpg");
		   BufferedImage img2 = pic2.getImage(200, 200);
		   g.drawImage(img2, 200, 200,this);
		   
		   // rotate img1
		   AffineTransform transform = new AffineTransform();
		   transform.setToTranslation(p_width / 2,
		            p_height / 2);
		   int angle = 120;
		   transform.rotate(Math.toRadians(angle), 200 / 2,
		            200 / 2);
		   Graphics2D g2d = (Graphics2D)g;
		   g2d.drawImage(img1, transform, this);		        
	   }
	   
	   public void example2(Graphics g) {
		   Picture pic1 = new Picture(200, 200, "https://res.cloudinary.com/demo/image/upload/sample.jpg");
		   Picture pic2 = new Picture(200, 200, "https://upload.wikimedia.org/wikipedia/commons/9/92/2008-05-04_at_18-26-44-Forgetmenot-Flower.jpg");
		   Picture pic3 = new Picture(200, 200, "https://upload.wikimedia.org/wikipedia/commons/8/81/Leucanthemum_vulgare_%27Filigran%27_Flower_2200px_edit1.jpg");

		   Picture newPict = Collage.makeCollage(pic1, pic2, pic3);
		   BufferedImage img3 = newPict.getImage(400, 400);
		   g.drawImage(img3, 100, 100,this);
	   }
	   
	   public void example3(Graphics g) throws Exception{	
		   /*
		   List<String> nameList = new ArrayList<String>();
		   nameList.add("https://res.cloudinary.com/demo/image/upload/sample.jpg");
		   nameList.add("https://sites.google.com/site/ilikedirt/30853451.Tiffy2-full.jpg");
		   nameList.add("https://upload.wikimedia.org/wikipedia/commons/9/92/2008-05-04_at_18-26-44-Forgetmenot-Flower.jpg");
		   nameList.add("https://upload.wikimedia.org/wikipedia/commons/8/81/Leucanthemum_vulgare_%27Filigran%27_Flower_2200px_edit1.jpg");
			
			*/
		   
		  // Searching test = new Searching();
		  // List<String> nameList = test.searchQuery("dog");
			List<String> nameList = new ArrayList<String>();
			for (int i = 0; i < 30; i++) {
				nameList.add("https://res.cloudinary.com/demo/image/upload/sample.jpg");
			}
		   
		  // List<Integer> angelList = new ArrayList<Integer>();
		  // angelList.add(20);
		   //angelList.add(0);
		   //angelList.add(-20);
		   //angelList.add(10);
		   
		   List<Integer> angleList = new ArrayList<Integer>();
		   for (int i = 0; i < 30; i++) {
			   Random rand = new Random();
			   int input = rand.nextInt(91) - 45;
			   angleList.add(input);
				   
		   }
			   
		   int width = 800;
		   int height = 600;
		   Picture newPict = Collage.make30Collage(width, height, nameList, angleList);
		   newPict.writeImage("saved.png", "png");
		   BufferedImage img3 = newPict.getImage(width, height);
		   g.drawImage(img3, 0, 0,this);
	   }
	   
	   public static void main(String[] args) {
	      JFrame frame = new JFrame();
	      frame.getContentPane().add(new Test());

	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(p_width, p_height);
	      frame.setVisible(true);
	   }
	}
