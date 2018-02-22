<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <link rel="stylesheet" href="inputStyle.css">
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form>
		<div id="inputStuff">
			<input type="text" id="inputBox" name="input" placeholder="Enter topic">
			<button type="button" id="inputButton" onclick="openPage('CollageDisplay.jsp')" />Build Collage</button>
		</div>
	</form>
</body>
</html>