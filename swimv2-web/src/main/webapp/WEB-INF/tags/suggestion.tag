<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="profile" required="true" type="it.polimi.swimv2.entity.User"%>

<div class="thumbnail" style="margin-bottom: 0.3em;">
  <div class="caption" style="overflow: hidden;">
  	<div class="pull-left" style="clear: both; margin-right: 1em; max-width: 120px;">
  		<img class="img-polaroid" width="110px;" src="${pageContext.request.contextPath}/images/profile?user=${profile.id}" />
			<t:progressbar user="${profile}" />
  	</div>
  	<div style="font-size: 1.4em; font-weight: bold; float: left; height: 100%; margin-right: 1em;">
  		<p style="margin-top: 1em;" class="swimv2-tooltip" title="Experience (number of provided helps)">${profile.experience}</p>
  		<p style="margin-top: 2em;" class="swimv2-tooltip" title="Reputation (from 0 to 10)">${profile.reputation}</p>
	</div>
	<div class="pull-right">
    	<c:if test="${user!=profile}">
			<form method="post" action="friendsuggestions">
				<input type="hidden" name="id" value="${param.id}">
				<input type="hidden" name="receiver" value="${profile.id}">
				<input type="submit" name="Submit" value="Add as friend" class="btn">
			</form>
		</c:if>
	</div>
    <h3>${profile.name} ${profile.surname}</h3>
    <p>${profile.minibio}</p>
  </div>
</div>