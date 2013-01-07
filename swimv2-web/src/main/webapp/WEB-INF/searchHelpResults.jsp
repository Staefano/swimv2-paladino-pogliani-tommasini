<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:public-page title="SWIM version 2">
	<jsp:attribute name="header">
		<h1>Search results</h1>
	</jsp:attribute>
	<jsp:body>
		<form class="form-search">
			<input type="text" name="abilities" class="span6 input-medium search-query" value="${abilities}">
			<button type="submit" class="btn">Search</button>
		</form>
	
		<c:choose>
			<c:when test="${found}">
				<c:forEach items="${results}" var="curUser">
                	<t:single-result user="${curUser}"/>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="alert alert-block">
					<h4>No result found!</h4>
  					No user satisfying your search criteria was found in the system.
				</div>
			</c:otherwise>
		</c:choose>
	</jsp:body>
</t:public-page>