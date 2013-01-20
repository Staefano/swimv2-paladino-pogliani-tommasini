<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>
<%@ attribute name="buttons" fragment="true" %>

<div class="thumbnail" style="margin-bottom: 0.3em;">
  <div class="caption" style="overflow: hidden;">
  	<div class="pull-left" style="clear: both; margin-right: 1em; max-width: 120px;">
  		<img class="img-polaroid" width="110px;" src="${pageContext.request.contextPath}/images/profile?user=${user.id}" />
			<t:progressbar user="${user}" />
  	</div>
  	<div style="font-size: 1.4em; font-weight: bold; float: left; height: 100%; margin-right: 1em;">
  		<p style="margin-top: 1em;">${user.experience}</p>
  		<p style="margin-top: 2em;">${user.reputation}</p>
	</div>
	<div class="pull-right">
    	<jsp:invoke fragment="buttons" />
   	</div>
    <h3><a href="profile?id=${user.id}">${user.name} ${user.surname}</a></h3>
    <p>${user.minibio}</p>
  </div>
</div>