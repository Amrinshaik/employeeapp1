<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="cyan">

<h1>find Employee By Id</h1><hr>
<form:form action="findEmployee" method="post" modelAttribute="employee">
  <form:label path="employeeId">Employee Id: </form:label>
<form:input path="employeeId"/><br>


<input type="submit" value="findEmployee">
</form:form>
</body>
</html>