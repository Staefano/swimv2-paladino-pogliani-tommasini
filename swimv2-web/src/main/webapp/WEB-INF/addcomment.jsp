<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
	<form name="helprequest" method="post" action="helprequest">
		<input type="hidden" name="hr_id" value="${hr}">
		
		<p>
			<b>Comment:</b>
		</p>
		<textarea name="comment" rows="4" cols="50">
		</textarea>
			<br>
		
		<input type="submit" value="Submit" id="Submit">
	</form>

	</jsp:body>
</t:template>