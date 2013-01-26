<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template user="${user}" title="Edit abilities">
	<jsp:attribute name="header">
		<h1>Edit your abilities</h1>
	</jsp:attribute>
	<jsp:body>
		
		<div class="container">
		<c:if test="${result == 'added'}">
			<div class="alert alert-success">
  				Ability ${chosenAb} was successfully added to your curriculum!
			</div>
		</c:if>
		<c:if test="${result == 'removed'}">
			<div class="alert alert-success">
  				Ability ${chosenAb} was successfully removed from your curriculum!
			</div>
		</c:if>
		
		<h2>Remove abilities <small>click to remove</small></h2>
		<c:forEach items="${user.abilities}" var="cur">
			<a href="edit-abilities?abId=${cur.name}&action=remove&searchAb=${param.searchAb}">
				<span class="badge badge-info">${cur.name}</span></a>
		</c:forEach>
		<h2>Add abilities</h2>
		<form class="navbar-search pull-left" style="margin-bottom: 1.2em;" action="edit-abilities" method="get">
			<input type="text" class="search-query" placeholder="Search ability" name="searchAb" maxlength="255">
		</form>
		</div>
		
		<div>
		<c:choose>
			<c:when test="${outcome == 'emptyField'}">
				<div class="alert alert-block">
  					Insert, into the above field, the name of the ability you are looking for!
				</div>
			</c:when>
			<c:when test="${outcome == 'noAbilityFound'}">
				<div class="alert alert-block">
  					No ability found!
				</div>
			</c:when>
			<c:otherwise>
			<p class="muted">Click on the abilities to add them.</p>
			<c:forEach items="${abilitiesList}" var="curAbility">
					<a href="edit-abilities?abId=${curAbility.name}&searchAb=${param.searchAb}">
						<span class="badge badge-info">${curAbility.name}</span></a>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</div>
		
		
		<br>
		<div class="well well-small">
		
		<c:if test="${message == 'success'}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Success!</strong> The ability ${abName} has been requested!
			</div>
		</c:if>
		<c:if test="${message == 'failed'}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Error!</strong> The ability ${abName} already exists or the name field was empty!
			</div>
		</c:if>
		
			<p><b>Your precious ability doesn't exist? Request it!</b></p>
			<form class="form-horizontal" method="post" action="edit-abilities">
		  		<div class="control-group">
		  			<input type="text" placeholder="Ability Name"
						class="input-medium search-query" name="name" maxlength="42">
		  		</div>
		  		<div class="control-group">
		  			<textarea name="comment" rows="4" cols="50" placeholder="Write here a comment..." maxlength="255"></textarea>
		  		</div>
		  		<button type="submit" class="btn btn-small btn-primary">Send Request</button>
			</form>
		</div>

	</jsp:body>
</t:template>