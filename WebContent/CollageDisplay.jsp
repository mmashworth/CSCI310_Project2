<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "collage.Picture" %>
<%@ page import = "java.util.List" %>
<% Picture currentCollage = (Picture)request.getSession().getAttribute("collageImage"); %> 
<% List<Picture> history = (List<Picture>)request.getSession().getAttribute("history"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="collageDisplayStyle.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		
		<script>
			function changeTitle(element) {
				document.getElementById('topic').innerHTML = element.value;
			}
		</script>
	</head>
	<body>
		<div id="title">
		<%
		 String fileName = application.getRealPath("/") + "saved.png";
		 /* Picture currentCollage = (Picture)request.getParameter("collageImage"); */
		 currentCollage.writeImage(fileName, "png");
		 %>
			Collage for <%= request.getSession().getAttribute("topic") %> <span id="topic"></span>
		</div>
		
		<div id="collageSpace">
			<img alt="collage" src="saved.png">
		</div>
	
		<form method = "GET" action="BuildCollage" >
			<div id="inputStuff">
				<br />
				<input type="text" id="inputBox" name="topic" placeholder="Enter another topic">
				<input type="submit" value="Build Another Collage">
				<button type="button" id="exportButton">Export Collage</button>
			</div>
		</form>
		
		<div id="previousCollages" style="overflow: auto; width: 730px; height: 120px;">
			<% 
				if (history != null){
					for (int i=0; i < history.size(); i++){
						Picture newPic = history.get(i);
						String fileName2 = application.getRealPath("/") + "saved.png";
				 		currentCollage.writeImage(fileName2, "png");
				 		%>
				 		<img alt="collage" src="saved.png" style="float: left; width: 160px; height: 120px; margin: 0 5px;">
				 	<%
					   }
				}
				
			%>
		
		</div>
	</body>
</html>