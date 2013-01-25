<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:attribute name="header">
		<h1>Ask help to ${receiver.name} ${receiver.surname}</h1>
	</jsp:attribute>
	<jsp:body>
	<c:if test="${error == 'mandatoryField' }">
		<div class="alert alert-error">Cannot send the help request. Make sure you compiled the Subject field and selected at least one
		ability. If no abilities are declared by ${receiver.name} ${receiver.surname}, you can't send the request!</div>
	</c:if>
	<c:if test="${error == 'serverError'}">
		<div class="alert alert-error">There was an error processing your request. Please try again later.</div>
	</c:if>

	<div class="row">
	<form name="helprequest" method="post" action="helprequest" class="offset2 span8">
	
		<label>Subject</label>
		<input type="text" name="subject" class="input-block-level" maxlength="255">
				
		<label>Which abilities of ${receiver.name} ${receiver.surname} do you need?</label>
		<c:forEach var="a" items="${abilities}">
			<label class="checkbox inline"><input type="checkbox" name="ability" value="${a.name}"> ${a.name} </label>
		</c:forEach>
		<br><br>
		<label>Comment</label>
		<textarea class="input-block-level" name="comment" rows="6" maxlength="255"></textarea>
		
		<input type="hidden" name="receiver" value="${receiver.id}">
		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="Submit" id="Submit">
		</div>
	</form>
	</div>

	</jsp:body>
</t:template>