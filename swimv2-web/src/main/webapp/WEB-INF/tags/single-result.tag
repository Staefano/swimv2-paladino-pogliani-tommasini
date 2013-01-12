<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>

<div class="thumbnail" style="margin-bottom: 0.3em;">
  <!-- TODO image and the rest... -->
  <div class="caption" style="overflow: hidden;">
  	<img class="img-polaroid pull-left" style="clear: both; margin-right: 1em; max-width: 100px;" src="${pageContext.servletContext.contextPath}/images/profile?user=${user.id}">
    <h3><a href="profile?id=${user.id}">${user.name} ${user.surname}</a></h3>
    <p>${user.minibio}</p>
  </div>
</div>