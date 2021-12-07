<%@page import="com.nevexis.model.Person"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete person</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<form action="deletePerson" method="post">
		<select name="person">
			<%
			List<Person> people = (List<Person>) request.getAttribute("people");
			for (Person person : people) {
			%>
			<option value="<%= person.getId() %>"><%=person%></option>
			<%
			}
			%>
		</select> <input class="btn" type="submit" value="DELETE">
	</form>
	<button class="btn" type="button"
		onclick="window.location.href='index.jsp'">Go Back</button>

</body>
</html>