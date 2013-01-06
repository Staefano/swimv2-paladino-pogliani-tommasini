<%@tag description="Template for public pages" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" %>
<%@attribute name="header" fragment="true"%>

<!doctype html>

<html>
	<head>
		<title>${title}</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

		<!-- TODO to be moved in a separate .css file -->
		<style type="text/css">
		#swim-general-header {
			margin-top: 2em;
			margin-bottom: 1em;
		}
		
		#swim-general-header a:hover {
			text-decoration: none;
		}
		</style>
	</head>
<body>
	<div class="container">

		<div id="swim-general-header">
			<h3>
				<a class="muted" href="${pageContext.request.contextPath}">SWIMv2</a>
			</h3>
		</div>

		<div class="row">
			<jsp:invoke fragment="header" />
			<jsp:doBody />
		</div>
	</div>
	<!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
