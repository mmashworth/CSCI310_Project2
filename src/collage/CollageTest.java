package collage;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collage.Collage;
import collage.Picture;

public class CollageTest {
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
}
