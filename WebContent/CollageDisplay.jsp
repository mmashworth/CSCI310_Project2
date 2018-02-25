<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		
		<div id="previousCollages">
		
		</div>
	</body>
</html>