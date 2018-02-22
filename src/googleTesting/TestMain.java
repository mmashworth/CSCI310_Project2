package googleTesting;

import java.util.List;

public class TestMain {


	public static void main(String[] args) throws Exception {
		Searching test = new Searching();
		List<String> printDogs = test.searchQuery("dog");	//just swap out the ""'s if you want a different search term
		printDogs.forEach(System.out::println);
	}
}

