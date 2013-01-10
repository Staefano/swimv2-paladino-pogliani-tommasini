<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Profile Page">
	<jsp:attribute name="header">
		<h1>${profile.name} ${profile.surname}'s profile</h1>
		<c:if test="${user.admin && user.id != profile.id }">
			<c:if test="${!profile.admin}">
				<a href="promote?profileId=${profile.id}"><button
						class="btn btn-success" type="button">Promote to Admin</button></a>
			</c:if>
			<c:if test="${profile.admin}">
				<div class="alert alert-info">This User is an Admin</div>
			</c:if>
		</c:if>
	</jsp:attribute>
	<jsp:body>
				
	<div class="container">
	<div class="row">
		<div class="span3">
			<img class="img-polaroid" src="${pageContext.request.contextPath}/images/profile?user=${profile.id}" width="220px" height="330px" />
			<ul style="list-style-type: none; padding: 0; margin: 0; margin-top: 1em;">
				<li><i class="icon-user"></i> ${profile.name} ${profile.surname}</li>
				<li><i class="icon-home"></i> <a href="http://${profile.website}">${profile.website}</a></li>
				<li><i class="icon-envelope"></i> <a href="mailto:${profile.email}">${profile.email}</a></li>
				<li><i class="icon-calendar"></i> ${profile.birthdate}</li>
				<li><i class="icon-globe"></i> ${profile.location}</li>
			</ul>
		</div>
		<div class="span9">
			<div class="well" style="margin-left: 1.5em;">
				<p><i>${profile.minibio}</i></p>
			</div>
		</div>
		<div class="row">
			<div class="span5" style="padding-left: 1.5em;">
				<h3>Abilities</h3>
				<p>
				<c:forEach var="ab" items="${profile.abilities}">
					<span class="badge badge-info">${ab.name}</span>
				</c:forEach>
				</p>
				
				<c:if test="${user.id == profile.id }">
					<a href="searchability">Add more...</a>
				</c:if>
				
				<c:if test="${not empty profile.description}">
					<h3>Further information</h3>
					<p>${profile.description}</p>
				</c:if>
			</div>
			<div class="span3" style="float: left" >
				<c:forEach var="hrp" items="${providedList}">
					<p>${hrr.subject }</p>
				</c:forEach>
	
				<c:forEach var="hrr" items="${receivedList}">
					<p>${hrr.subject }</p>
				</c:forEach>
			
				<c:if test="${user.id != profile.id }">
					<ul>
					<c:if test="${showFR}">
						<li><a href="friendrequest?asker=${user.id}&receiver=${profile.id}">Add as friend</a></li>
					</c:if>
					<li><a href="message?">Send message</a></li>
					<li><a href="helprequest?receiver=${profile.id}">Ask help!</a></li>
					</ul>
				</c:if>		
			</div>
		</div>
	</div>
	</div>
	</jsp:body>
</t:private-page>
