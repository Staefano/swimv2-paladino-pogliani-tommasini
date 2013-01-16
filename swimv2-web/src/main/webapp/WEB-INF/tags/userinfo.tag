<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true"
	type="it.polimi.swimv2.entity.User"%>

<div class="span2">
	<img class="img-polaroid"
		src="${pageContext.request.contextPath}/images/profile?user=${user.id}" />
		<div class="container">
			<div class="span1"><h4>${xp}</h4></div>
			<div class="span1"><h4>${rep}</h4></div>
		</div>
	<div class="progress">
		<div class="bar bar-success" style="width: 60%;"></div>
		<div class="bar bar-warning" style="width: 30%;"></div>
		<div class="bar bar-danger" style="width: 10%;"></div>
	</div>
	<ul
		style="list-style-type: none; padding: 0; margin: 0; margin-top: 1em;">
		<li><i class="icon-user"></i> ${user.name} ${user.surname}</li>
		<li>${user.minibio}</li>
	</ul>
</div>