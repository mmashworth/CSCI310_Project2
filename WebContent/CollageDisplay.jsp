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
		<script>
			function swapImages() {
				console.log('an image was clicked');
			}
		</script>
		<script>	
			function swapImages(filename, topic) {
				console.log('in swapImages function');
				console.log('   ' + topic);
				
				var mainSource = document.getElementById('currentCollage').src;
				console.log(mainSource);
				var swapSource = document.getElementById(filename).src;
				console.log(swapSource);
				
				//move current collage image to clicked image
				document.getElementById(filename).src = mainSource;
				//move clicked image into main collage space
				document.getElementById('currentCollage').src =  swapSource;
				
				
				var mainTopic = document.getElementById('currentCollage').alt;
				var swapTopic = document.getElementById(filename).alt;
				
				document.getElementById('currentCollage').alt = swapTopic;
				document.getElementById(filename).alt = mainTopic
				//change title
				document.getElementById('title').innerHTML = 'Collage for ' + swapTopic;
			}	
		</script>
		
		<script>
			function exportCollage() {
				var seconds = new Date().getTime() / 1000;
				var xhttp= new XMLHttpRequest();
				xhttp.open("GET", "ExportCollage.jsp?type=export&random="+seconds, false);
				xhttp.send();
			}
		</script>
		<script>
			function exportCollagePDF() {
				var seconds = new Date().getTime() / 1000;
				var xhttp= new XMLHttpRequest();
				xhttp.open("GET", "ExportCollagePDF.jsp?type=export&random="+seconds, false);
				xhttp.send();
			}
		</script>
		<script>
			function returnToIndex() {
				window.location.replace("/inputServlet2.jsp");
			}
		</script>
		<script>
			function goToExportServlet() {
			    var xhr = new XMLHttpRequest();
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState == 4) {
			            var data = xhr.responseText;
			            alert(data);
			        }
			    }
			    xhr.open('GET', 'PDFExport', true);
			    xhr.send(null);
			}
		</script>
	</head>
	<body>
		<!-- This is where we are saving the pictures that we will use in the collage -->
		<%
		 String fileName = application.getRealPath("/") + "saved.png";
		 /* Picture currentCollage = (Picture)request.getParameter("collageImage"); */
		 currentCollage.writeImage(fileName, "png");
		 %>
	
		<div id="title">	 
			Collage for <%= request.getSession().getAttribute("topic") %> <span id="topic"></span>
		</div>
		
		
		
		<div id="collageSpace">
			<img alt="<%=currentCollage.getTopic()%>" src="saved.png" id="currentCollage">
		</div>
	
	
		<!-- This is the get form where we will allow the user to export collages-->
		<form method = "GET" action="BuildCollage" >
			<div id="inputStuff">
				<br />
			<!--	<input type="text" id="inputBox" name="topic" placeholder="Enter another topic"> -->
			<!-- <input type="submit" value="Build Another Collage"> -->
				<button type="button" id="build" onclick = "returnToIndex()" > Build Another Collage </button>
				<button type="button" id="exportButtonPNG" onclick = "exportCollage()">Export Collage as PNG</button>
				<button type="button" id="exportButtonPDF" onclick = "exportCollagePDF()">Export Collage as PDF</button>
			</div>
		</form>
		
		
		
		<!-- Set up for previous collages that the user can both look at and click on -->
		<div id="previousCollages" style="overflow: auto; width: 730px; height: 120px;">
			<div id="innerPrevCollages" style="width: 5000px;">
				<% 
					if (history != null){
						for (int i=0; i < history.size(); i++){
							Picture newPic = history.get(i);
							String fileName2 = application.getRealPath("/") + "savedAgain" + Integer.toString(i) + ".png";
					 		String filename = "savedAgain" + Integer.toString(i) + ".png";
							newPic.writeImage(fileName2, "png");
					 		%>
					 		<img alt=<%=newPic.getTopic()%> src=<%=filename%> id=<%=filename%> 
					 			 style="float:left; width: 160px; height: 120px; margin: 0 5px;"
					 			 onclick= 'swapImages("<%=filename%>", "<%=newPic.getTopic()%>", "<%=currentCollage.getTopic()%>")'>
					 		<%
						   }
					}
				
				%>
			</div>
		</div>
	</body>
</html>