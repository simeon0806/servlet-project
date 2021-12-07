<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nevexis.model.Person"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" />
</head>

<body>

	<table>
		<caption>People</caption>

		<tr>
			<td>ID</td>
			<td>NAME</td>
		</tr>

		<%
		List<Person> persons = (List<Person>) request.getAttribute("personList");

		for (Person p : persons) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
		</tr>

		<%
		}
		%>
	</table>


	<button class="btn" type="button"
		onclick="window.location.href='index.jsp'">Go Back</button>
</body>
</html>