<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="profile" required="true" type="it.polimi.swimv2.entity.User" %>
<%@attribute name="user" required="false" type="it.polimi.swimv2.entity.User" %>

<div class="span5" style="padding-left: 1.5em;">
	<h3>Abilities</h3>
	<p>
		<c:forEach var="ab" items="${profile.abilities}">
			<span class="badge badge-info">${ab.name}</span>
		</c:forEach>
	</p>

	<c:if test="${user.id == profile.id }">
		<a href="edit-abilities">Edit...</a>
	</c:if>

	<c:if test="${not empty profile.description}">
		<h3>Further information</h3>
		<p>${profile.description}</p>
	</c:if>
</div>