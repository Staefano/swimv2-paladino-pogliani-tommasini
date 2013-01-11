<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template user="${user}" title="People Search Results">
	<jsp:attribute name="header">
		<h1>Search results</h1>
	</jsp:attribute>
	<jsp:body>
		<c:choose>
		<c:when test="${outcome == 'emptyField'}">
			<div class="alert alert-block">
  				Insert, into the above field, the name of the user you are looking for!
			</div>
		</c:when>
		<c:when test="${outcome == 'noUserFound'}">
			<div class="alert alert-block">
  				No user found!
			</div>
		</c:when>
		<c:otherwise>
		<c:forEach items="${friends}" var="curUser">
			<t:suggestion profile="${curUser}" />
		</c:forEach>
		</c:otherwise>
		</c:choose>

	</jsp:body>
</t:template>