package Servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import collage.User.UserClass;


public class BuildCollageJUnitTest extends Mockito {
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
	     when(request.getSession()).thenReturn(session);
	     when(request.getSession().getAttribute("topic")).thenReturn("dog");
	     when(request.getRequestDispatcher("/CollageDisplay.jsp")).thenReturn(rd);
	     UserClass.numPreviousSearches++;
	
	     
	     new BuildCollage().service(request, response);

	     
	     
	    


	}

}
