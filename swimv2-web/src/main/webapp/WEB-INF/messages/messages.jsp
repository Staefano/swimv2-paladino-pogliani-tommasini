<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:private-page user="${user}" title="SWIMv2 - Messages">
	<jsp:attribute name="header">
		<h1>Messages</h1>
	</jsp:attribute>
	<jsp:body>
	
		<c:forEach var="cur" items="${userList}">
			<h2>${cur.name} ${cur.surname}</h2>
			<p><a href="${pageContext.servletContext.contextPath}/messages?conversation=${cur.id}">Go to conversation</a></p>
		</c:forEach>
	
	</jsp:body>
</t:private-page>