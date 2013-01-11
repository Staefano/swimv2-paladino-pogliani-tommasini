<%@tag description="Main template for the SWIMv2 website" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" %>
<%@attribute name="header" fragment="true"%>
<%@attribute name="user" required="false" type="it.polimi.swimv2.entity.User" %>
<%@attribute name="scripts" fragment="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<!doctype html>

<html>
	<head>
		<title>${title}</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/datepicker.css" rel="stylesheet" media="screen">

		<!-- TODO to be moved in a separate .css file -->
		<style type="text/css">
		#swim-general-header {
			margin-top: 2em;
			margin-bottom: 1em;
		}
		
		#swim-general-header a:hover {
			text-decoration: none;
		}
		
		#privatepage-container {
			padding-top: 40px;
		}
		</style>
	</head>
	<body>
	<c:choose>
		<c:when test="${empty user}"> <%-- Specific for public pages --%>
			<div class="container">
				<div id="swim-general-header">
					<h3><a class="muted" href="${pageContext.request.contextPath}">SWIMv2</a></h3>
				</div>
				<jsp:invoke fragment="header" />
				<jsp:doBody />
			</div>
		</c:when>
		<c:otherwise>
			<t:userbar user="${user}" /> <%-- When the user is logged in --%>
			<div class="container" id="privatepage-container">
				<div class="page-header">
					<jsp:invoke fragment="header" />
				</div>
				<jsp:doBody />
			</div>
		</c:otherwise>
	</c:choose>
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.placeholder.min.js"></script>
	<script type="text/javascript">
	$(function() {
		$('input, textarea').placeholder();
	})
	</script>
	<jsp:invoke fragment="scripts" />
	</body>
</html>