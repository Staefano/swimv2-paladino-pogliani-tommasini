<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User"%>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="${pageContext.request.contextPath}/">SWIMv2</a>
			<form class="navbar-search pull-left" action="search-user"
				method="get">
				<input type="text" class="search-query" placeholder="Search people"
					name="search" value="${param.search}">
			</form>
			<ul class="nav pull-right">
				<li><a href="profile?id=${user.id}">Hi, ${user.name} ${user.surname}</a> </li>
				<c:if test="${user.admin}">
					<li><a href="admin">Administer</a></li>
				</c:if>
				<li><a href="editprofile">Edit Profile</a></li>
				<li><a href="logout">Logout</a></li>
			</ul>
		</div>
	</div>
</div>