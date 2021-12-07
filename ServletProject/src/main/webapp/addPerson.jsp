<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="css/style.css" />
</head>

<body>
	<form action="addPerson" method="post">
		ID: <input class="textInput" type="text" name="id">
		<br>
		NAME: <input class="textInput" type="text" name="name">
		<br>
		<input class="btn" type="submit" value="ADD">
	</form>
	
	<button class="btn" type="button" onclick="window.location.href='index.jsp'">Go Back</button>
</body>
</html>