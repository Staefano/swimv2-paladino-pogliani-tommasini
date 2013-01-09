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
		
		<p>
			<a href="${pageContext.servletContext.contextPath}/messages">All messages...</a>
		</p>

		<h1>Notifications</h1>
		<c:forEach var="n" items="${notifications}">
			<c:if test="${n.type!='FRIENDSHIP_RECEIVED'}">
			<a href="readnotification?notification_id=${n.id}">Mark as Read  ${ n.id}</a>
			</c:if>
			<c:if test="${n.type=='FRIENDSHIP_RECEIVED'}">
					<p>Friendship Request from  ${n.srcUser.name}  ${n.srcUser.surname}</p>
					<a href="friendshipaccepted?notification_id=${n.id}">Accept</a>
					<a href="friendshiprefused?notification_id=${n.id}">Refuse</a>
			</c:if>
		</c:forEach>
	</jsp:body>
</t:private-page>