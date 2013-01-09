<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<h1>Profile of</h1>
			<h1>${profile.name}</h1>
			<h1>${profile.surname}</h1>
			<h1>${profile.birthdate}</h1>
			<h1>${profile.email}</h1>
			<h1>${profile.website}</h1>
			<h1>${profile.location}</h1>
			<h1>${profile.minibio}</h1>
			<h1>${profile.description}</h1>
		</div>
		<div>
			<c:forEach var="hrp" items="${providedList}">
				<p>${hrr.subject }</p>
			</c:forEach>
		</div>
		<div>
			<c:forEach var="hrr" items="${receivedList}">
				<p>${hrr.subject }</p>
			</c:forEach>
		</div>
	</div>
	<c:if test="${user.id != profile.id }">
		<a href="friendrequest?asker=${user.id}&receiver=${profile.id}">richiesta di amicizia</a>
		<a href="message?">messaggio</a>
		<a href="helprequest?">helprequest</a>
	</c:if>
	<c:if test="${user.id == profile.id }">
	<a href="editprofile">modifica</a>
	</c:if>



</body>
</html>