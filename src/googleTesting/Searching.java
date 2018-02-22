package googleTesting;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Searching {
	
	private String key;
	private String cx;
	
	public Searching(){
	    key="AIzaSyDTKzqPi1WvntRYtLHEAy-OzxOLrkOv4hA";		//This is a hardcoded value-- it is the api key
	    cx="016372075374369882146:pkcnxhfl16m";				//This is a hardcoded value-- it is the search engine key
	}
	
	public List<String> searchQuery(String query) throws Exception{	//"Throws exception" is exceptionally shitty coding-- I'll change this later
		List<String> searchResults = new ArrayList<>();
	    String qry=query;											//This is a hardcoded value for testing
	    
	    
	    //I'll make the below for-loop more readable later, but it does the following:
	    //1. take in the key, cx, query parameters
	    //2. add the first 10 terms of the search to the arraylist
	    //3. repeat step 2 for the next 10 terms, and the next 10 terms for a total of 30 results
	    for(int i=1; i<31; i=i+10){
		    URL url = new URL(
		            "https://www.googleapis.com/customsearch/v1?key="+key+ "&cx="+cx+"&q="+ qry + "&alt=json&searchType=image&num=10&start="+i);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Accept", "application/json");
		    BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));
		    String output;
		    while ((output = br.readLine()) != null) {
		        if(output.contains("\"link\": \"")){                
		            String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
		            searchResults.add(link);
		        }
		    }
		    conn.disconnect();
	    }
	    
		return searchResults;	//searchResults is returned as the url to the picture of the image
		
								//NOTE: It is possible to get images of specific sizes! However, to give a working example,
								//the images currently returned will be of various sizes.
	}
}