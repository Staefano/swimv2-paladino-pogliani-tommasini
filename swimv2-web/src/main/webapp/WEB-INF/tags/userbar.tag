<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true"
	type="it.polimi.swimv2.entity.User"%>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand pull-left" href="${pageContext.request.contextPath}/">SWIMv2</a>
			<p class="navbar-text pull-left">Hi, ${user.name} ${user.surname}
			</p>
			<form class="navbar-search" style="margin-left: 1em;"
				action="search-user" method="get">
				<input type="text" class="search-query" placeholder="Search people"
					name="search" value="${param.search}" maxlength="510"> <!-- name(255) + surname(255) -->
			</form>
			<ul class="nav pull-right">
				<c:if test="${user.admin}">
					<li><a href="admin">Administer</a></li>
				</c:if>
				<li><a href="profile?id=${user.id}">My profile</a></li>
				<li><a href="logout">Logout</a></li>
			</ul>
		</div>
	</div>
</div>