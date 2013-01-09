<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:private-page user="${user}" title="SWIMv2 - Messages">
	<jsp:attribute name="header">
		<h1>Messages <small>You and ${otherUser.name} ${otherUser.surname}</small></h1>
	</jsp:attribute>
	<jsp:body>
		
	
		<c:forEach var="msg" items="${messages}">
			<h3>${msg.sender.name} ${msg.sender.surname}</h3>
			<p>${msg.text}</p>
		</c:forEach>
		
		<form method="post">
			<textarea name="text"></textarea>
			<input type="hidden" name="to" value="${otherUser.id}">
			<button type="submit">Submit</button>
		</form>
	
	</jsp:body>
</t:private-page>