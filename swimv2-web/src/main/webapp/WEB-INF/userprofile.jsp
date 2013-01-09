<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Profile Page">
	<jsp:attribute name="header">
		<h1>Welcome to the user area!</h1>
	</jsp:attribute>
	<jsp:body>
<div>
<img class="profile_img"
				src="${pageContext.request.contextPath}/img/unknown-profile.jpg"
				width="300px" style="float:left" />

		<div style="float:left">
			<h1>Profile of</h1>
			<h1>${profile.name}</h1>
			<h1>${profile.surname}</h1>
			<h1>${profile.birthdate}</h1>
			<h1>${profile.email}</h1>
			<h1>${profile.website}</h1>
			<h1>${profile.location}</h1>
			<h1>${profile.minibio}</h1>
			<h1>${profile.description}</h1>
		</div>
		<div>
			<c:forEach var="hrp" items="${providedList}">
				<p>${hrr.subject }</p>
			</c:forEach>
		</div>
		<div>
			<c:forEach var="hrr" items="${receivedList}">
				<p>${hrr.subject }</p>
			</c:forEach>
		</div>
	</div>
	<c:if test="${user.id != profile.id }">
		<ul>
		<c:if test="${showFR}">
				<li><a href="friendrequest?asker=${user.id}&receiver=${profile.id}">richiesta di amicizia</a></li>
		</c:if>
		<li><a href="message?">messaggio</a></li>
		<li><a href="helprequest?">helprequest</a></li>
		</ul>
	</c:if>

	</jsp:body>
</t:private-page>