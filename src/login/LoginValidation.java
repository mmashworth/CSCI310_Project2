package login;

import database.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		DataContainer dc = new DataContainer();
		boolean valid = dc.searchForUser(username, password);
		
		//In the above line, the data container is searched to see if the username and password match a login already 
		// contained within the database. If the combination of user/password does not exsist, valid becomes false,
		// and the user is not allowed to login, so the page is redirected to the initial login page. If the combination
		// exists within the data container the user is redirected to the collage build page, which is InputServlet2
		
		if(valid) {
			dc.populateUserClass(username);
			request.setAttribute("username", username);
			response.sendRedirect("InputServlet2.jsp");
		}
		else
			response.sendRedirect("login.html");
		
		
	}
	

	

}
