<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - User Promotion">
	<jsp:attribute name="header">
		<h1>Search results</h1>
	</jsp:attribute>
	<jsp:body>
		<c:choose>
		<c:when test="${message}">
			<div class="alert alert-success">
				<h1>All went well!</h1>
				<t:single-result user="${profile}" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-danger">
				<h1>Something went wrong!</h1>
			</div>
		</c:otherwise>

		</c:choose>

	</jsp:body>
</t:private-page>