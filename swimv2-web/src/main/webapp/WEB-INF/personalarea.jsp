<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h1>Welcome to the user area!</h1>
	</jsp:attribute>
	<jsp:body>
	<p>
		I'm the (dummy) content of this page.
		
		
		<h1>Notifications</h1>
		<c:forEach var="n" items="${notifications}">
	<a href="notification?notification_id=">${ n.type}</a>
		</c:forEach>
	</jsp:body>
</t:private-page>