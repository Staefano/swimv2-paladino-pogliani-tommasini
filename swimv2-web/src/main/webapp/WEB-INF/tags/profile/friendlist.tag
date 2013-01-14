<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@attribute name="profile" required="true"
	type="it.polimi.swimv2.entity.User"%>
<%@attribute name="friends" required="true" type="java.util.List"%>

<div class="well well-small" style="margin-top: 1em;">
	${profile.name}'s Friends:
	<ul
		style="list-style-type: none; padding: 0; margin: 0; margin-top: 1em;">
		<c:forEach var="fr" items="${friendsList}">
			<c:if test="${user!=fr}">
				<li><a href="profile?id=${fr.id}">${fr.name} ${fr.surname}</a></li>
			</c:if>
		</c:forEach>
	</ul>
</div>