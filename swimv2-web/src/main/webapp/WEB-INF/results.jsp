<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Results:</h1>

<c:forEach items="${users}" var="i">
   <h2>User id ${i.id}</h2>
   Name: <c:out value="${i.name}"/><p>
   Surname: <c:out value="${i.surname}"/><p>
</c:forEach>

<a href="add-user">Add new users</a>

</body>
</html>