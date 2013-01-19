<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags/profile"%>

<t:template user="${user}"
	title="SWIMv2 - ${profile.name} ${profile.surname}">
	<jsp:attribute name="header">
		<h1>${profile.name} ${profile.surname}'s profile <c:if
				test="${profile.admin}">
				<small>admin</small>
			</c:if>
		</h1>
	</jsp:attribute>
	<jsp:body>
				
	<div class="container">
	<div class="row">
		<div class="span3">
			<t:userinfo user="${profile}" width="210px"/>
			<p:left-badge profile="${profile}" />
			<c:if test="${showFirends || user==profile}">
				<p:friendlist profile="${profile}" friends="${friendsList}" />
			</c:if>
		</div>
		<div class="span9">
			<%-- Navigation buttons are shown only if user is logged in --%>
			<c:if test="${not empty user}">
			<ul class="swim-page-navigation swim-nav-right">
				<c:choose>
				<%-- viewing another user's profile --%>
				<c:when test="${user != profile}">
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
				</c:when>
				<%-- viewing my profile --%>
				<c:otherwise>
						<li><a class="btn" href="editprofile">Edit profile</a></li>
				</c:otherwise>
				</c:choose>
			</ul>
			</c:if>
			<c:if test="${not empty profile.minibio}">
			<div class="well" style="margin-left: 1.5em;">
				<p><i>${profile.minibio}</i></p>
			</div>
			</c:if>
		</div>

		<p:central-profile profile="${profile}" user="${user}"></p:central-profile>
		<div class="span3" style="float: left">
			<p:feedback providedList="${providedList}" receivedList="${receivedList}" />		
		</div>
	</div>
	</div>
	
	<c:if test="${not empty user && user.id != profile.id}">
		<t:sendmessage-popup to="${profile}" />
	</c:if>
	
	</jsp:body>
</t:template>
