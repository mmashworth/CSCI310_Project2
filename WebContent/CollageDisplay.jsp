<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.List" %>
    <%@ page import = "java.util.Random" %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "java.awt.Image" %>
    <%@ page import = "java.awt.image.BufferedImage" %>
    
    <%@ page import = "googleTesting.Searching" %>
        <%@ page import = "collage.Picture" %>
        <%@ page import = "collage.Collage" %>
        
        <%@ page import = "googleTesting.Searching" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="collageDisplayStyle.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		
		<script>
		  window.onload = function processForm() {
		    var parameters = location.search.substring(1).split("&");
		    var temp = parameters[0].split("=");
		    var initTopic = unescape(temp[1]);
		    //alert(initTopic); // what the user inputted
		    document.getElementById("topic").innerHTML = initTopic;
		  }
		  //processForm();
		</script>
		
		<script>
			function changeTitle(element) {
				document.getElementById('topic').innerHTML = element.value;
			}
		
		</script>
		
	</head>
	<body>
		<div id="title">
			Collage for topic <span id="topic"></span>
		</div>
		
		<div id="collageSpace">
		<%
			//Searching test = new Searching();
			//List<String> nameList = test.searchQuery("dog");
								
			List<String> nameList = new ArrayList<String>();
			for (int i = 0; i < 30; i++) {
				nameList.add("https://res.cloudinary.com/demo/image/upload/sample.jpg");
			}
			
			List<Integer> angleList = new ArrayList<Integer>();
			for (int i = 0; i < 30; i++) {
				Random rand = new Random();
				int input = rand.nextInt(91) - 45;
				angleList.add(input);
			}
		
			 int width = 800;
			 int height = 600;
			 Picture newPict = Collage.make30Collage(width, height, nameList, angleList);
			 //BufferedImage img3 = newPict.getImage(width, height);
			 String fileName = application.getRealPath("/") + "saved.png";
			 newPict.writeImage(fileName, "png");
		%>
		<img src = "saved.png">
		</div>
	
		<form method="GET">
			<div id="inputStuff">
				<br />
				<input type="text" id="inputBox" name="input" placeholder="Enter another topic">
				<button type="submit" id="inputButton" onclick="changeTitle(input)">Build Another Collage</button>
				<button type="button" id="exportButton">Export Collage</button>
			</div>
		</form>
		
		<div id="previousCollages" style="overflow: auto; width: 730px; height: 120px;">
			<div id="innerPrevCollages" style="width: 5000px;"> 	
			<!-- 
				<img src="pictures/dog1.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				
				images in the scroll bar will have these attributes, change src	
				width:height=8:6
			 -->		
				<img src="pictures/dog1.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog2.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog3.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog4.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog5.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog6.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog7.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog8.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
				<img src="pictures/dog9.jpg" style="float: left; width: 160px; height: 120px; margin: 0 5px;" alt="image name">
			</div>
		</div>
	</body>
</html>
