<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="profile" required="true"
	type="it.polimi.swimv2.entity.User"%>

<div class="thumbnail">
	<!-- TODO image and the rest... -->
	<div class="caption">
		<a
			href="friendrequest?asker=${user.id}&receiver=${profile.id}&type=indirect">Add ${profile.name} ${profile.surname} as friend</a>
	</div>
</div>