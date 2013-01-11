<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template user="${user}" title="Search Ability Results">
	<jsp:attribute name="header">
		<h1>Search results</h1>
	</jsp:attribute>
	<jsp:body>
		
		<div class="container">
		<form class="navbar-search pull-left" action="searchability" method="get">
			<input type="text" class="search-query" placeholder="Search ability to add" name="searchAb">
		</form>
		</div>
		
		<div>
		<c:if test="${result == 'added'}">
			<div class="alert alert-success">
  				Ability ${chosenAb} has been added to your curriculum!
			</div>
		</c:if>
		
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
			<c:forEach items="${abilitiesList}" var="curAbility">
				<div class="well well-small">
					${curAbility.name} 
					<a href="searchability?abId=${curAbility.name}&searchAb=${param.searchAb}">
						<button class="btn btn-mini btn-primary" type="button">Add!</button>
					</a>
				</div>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</div>
		
		
		
		
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
		
		<div class="well well-small">
			Your precious ability doesn't exist? Request it!
			<form class="form-horizontal" method="post" action="searchability">
		  		<div class="control-group">
		  			<input type="text" placeholder="Ability Name"
						class="input-medium search-query" name="name">
		  		</div>
		  		<div class="control-group">
		  			<input type="text" placeholder="Short Comment"
						class="input-medium search-query" name="comment">
		  		</div>
		  		<button type="submit" class="btn btn-small btn-primary">Send Request</button>
			</form>
		</div>

	</jsp:body>
</t:template>