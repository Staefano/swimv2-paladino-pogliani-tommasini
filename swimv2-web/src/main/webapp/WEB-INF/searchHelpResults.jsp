<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="SWIM version 2">
	<jsp:attribute name="header">
		<h1>Search for help!</h1>
	</jsp:attribute>
	<jsp:body>
		<form class="input-prepend input-append" action="search" method="get">
			<span class="add-on"><i class="icon-search"></i></span> 
			<input name="abilities" class="span5" type="text"
				placeholder="cooker, plumber" value="${abilities}" maxlength="255">
			<c:if test="${not empty user}">
			<label class="inline add-on" >
				<input type="radio" name="scope" value="all" checked="checked"> all the users 
			</label>
			<label class="inline add-on" >
				<input type="radio" name="scope" value="friends"> friends only 
			</label>
			</c:if>
			<button type="submit" class="btn btn-primary">Find help!</button>
		</form>
	
		<c:choose>
			<c:when test="${found}">
				<c:forEach items="${results}" var="curUser">
                	<t:single-result user="${curUser}">
                		<jsp:attribute name="buttons">
                			<c:if test="${not empty user}">
                				<a class="btn" href="helprequest?receiver=${curUser.id}">Ask help!</a>
                			</c:if>
			  			</jsp:attribute>
                	</t:single-result>
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
</t:template>