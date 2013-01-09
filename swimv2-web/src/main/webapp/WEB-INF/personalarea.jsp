<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h1>Welcome to the user area!</h1>
	</jsp:attribute>
	<jsp:body>
	<p>
		<h1>Messages</h1>
		<c:if test="${empty usersWithUnread}">
			You don't have unread messages
		</c:if>
		
		<ul>
		<c:forEach var="u" items="${usersWithUnread}">
			<li><a href="${pageContext.servletContext.contextPath}/messages?conversation=${u.id}">${u.name} ${u.surname}</a></li>
		</c:forEach>
		</ul>
		
		<p><a href="${pageContext.servletContext.contextPath}/messages">All messages...</a></p>

		<h1>Notifications</h1>
		<c:forEach var="n" items="${notifications}">
	<a href="notification?notification_id=">${ n.type}</a>
		</c:forEach>
	</jsp:body>
</t:private-page>