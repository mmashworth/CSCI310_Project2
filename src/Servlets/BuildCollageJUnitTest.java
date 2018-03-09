package Servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;


public class BuildCollageJUnitTest extends Mockito {

	@Test
	public void testBuildCollage() throws Exception {
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);
	     
	     when(request.getParameter("topic")).thenReturn("dog");
	     StringWriter stringWriter = new StringWriter();
	     PrintWriter writer = new PrintWriter(stringWriter);
	     
	     when(response.getWriter()).thenReturn(writer);

	     new BuildCollage().service(request, response);
	     
	     verify(request, atLeast(1)).getParameter("topic");
	     writer.flush();
	     assertTrue(stringWriter.toString().contains("My expected string"));
	}

}
