<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true"
	type="it.polimi.swimv2.entity.HelpRequest"%>

<!-- TODO image and the rest... -->

<p>
	<b>${hr.subject}: </b>
	<c:if test="${hr.status == 'WAITING'}">
												you should answer this hr <br><a
										href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> 
										<a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a>
										<a href="accepthelprequest?hr_id=${hr.id}"><button
										class="btn btn-success" type="button">Accept </button></a>
					                	<a
									href="refusehelprequest?hrid=${hr.id}"><button
										class="btn btn-danger" type="button">Refuse </button></a>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
												complete the work to give the feedback<br>
										<a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a>
										<a
									href="closehelprequest?hrid=${hr.id}"><button
										class="btn btn-danger" type="button">Close and give a Feedback </button></a>
										
											</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
												the user you help hasn't give you the feedback yet <a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a> <br>
											</c:if>
</p>
