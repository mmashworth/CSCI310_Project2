<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		
		</div>
	
		<form>
			<div id="inputStuff">
				<br />
				<input type="text" id="inputBox" name="input" placeholder="Enter another topic">
				<button type="button" id="inputButton" onclick="changeTitle(input)">Build Another Collage</button>
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
