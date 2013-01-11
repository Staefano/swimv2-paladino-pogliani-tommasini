<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:public-page title="SWIM version 2">
	<jsp:attribute name="header">
		<h1>Confirm your registration</h1>
	</jsp:attribute>
	<jsp:body>
		<c:if test="${wrongToken}">
			<div class="alert alert-error">Wrong token! Please paste the complete link you received by
				email, or request another token by registering again to the website.</div>
		</c:if>
		<c:if test="${!wrongToken}">
			<c:if test="${formError}">
				<div class="alert alert-error"> There was an error processing the form. Please, complete all the required fields 
				and submit the form again </div>
			</c:if>
			Thank you for registering to SWIMv2. Please complete your registration
			by inserting your personal data.
			<form method="post" style="margin-top: 1em;">
				<fieldset>
					<label>Name *</label> <input type="text" name="name"
						placeholder="John" required> <label>Surname *</label> <input
						type="text" name="surname" placeholder="Smith" required> <input
						type="hidden" name="token" value="${token}">
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</fieldset>
			</form>
		</c:if>
	</jsp:body>
</t:public-page>