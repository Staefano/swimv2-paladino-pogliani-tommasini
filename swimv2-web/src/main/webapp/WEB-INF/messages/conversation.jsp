<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:private-page user="${user}" title="SWIMv2 - Messages">
	<jsp:attribute name="header">
		<h1>Messages <small>You and ${otherUser.name} ${otherUser.surname}</small></h1>
	</jsp:attribute>
	<jsp:body>
		
		<a href="${pageContext.servletContext.contextPath}/messages" role="button" class="btn" ><li class="icon-chevron-left"></li> All messages</a>
		<a href="#sendMessage" role="button" class="btn" data-toggle="modal">New message</a>
		<form method="post" style="display: inline;">
			<input type="hidden" name="id" value="${otherUser.id}">
			<button type="submit" name="delete" class="btn">Delete conversation</button>
		</form>
	
		<div class="media">
		<c:forEach var="msg" items="${messages}">
			<div class="media-body" >
				<img src="${pageContext.servletContext.contextPath}/images/profile?user=${msg.sender.id}" class="media-object pull-left" style="max-width: 64px; padding-bottom: 10px;">
				<h4 class="media-heading">${msg.sender.name} ${msg.sender.surname} <small> <fmt:formatDate value="${msg.timestamp}" pattern="dd/MM/yyyy HH:mm" /> </small></h4>
				<p>${msg.text}</p>
			</div>
		</c:forEach>
		</div>
	
		<t:sendmessage-popup to="${otherUser}" />
	
	</jsp:body>
</t:private-page>