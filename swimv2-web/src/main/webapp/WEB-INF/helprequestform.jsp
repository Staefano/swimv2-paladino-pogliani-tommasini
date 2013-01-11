<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
	<form name="helprequest" method="post" action="helprequest">

		<p>
			<b>Subject:</b>
		</p>

		<input type="text" name="subject">
		
		<input type="hidden" name="receiver" value="${receiver.id}">
		
		<p>
			<b>Abilities of ${receiver.name} ${receiver.surname}:</b>
			<c:forEach var="a" items="${abilities}">
					<p>	<input type="checkbox" name="ability"> ${a.name}</p>
			</c:forEach>
		</p>
			
		
		<input type="submit" value="Submit" id="Submit">
	</form>

	</jsp:body>
</t:private-page>