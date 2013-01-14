<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags/profile"%>

<t:template user="${user}" title="SWIMv2 - ${profile.name} ${profile.surname}">
	<jsp:attribute name="header">
		<h1>${profile.name} ${profile.surname}'s profile <c:if test="${profile.admin}"><small>admin</small></c:if></h1>
	</jsp:attribute>
	<jsp:body>
				
	<div class="container">
	<div class="row">
		<div class="span3">
			<p:left-badge profile="${profile}" />
			<p:friendlist profile="${profile}" friends="${friendsList}" />
		</div>
		<div class="span9">
			<c:if test="${not empty user}">
				<c:choose>
				<c:when test="${user != profile}">
					<ul class="swim-page-navigation pull-right">
					<c:if test="${user.admin && !profile.admin }">
						<li><a class="btn btn-success" href="promote?profileId=${profile.id}">Promote to Admin</a></li>
					</c:if>
					<c:if test="${showFR}">
						<li><a class="btn" href="friendrequest?asker=${user.id}&receiver=${profile.id}&type=direct">Add as friend</a></li>
					</c:if>
					<c:if test="${showMSG }">
						<li><a class="btn" href="#sendMessage" data-toggle="modal">Send Message</a></li>
					</c:if>
						<li><a class="btn" href="helprequest?receiver=${profile.id}">Ask help!</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="swim-page-navigation pull-right">
						<li><a class="btn" href="editprofile">Edit profile</a></li>
					</ul>
				</c:otherwise>
				</c:choose>
			</c:if>
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
			</div>
		</div>
	</div>
	</div>
	
	<c:if test="${not empty user && user.id != profile.id}">
		<t:sendmessage-popup to="${profile}" />
	</c:if>
	
	</jsp:body>
</t:template>
