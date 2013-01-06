<%@tag description="Template for private pages" pageEncoding="UTF-8"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>
<%@attribute name="title" required="true" %>
<%@attribute name="header" fragment="true"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!doctype html>

<html>
<head>
<title>${title}</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<t:userbar user="${user}" />
	
	<!-- Begin page content -->
	<div class="container" style="padding-top: 40px;">

		<div class="page-header">
			<jsp:invoke fragment="header" />
		</div>
		
		<jsp:doBody />

	</div>
	<!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
