<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true"
	type="it.polimi.swimv2.entity.HelpRequest"%>

<!-- TODO image and the rest... -->

<p>
	<b><a href="profile?id=${hr.sender.id}">${hr.sender.name }  ${hr.sender.surname }</a> ask you for help : ${hr.subject}<br> </b>
	<c:if test="${hr.status == 'WAITING'}">
												<b>Status:</b> you should answer this hr <br><a
										href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> 
										<a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a>
										<a href="helprequestanswer?hr_id=${hr.id}&choice=approved"><button
										class="btn btn-success" type="button">Accept </button></a>
					                	<a
									href="helprequestanswer?hr_id=${hr.id}&choice=refused"><button
										class="btn btn-danger" type="button">Refuse </button></a>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
												<b>Status:</b> complete the work to receive the feedback<br>
										<a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a>
										
										
											</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
												<b>Status:</b>all work is done, you should give the feedback to the user you help <a href="comment?hr_id=${hr.id}"><button
										class="btn btn-info" type="button">Expand all comments </button></a> <br><a href="feedback?hr_id=${hr.id}&role=helper"><button
										class="btn btn-success" type="button">Leave the Feedback</button></a>
											</c:if>
</p>
