<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Interest</title>
<link rel="stylesheet" href="styles/style.css">
</head>
<body>


<div id="header">
Engineering Decision Support System
</div>
<div class="questionForm">

<h3>Hi <%= session.getAttribute("username") %>, Your Interest</h3>
<form id="formId">        
   <img src="displayChart" />
</form>
<div><a href="logout">Go to Home Page</a></div>
</div>



</body>
</html>