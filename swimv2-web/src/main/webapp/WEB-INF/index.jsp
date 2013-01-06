<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:public-page title="SWIM - Homepage">
	<jsp:attribute name="header"></jsp:attribute>
	<jsp:body>
	<c:if test="${accessDenied}">
		<div class="alert alert-error">Permission denied. Please log in again</div>
	</c:if>

	<div class="span7 hero-unit">
		<h1>SWIMv2</h1>
		<p>The Small World Hypotesis Machine, reloaded</p>
		<h2>Search for help!</h2>
		<p>What kind of ability are you looking for?</p>
		<form class="input-prepend input-append">
			<span class="add-on"><i class="icon-search"></i></span> <input
			id="abilities" class="span5" type="text"
			placeholder="cooker, plumber">
			<button type="submit" class="btn btn-primary">Find help!</button>
		</form>
	</div>
	<div class="span3">
		<ul class="nav nav-tabs">
			<li
			<c:if test="${!toggleRegistration}">
	                         class="active"
	                     </c:if>><a
			href="#login" data-toggle="tab">Login</a></li>
			<li
			<c:if test="${toggleRegistration}">
	                             class="active"
	                         </c:if>><a
			href="#register" data-toggle="tab">Register</a></li>
		</ul>
		<div class="tab-content">
			<div
			class="tab-pane <c:if test="${!toggleRegistration}">
	                         active
	                     </c:if>"
			id="login">
				<form method="post">
					<c:if test="${wrongLogin}">
						<div class="alert alert-error">Wrong username or password</div>
					</c:if>
					<fieldset>
						<input type="text" name="user" placeholder="Username">
						<input type="password" name="password" placeholder="Password">
						<button type="submit" class="btn btn-primary">Login</button>
					</fieldset>
				</form>
			</div>
			<div class="tab-pane <c:if test="${toggleRegistration}">active</c:if>" id="register">
			<form method="post">
				<c:choose>
					<c:when test="${registrationOutcome == 1}">
						<div class="alert alert-success">Thank you for
							registering. We'll send you an email with the instruction to
							complete the registration</div>
					</c:when>
					<c:when test="${registrationOutcome == 2}">
						<div class="alert alert-error">An error occurred during
							registration. Please try again later.</div>
					</c:when>
					<c:when test="${registrationOutcome == 3}">
						<div class="alert alert-error">You are already
							registered. Contact an administrator if you don't remember
							your password.</div>
					</c:when>
					<c:when test="${registrationOutcome == 4}">
						<div class="alert alert-info">Complete all the required
							fields!</div>
					</c:when>
				</c:choose>
				<fieldset>
					<input type="text" name="email" placeholder="E-mail address">
					<input type="password" name="password" placeholder="Password">
					<span class="help-block">Complete the fields above to register</span>
					<button type="submit" class="btn btn-primary">Register</button>
				</fieldset>
			</form>
			</div>
		</div>
	</div>
	</jsp:body>
</t:public-page>