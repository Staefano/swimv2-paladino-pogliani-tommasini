<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="comment" required="true" type="it.polimi.swimv2.entity.Comment" %>

<div class="thumbnail">
  <!-- TODO image and the rest... -->
  <div class="caption">
	<p><b>${comment.sender.name }</b>	- <b>${comment.sender.surname }</b><br>
			${comment.text}</p>
  </div>
</div>		
		
			