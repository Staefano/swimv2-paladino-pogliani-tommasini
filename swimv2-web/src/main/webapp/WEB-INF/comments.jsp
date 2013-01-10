<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h1>HelpRequest number ${hr.id } about ${hr.subject}!</h1>
	</jsp:attribute>
	<jsp:body>
		<c:forEach var="c" items="${comments}">
			<t:comment comment="${c}" />
		</c:forEach>
		
		
		<form class="form-horizontal"  name="newcomment" method="post" action="comment">
		
		<input type="hidden" name="hr_id" value="${hr.id }">
				<p>
			<b>Comment:</b>
		</p>
		<textarea name="comment" rows="4" cols="50">
		</textarea>
		
		
		
		<input
					type="submit"  value="Comment">
		</form>	
	
	</jsp:body>
</t:private-page>