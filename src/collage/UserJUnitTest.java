package collage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collage.User.UserClass;

public class UserJUnitTest {

	@Test
	public void testPrevCollagesAfterSearches() {
		Picture newSearch = new Picture(2,2);
		UserClass.setCurrentCollage(newSearch);
		UserClass.numPreviousSearches++;
		assertTrue(UserClass.getNumPreviousSearches() == 0);
		assertTrue(UserClass.getCollages().size() == 0);
		Picture newSearch2 = new Picture(2,2);
		UserClass.addPreviousCollage();
		UserClass.numPreviousSearches++;
		assertTrue(UserClass.getNumPreviousSearches() == 1);
		UserClass.setCurrentCollage(newSearch2);
		assertTrue(UserClass.getCollages().size() == 1);
		assertTrue(UserClass.currentCollage == newSearch2);
		
	}

}
