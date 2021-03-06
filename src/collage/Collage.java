package collage;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import collage.Picture;
import googleTesting.Searching;


public final class Collage {
	boolean savedCollage = false;
	
	public void saveCollage() {
		savedCollage = true;
	}
	public boolean getSaveCollage() {
		return savedCollage;
	}
	
	public List<String> getUrls(String topic) {
		
		Searching newSearch = new Searching();
		List<String> urls = new ArrayList<String>();
		try {
			urls = newSearch.searchQuery(topic);
		} catch (Exception e) {
		}
		return urls;
	}
	public List<Integer> getAngles() {
		List<Integer> angles = new ArrayList<Integer>();
		for (int i = 0; i < 30; i++) {
			Random rand = new Random();
			int input = rand.nextInt(91) - 45;
			angles.add(input);
		}
		return angles;

	}
	
	
	public static Picture make30Collage (int new_width, int new_height, List<String> nameList, 
									    List<Integer> angelList, boolean rotations, boolean borders) 
	{
		if(!rotations) {
			for(int i=0; i<angelList.size(); i++) {
				angelList.set(i, Integer.valueOf(0));
			}
		}
		
		
		Picture pictNew = new Picture (new_width, new_height);
		System.out.println(nameList.size());
		int row = 5;
		int col = 6;
		
		// decide the width and height of each component picture
		// each is 1/20th of the new picture
		int width = (int)Math.ceil(new_width / 5);
		int height = (int)Math.ceil(new_height / 4);
		// System.out.println("width: " + width + ", height: " + height);
		
		List<Picture> pictList = new ArrayList<Picture>();
		for (int k = 0; k < nameList.size(); k++) {
			String name = nameList.get(k);
			Picture pict = new Picture(width, height, name);
			
			if(borders)
				pict.addFrame();
			// rotate the picture to angel degree
			int angel = angelList.get(k).intValue();
			if ( angel != 0 )
				pict.rotateImage(angel);
			
			pictList.add(pict);
		}
		
		// decide the area each component picture needs to cover
		int cw = (int)Math.ceil(new_width / col);
		int ch = (int)Math.ceil(new_height / row);
		int diffw = (int)Math.ceil((width - cw) / 4);
		int diffh = (int)Math.ceil((height - ch) / 4);
		
		// System.out.println(cw + "," + ch + "," + diffw + "," + diffh);
		
		// determine start position of each picture
		List<Integer> x_arr = new ArrayList<Integer>();
		List<Integer> y_arr = new ArrayList<Integer>();

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				int x = cw * i;
				x -= (int)(Math.random() * diffw);
				x_arr.add(x);
				int y = ch * j;
				y -= (int)(Math.random() * diffh);
				y_arr.add(y);
			}
		}
		
		// shuffle pictures
		int count = 30;
		int[] orderList = new int[count];
		for (int k = 0; k < count; k++) {
			orderList[k] = k;
		}
		for (int i = 0; i < count; i++) {
			int j = (int)(Math.random() * count); // Get a random index out of count
		    int temp = orderList[i]; // Swap
		    orderList[i] = orderList[j];
		    orderList[j] = temp;
		}
		
		// add each component picture to new picture
		for (int n = 0; n < count; n++) {
			int k = orderList[n];
			//System.out.println(k);
			//System.out.println(pictList.size());

			int idx = k % pictList.size();
			Picture pict = pictList.get(idx);

	
			int start_x = x_arr.get(k).intValue();
			int start_y = y_arr.get(k).intValue();
			start_x -= pict.getXPos();
			start_y -= pict.getYPos();
			
			//System.out.println("start " + n + ":" + start_x + ", " + start_y);
			//System.out.println(pict.getXPos() + ", " + pict.getYPos());
			
			for (int i = 0; i < pict.getWidth(); i++) {
				int x = i + start_x;
				for (int j = 0; j < pict.getHeight(); j++) {
					int y = j + start_y;
					int pixel = pict.getPixel(i, j);
					// System.out.println("*** " + i + " " + j + " " + pixel);
					if ( pixel != 0 )
						pictNew.setPixel(x, y, pixel);
				}
			}
			
		}
		return pictNew;
	}
}