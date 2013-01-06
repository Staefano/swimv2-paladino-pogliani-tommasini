<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html>
<head>
<title>SWIM version 2 - PersonalArea draft</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

	<!-- User area navigation bar -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="<%=request.getContextPath()%>/">SWIMv2</a> <span
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

	<!-- Begin page content -->
	<div class="container" style="padding-top: 40px;">

		<div class="page-header">
			<h1>Welcome to the user area!</h1>
		</div>

	</div>

	<!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
