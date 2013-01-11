<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sfn" uri="http://polimi/swimfn" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template user="${user}" title="SWIMv2 - Messages">
	<jsp:attribute name="header">
		<h1>Messages</h1>
	</jsp:attribute>
	<jsp:body>
	
	<c:if test="${empty userList}">
		<div class="alert alert-info">No message to read! To start a conversation, search the user and click the button on the profile page!</div>
	</c:if>
	
	<div class="media">
		<c:forEach var="cur" items="${userList}">
			<div class="media-body" >
				<img src="${pageContext.servletContext.contextPath}/images/profile?user=${cur.id}" class="media-object pull-left" style="max-width: 64px; padding-bottom: 10px;">
				<h3 class="media-heading">${cur.name} ${cur.surname} <c:if test="${sfn:contains(unread, cur)}"><small>has unread messages</small></c:if></h3>
				<p><a href="${pageContext.servletContext.contextPath}/messages?conversation=${cur.id}">Go to conversation</a></p>
			</div>
		</c:forEach>
	</div>
	
	</jsp:body>
</t:template>