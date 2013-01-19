<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="comment" required="true" type="it.polimi.swimv2.entity.Comment" %>

<div class="thumbnail">
  <!-- TODO image and the rest... -->
  <div class="caption">
	<c:choose>
		<c:when test="${user == comment.sender}">
			<p><b>You</b><br> ${comment.text}</p>
		</c:when>
		<c:otherwise>
			<p><a href="profile?id=${comment.sender.id}">${comment.sender.name} ${comment.sender.surname }</a> <br> ${comment.text}</p>
		</c:otherwise>
	</c:choose>	
  </div>
</div>		
		
			