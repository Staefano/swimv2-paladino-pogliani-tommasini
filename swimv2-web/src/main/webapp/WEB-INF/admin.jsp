<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Admin Area">
	<jsp:attribute name="header">
		<h1>Welcome to the Admin Area!</h1>
	</jsp:attribute>
	<jsp:body>
	
		<c:if test="${message == 'new'}">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Success!</strong> The ability ${abName} has been added!
			</div>
		</c:if>
		<c:if test="${message == 'already'}">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Warning!</strong> The ability ${abName} already exists!
			</div>
		</c:if>
		
		<form class="form-search" method="get" action="admin">
  			<input type="text" class="input-medium search-query" name="ability" maxlength="42">
  			<button type="submit" class="btn btn-primary">Add New Ability</button>
		</form>
		
		<c:if test="${message == 'approve'}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Success!</strong> The ability ${abName} has been approved!
			</div>
		</c:if>
		<c:if test="${message == 'reject'}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Success!</strong> The ability ${abName} has been rejected!
			</div>
		</c:if>

		<c:choose>
			<c:when test="${found}">
				<c:forEach var="abReq" items="${results}">
                	<div class="well">
                	<p>Name: ${abReq.ability}</p>
                	<p>Comment: ${abReq.comment}</p>
                	<p>User: ${abReq.sender.name} ${abReq.sender.surname}</p>
                	<a href="admin?abId=${abReq.id}&choice=approve"><button
							class="btn btn-success" type="button">Approve Ability</button></a>
                	<a href="admin?abId=${abReq.id}&choice=reject"><button
							class="btn btn-danger" type="button">Refuse Ability</button></a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h4>No pending request today!</h4>
			</c:otherwise>
		</c:choose>
	</jsp:body>
</t:template>