package Servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import collage.User.UserClass;


public class BuildCollageJUnitTest extends Mockito {
	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
    }

	@Test
	public void testBuildCollage() throws Exception {

		 HttpServletRequest request = mock(HttpServletRequest.class);       
         HttpServletResponse response = mock(HttpServletResponse.class); 
         

	     HttpSession session = mock(HttpSession.class);
	     RequestDispatcher rd = mock(RequestDispatcher.class);
	     
	     when(request.getParameter("topic")).thenReturn("dog"); 
	     UserClass.numPreviousSearches++;
	     
	     StringWriter sw = new StringWriter();
	     PrintWriter pw = new PrintWriter(sw);
	     when(response.getWriter()).thenReturn(pw);
	     
	     BuildCollage testBC = new BuildCollage();
	     testBC.service(request, response);

	     
	     verify(session).setAttribute("collageImage", true);
	     verify(session).setAttribute("topic", true);
	     verify(session).setAttribute("history", true);
	     
	    


	}

}
