<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html>
<head>
<title>SWIM version 2</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- TODO to be moved in a separate .css file -->
<style type="text/css">
#swim-general-header {
	margin-top: 2em;
	margin-bottom: 1em;
}
</style>
</head>
<body>
	<div class="container">

		<div id="swim-general-header">
			<h3 class="muted">SWIMv2</h3>
		</div>

		<div class="page-header">
			<h1>Confirm your registration</h1>
		</div>

		<c:if test="${wrongToken}">
			<p>Wrong token! Please paste the complete link you received by
				email, or request another token by registering again to the website.</p>
		</c:if>
		<c:if test="${!wrongToken}">

			<c:if test="${formError}">
				There was an error processing the form. Please, complete all the required fields and submit the form again
			</c:if>
		
			Thank you for registering to SWIMv2. Please complete your registration
			by inserting your personal data.
			
			<form method="post" style="margin-top: 1em;">
				<fieldset>
					<label>Name *</label> <input type="text" name="name"
						placeholder="John"> <label>Surname *</label> <input
						type="text" name="surname" placeholder="Smith"> <input
						type="hidden" name="token" value="${token}">
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</fieldset>
			</form>

		</c:if>

	</div>
	<!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
