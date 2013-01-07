<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="${pageContext.request.contextPath}/">SWIMv2</a> <span
				class="navbar-text pull-left" style="padding-right: 1em;">Hi,
				${user.name} ${user.surname} </span>
			<form class="navbar-search pull-left">
				<input type="text" class="search-query" placeholder="Search people">
			</form>
			<ul class="nav pull-right">
				<c:if test="${user.admin}">
					<li><a href="administer">Administer</a></li>
				</c:if>
				<li><a href="logout">Logout</a></li>
			</ul>
		</div>
	</div>
</div>