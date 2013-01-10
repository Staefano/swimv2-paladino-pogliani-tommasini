<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:private-page user="${user}" title="Search Ability Results">
	<jsp:attribute name="header">
		<h1>Search results</h1>
	</jsp:attribute>
	<jsp:body>
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
			<p> ${curAbility} </p>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		
		<div class="well well-small">
			Your precious ability doesn't exist? Request it!
			<form class="form-horizontal" method="post" action="abilityrequest">
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
</t:private-page>