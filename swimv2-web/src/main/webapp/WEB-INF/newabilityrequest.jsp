<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Personal Area">
	<jsp:body>
	
	<c:choose>
		<c:when test="${message}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Success!</strong> The ability ${abName} has been requested!
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Error!</strong> The ability ${abName} already exists!
			</div>
		</c:otherwise>
	</c:choose>
	
	<div class="well well-small">
		Want to add one more?
		<form class="form-horizontal" method="post" action="abilityrequest">
  			<div class="control-group">
  				<input type="text" placeholder="Ability Name" class="input-medium search-query" name="name">
  			</div>
  			<div class="control-group">
  				<input type="text" placeholder="Short Comment" class="input-medium search-query" name="comment">
  			</div>
  			<button type="submit" class="btn btn-small btn-primary">Send Request</button>
		</form>
	</div>
	
	</jsp:body>
</t:private-page>