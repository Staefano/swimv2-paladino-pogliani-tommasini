<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@attribute name="profile" required="true" type="it.polimi.swimv2.entity.User" %>

<img class="img-polaroid" src="${pageContext.request.contextPath}/images/profile?user=${profile.id}" />
<ul style="list-style-type: none; padding: 0; margin: 0; margin-top: 1em;">
	<li><i class="icon-user"></i> ${profile.name} ${profile.surname}</li>
	<li><i class="icon-envelope"></i> <a href="mailto:${profile.email}">${profile.email}</a></li>
	<c:if test="${not empty profile.website}">
		<li><i class="icon-home"></i> <a href="http://${profile.website}">
			${fn:substring(profile.website, 0, 28)}<c:if test="${fn:length(profile.website) > 28}">...</c:if>
		</a></li>
	</c:if>
	<c:if test="${not empty profile.birthdate}">
		<li><i class="icon-calendar"></i> <fmt:formatDate value="${profile.birthdate}" pattern="dd/MM/yyyy" /></li>
	</c:if>
	<c:if test="${not empty profile.location}">
		<li><i class="icon-globe"></i> ${profile.location}</li>
	</c:if>
</ul>
			