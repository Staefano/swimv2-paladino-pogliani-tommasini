<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template user="${user}" title="SWIMv2 - Messages">
	<jsp:attribute name="header">
		<h1>Messages <small>You and ${otherUser.name} ${otherUser.surname}</small></h1>
	</jsp:attribute>
	<jsp:body>
		
		<form method="post" style="display: inline;">
			<input type="hidden" name="id" value="${otherUser.id}">
			<ul class="swim-page-navigation">
				<li><a class="btn" href="${pageContext.servletContext.contextPath}/messages" role="button" ><span class="icon-chevron-left"></span> All messages</a>
				<li><a class="btn" href="#sendMessage" role="button" data-toggle="modal">New message</a></li>
				<li><button type="submit" name="delete" class="btn">Delete conversation</button></li>
			</ul>
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
</t:template>