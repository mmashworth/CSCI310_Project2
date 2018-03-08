package googleTesting;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class SearchingJUnit {

	@Test
	// test for searchQuery() in Searching class
	public void testSearchQueryDog() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.searchQuery("dog");
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// test for searchQuery() in Searching class
	public void testSearchQueryTree() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.searchQuery("tree");
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// test for searchQuery() in Searching class
	public void testSearchQueryHappyDog() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.searchQuery("happy dog");
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// test for fastSearch() in Searching class
	public void testFastSearchDog() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.fastSearch("dog",30);
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// test for fastSearch() in Searching class
	public void testFastSearchTree() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.fastSearch("tree",30);
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// test for fastSearch() in Searching class
	public void testFastSearchHappyDog() {
		Searching searchingTest = new Searching();
		try {
			List<String> urls = searchingTest.fastSearch("happy dog",30);
			assertTrue(urls.size() == 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
