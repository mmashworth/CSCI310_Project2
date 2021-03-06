package googleTesting;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//Documentation for Searching: https://pastebin.com/01xTyBQ2

public class Searching {
	
	private String key;
	private String cx;
	

	private int searchCount;		//this is used for fastSearch
	
	public Searching(){
	    key=
	    	"AIzaSyA9PLSIXBLsnQicKbJ39ZmtlAODCrvCC44";
	    	//"AIzaSyAR-346g8d3hDcU9caO9PS2hXdRHuOEF5k";
	    	//"AIzaSyC07Jw7fiw4uHcEiH_rXC6AP0Ohp2COi2E";
	    //"AIzaSyA-x7a0GxK2bs5H8uXAvl0KRF6e_-O-cDo";
	    cx="016372075374369882146:uqrovp3x_6s";				//This is a hardcoded value-- it is the search engine key
	    searchCount = 1;	//Number of searches to make
	}
	
	
	//Will return a List<String> of url's. If there are insufficient URL's, will throw an IOE exception
	//Currently does not return .gif files. Please check to make sure that this is not a requirement
	public List<String> searchQuery(String query) throws IOException {	//"Throws exception" is exceptionally shitty coding-- I'll change this later
	System.out.println("IN QUERY");
		List<String> searchResults = new ArrayList<>(); //The list of results
	    String qry=query;											//This is a hardcoded value for testing
	    qry = qry.replace(" ","\\");
	    Boolean resultCheck = false;
//	    System.out.println(qry);
	    for(int i=1; searchResults.size()<30; i=i+10){ //Adds results 10 at a time (10 per search)
//	    	System.out.println(searchResults.size());
		    URL url = new URL(
		            "https://www.googleapis.com/customsearch/v1?key="+key+ "&cx="+cx+"&q="+ qry + "&alt=json&excludeTerms=gif|svg&searchType=image&num=10&start="+i);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Accept", "application/json");
		    
		    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		    String output;
		    while ((output = br.readLine()) != null) {

		    	if(output.contains("\"totalResults\": \"") && resultCheck == false){
		    		String haha=output.substring(output.indexOf("\"totalResults\": \"")+("\"totalResults\": \"").length(), output.indexOf("\","));
				    if(Long.parseLong(haha) < 30){
				    	throw new IOException("Insufficient Images");
				    }else{
				    	resultCheck = true;
				    }
		    	}
			    
		    	
		    	
		    	
		        if(output.contains("\"link\": \"")){ //If it's a valid link
		            String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
		            URL u = new URL(link); 
	        	    HttpURLConnection huc =  (HttpURLConnection)  u.openConnection();
	        	    HttpURLConnection.setFollowRedirects(false);
	        	    huc.setRequestMethod("GET");
	        	    try{
		        	    huc.connect();
//		        	    System.out.println(huc.getResponseCode());
	        	    	if(huc.getResponseCode() == 200 && searchResults.size()<30){
//		        	    	System.out.println(searchResults.size() + " " + link);
				            searchResults.add(link);
		        	    }
	        	    }catch (Exception e){
//	        	    	System.out.println("lol who cares");
	        	    };
	        	    
		        }
		    }
		    conn.disconnect();
	    }
//	    System.out.println(searchResults.size());
	    
	    System.out.println("in query: " + searchResults.size());
	    
	    return searchResults;
								//NOTE: It is possible to get images of specific sizes! However, to give a working example,
								//the images currently returned will be of various sizes.
	}
	
//	public List<String> fastSearch(String query, int imagesNeeded) throws IOException{
//		List<String> searchResults = new ArrayList<>();
//	    String qry=query;
//	    qry = qry.replace(" ","\\");
//	    Boolean resultCheck = false;
//	    
//	    while(searchResults.size() < imagesNeeded){
//	    	int imagesToGrab = java.lang.Math.min(10, imagesNeeded-searchResults.size());
//	    	URL url = new URL(
//		            "https://www.googleapis.com/customsearch/v1?key="+key+ "&cx="+cx+"&q="+ qry + "&alt=json&excludeTerms=gif|svg&searchType=image&num="+imagesToGrab+"&start="+searchCount);
//		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		    conn.setRequestMethod("GET");
//		    conn.setRequestProperty("Accept", "application/json");
//		    
//		    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//		    String output;
//		    while ((output = br.readLine()) != null) {
//		    	
//		    	if(output.contains("\"totalResults\": \"") && resultCheck == false){
//		    		String haha=output.substring(output.indexOf("\"totalResults\": \"")+("\"totalResults\": \"").length(), output.indexOf("\","));
//				    if(Long.parseLong(haha) < 30){
//				    	throw new IOException("Insufficient Images");
//				    }else{
//				    	resultCheck = true;
//				    }
//		    	}
//		    	
//		    	
//		        if(output.contains("\"link\": \"")){
//		            String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
//		            searchResults.add(link);
//		            searchCount++;
//		        }
//		    }
//		    conn.disconnect();
//	    }
////	    System.out.println(searchResults.size());
////	    System.out.println(searchCount);
//	    return searchResults;
//	} 
}