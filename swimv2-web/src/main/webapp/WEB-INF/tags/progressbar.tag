<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>

<div class="progress" style="margin-bottom: 5px;">
	<div class="bar bar-success" style="width: ${user.posFB * 100 / (user.posFB + user.negFB + user.neuFB)}%;"></div>
	<div class="bar bar-warning" style="width: ${user.neuFB * 100 / (user.posFB + user.negFB + user.neuFB)}%;"></div>
	<div class="bar bar-danger" style="width: ${user.negFB * 100 / (user.posFB + user.negFB + user.neuFB)}%;"></div>
</div>