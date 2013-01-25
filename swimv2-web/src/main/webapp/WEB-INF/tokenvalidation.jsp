<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="SWIM version 2">
	<jsp:attribute name="header">
		<h1>Confirm your registration</h1>
	</jsp:attribute>
	<jsp:body>
		<c:if test="${formError}">
			<div class="alert alert-error"> There was an error processing the form. 
			Please, complete all the required fields and submit the form again </div>
		</c:if>
		<c:choose>
		<c:when test="${tokenOutcome == 'INVALID' }">
			<div class="alert alert-error">Wrong token! Please paste the complete link you received by
				email, or request another token by registering again to the website.</div>
		</c:when>
		<c:when test="${tokenOutcome == 'NEWUSER' }">
			Thank you for registering to SWIMv2. Please complete your registration
			by inserting your personal data.
			<form method="post" style="margin-top: 1em;">
				<fieldset>
					<label>Name *</label> <input type="text" name="name" placeholder="John" required maxlength="255">
					<label>Surname *</label> <input type="text" name="surname" placeholder="Smith" required maxlength="255">
					<input type="hidden" name="token" value="${token}">
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</fieldset>
			</form>		
		</c:when>
		<c:when test="${tokenOutcome == 'RESETPASSWORD' }">
			Type your new password here:
			<form method="post" style="margin-top: 1em;">
				<fieldset>
					<label>Password</label>
					<input type="password" name="password" required maxlength="255">
					<input type="hidden" name="token" value="${token}">
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</fieldset>
			</form>
		</c:when>
		</c:choose>
	</jsp:body>
</t:template>