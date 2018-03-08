package collage;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collage.Collage;
import collage.Picture;

public class CollageJUnitTest {
	
	//test for getUrls()
	@Test
	public void testGetCatUrls() {
		Collage collage = new Collage();
		List<String> urls = collage.getUrls("cat");
		assertTrue(urls.size() == 30);
	}

	//test for getUrls()
	@Test
	public void testGetDogUrls() {
		Collage collage = new Collage();
		List<String> urls = collage.getUrls("dog");
		assertTrue(urls.size() == 30);
	}
	
	//test for getUrls()
	@Test
	public void testGetHappyDogUrls() {
		Collage collage = new Collage();
		List<String> urls = collage.getUrls("happy dog");
		assertTrue(urls.size() == 30);
	} 
	
	//test for getAngles
	@Test
	// tests getAngles returns 30 angles
	public void testGetAnglesSize() {
		Collage collage = new Collage();
		List<Integer> testAngles = collage.getAngles();
		assertTrue(testAngles.size() == 30);
		

	}
	
	//test for getAngles
	@Test
	// gets getAngles returns random angles between -45 and 45
	public void testGetAnglesRange() {
		Collage collage = new Collage();
		List<Integer> testAngles = collage.getAngles();
		boolean inRange = true;
		for (int i = 0; i < testAngles.size(); i++) {
			if (testAngles.get(i) < -45 || testAngles.get(i) > 45) {
				inRange = false;
			}
		}
		
		assertTrue(inRange);
	}
}
