package Servlets;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import collage.Collage;
import collage.Picture;
import collage.User;
import collage.User.UserClass;

/**
 * Servlet implementation class BuildCollage
 */
@WebServlet("/BuildCollage")
public class BuildCollage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildCollage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String saveCollage = request.getParameter("saveBox");		
	
		System.out.println("----------In build collage servlet-----------");
		String topic = request.getParameter("topic");
		System.out.println("topic= " + topic);
		response.setContentType("text/html"); //can do text/json, in servlets usually will be text/html
		Collage newCollage = new Collage();
		List<String> urls = newCollage.getUrls(topic);
		System.out.println("number of urls: " + urls.size());
		for (int i=0; i < urls.size(); i++) {
			System.out.println(urls.get(i));
		}
	

		//get width and height right
		int width = 800;
		int height = 600;
		String w = request.getParameter("width");
		String h = request.getParameter("height");
		
		if(w != null && isInteger(w))
			width = Integer.parseInt( w );
		if(h != null && isInteger(h))
			height = Integer.parseInt( h );
		
		//get rotations 
		String rotationsStr = request.getParameter("rotationsBox");	
		boolean rotations = false;
		if(rotationsStr != null)
			rotations = true;
		//get borders
		String bordersStr = request.getParameter("bordersBox");
		boolean borders = false;
		if(bordersStr != null)
			borders = true;
			
		
		String filter = request.getParameter("filter");		
		Picture collageImage = Collage.make30Collage(width, height, urls, 
													newCollage.getAngles(), 
													rotations, borders);
		
		if(filter != null)
			collageImage.applyFilter(filter);

		UserClass.numPreviousSearches++;
		
		if(saveCollage != null) {
			collageImage.savePicture();
		}
		
		
		if (UserClass.getNumPreviousSearches() != 0) {
			if(UserClass.currentCollage != null && UserClass.currentCollage.getSavePicture()) {
				UserClass.addPreviousCollage(); //add old collage to list of historical collages
			}
		}
	
		UserClass.setCurrentCollage(collageImage); //update current collage to the new search
		collageImage.setTopic(topic);
		//UserClass.setCurrentCollage(collageImage); //update current collage to the new search
		List<Picture> history = UserClass.getCollages();
		System.out.println("history size: " + history.size());
		request.getSession().setAttribute("collageImage", collageImage);
		request.getSession().setAttribute("topic", topic);
		request.getSession().setAttribute("history", history);
		//RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/CollageDisplay.jsp");
		RequestDispatcher dispatch = request.getRequestDispatcher("/CollageDisplay.jsp");
		dispatch.forward(request, response);
	}
	

	public static boolean isInteger(String s) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),10) < 0) return false;
	    }
	    return true;
	}

}
