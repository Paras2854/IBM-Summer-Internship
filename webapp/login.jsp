<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link href="styles/style.css" rel="stylesheet">
</head>
<body>

	<div id="header">Engineering Decision Support System</div>
	<div class="questionForm">


		<div class="imgDiv">
			<img class="img" src="images/confused.jpg"> 
			
			<span
				class="mainTxt">	
				<h5>This platform helps you to find which Engineering Branch is suitable for you</h5></span>
		</div>

		<div>
			<form action="login" method="post">
				<fieldset class="Fieldset">
					<legend class="Legend">Login</legend>
					<label for="username">UserName:</label> <br> 
					
					<input
						type="text" required name="userName"> <br> 
						
						<label
						for="password">Password:</label> <br> 
						
						<input type="password"
						required name="userPass"> <br>
					<div class="btnWrapper">
						<input class="btn" style="margin-top: 4%;" type="submit"
							value="Login">
					</div>
				</fieldset>
			</form>
		</div>
		<div><a href="signUp.html">Create Account</a></div>
		<div class="error" >
			<%
				if (null != request.getAttribute("error")) {
					out.println(request.getAttribute("error"));
				}
			%>
		</div>
		<div class="success" >
			<%
				if (null != request.getAttribute("success")) {
					out.println(request.getAttribute("success"));
				}
			%>
		</div>
	</div>

</body>
</html>