<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags/profile"%>

<t:private-page user="${user}" title="SWIMv2 - ${profile.name} ${profile.surname}">
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

			<p:left-badge profile="${profile}" />
			<p:friendlist profile="${profile}" friends="${friendsList}" />
		</div>
		<div class="span9">
			<c:if test="${not empty profile.minibio}">
			<div class="well" style="margin-left: 1.5em;">
				<p><i>${profile.minibio}</i></p>
			</div>
			</c:if>
		</div>
		<div class="row">
			<p:central-profile profile="${profile}" user="${user}"></p:central-profile>
			<div class="span3" style="float: left" >
				<p:feedback providedList="${providedList}" receivedList="${receivedList}" />
			
				<c:if test="${user.id != profile.id }">
					<ul>
					<c:if test="${showFR}">
						<li><a href="friendrequest?asker=${user.id}&receiver=${profile.id}">Add as friend</a></li>
					</c:if>
					<li><a href="#sendMessage" data-toggle="modal">Send Message</a></li>
					<li><a href="helprequest?receiver=${profile.id}">Ask help!</a></li>
					</ul>
				</c:if>		
			</div>
		</div>
	</div>
	</div>
	
	<c:if test="${user.id != profile.id}">
		<t:sendmessage-popup to="${profile}" />
	</c:if>
	
	</jsp:body>
</t:private-page>
