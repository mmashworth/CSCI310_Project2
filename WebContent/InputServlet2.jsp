<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  	<style type="text/css">
 	body {
		background-color: #d3d3d3;
	}
	#inputBox {
		border-color: #a9a9a9;
	}
	#inputStuff {
		position: absolute;
	
	    width: 300px;
	    height: 300px;
	
	    /* Center form on page horizontally & vertically */
	    top: 50%;
	    left: 50%;
	    margin-top: -40px;
	    margin-left: -90px;
	} 
	#inputButton {
		background-color: #a9a9a9;
		color: white;
	}
	
	#loadingSymbol {
		top: 50%;
	    left: 50%;

	}
</style>
<script type="text/javascript">
 function loading()
 {
	document.getElementById("loadingSymbol").style.visibility = "visible";
	document.getElementById("inputStuff").style.visibility = "hidden";
 	//window.location.href = pageURL;
 }
</script>

<script type="text/javascript">
function enterInput(pageURL) {
	document.getElementById('inputBox').onkeypress = function(e) {
		var event = e || window.event;
	    var charCode = event.which || event.keyCode;
	
	    if ( charCode == '13' ) {
	      // Enter pressed    
	      document.getElementById("loadingSymbol").style.visibility = "visible";
	      window.location.href = pageURL;
	      return false;
	    }
	}
}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method = "GET" action="BuildCollage" >
			<div id="inputStuff">
				<input type="text" name="topic" placeholder="Enter topic" onkeydown="enterInput('CollageDisplay.jsp')"> <br>
				<input type="text" name="shape" placeholder="Enter shape"><br>
				Collage Options <select name = "options"></select><br>
  				<button name = "save"> Save to History</button></br>
  				
				Collage Width <input type="text"  name="width" value="800"> <br>
				Collage Height <input type="text"  name="height" value="600"> <br>
				
				<select name="filter">
					<option value="nofilter">No filter</option>
    					<option value="bw">Black and white</option>
    					<option value="gs">Grayscale</option>
    					<option value="sepia">Sepia</option>
  				</select> <br>
				
				
				<input type="submit" onclick='loading()' value="Build Collage">
			</div>
		</form>
		
		<div id="loadingSymbol" style='visibility: hidden'>
			<img src="loading.gif">
		</div>
		
	</div>
	

	
</body>
</html>
