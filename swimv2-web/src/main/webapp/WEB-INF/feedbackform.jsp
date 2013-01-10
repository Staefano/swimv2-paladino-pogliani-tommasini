<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
		<form name="feedbackform" method="post" action="feedback">
					
					
					<input type="hidden" name="hr_id" value="${hr}">
					<input type="hidden" name="role" value="${role}">
					<input type="text" name="evaluation"">
					<p><b>Comment:</b></p>
					<textarea name="comment" rows="4" cols="50"></textarea><br>
					<input type="submit" value="Submit" id="Submit">
		</form>
	</jsp:body>
</t:private-page>