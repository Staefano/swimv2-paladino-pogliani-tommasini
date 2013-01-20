<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true" type="it.polimi.swimv2.entity.HelpRequest"%>

<p>
	<b><a href="profile?id=${hr.sender.id}">${hr.sender.name} ${hr.sender.surname}</a> asked you help about ${hr.subject}<br></b>
	<c:if test="${hr.status == 'WAITING'}">
		<b>Status:</b> waiting for a reply... <br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info" type="button">View comments</a>
		<a href="helprequestanswer?hr_id=${hr.id}&choice=approved" class="btn btn-success" type="button">Accept</a>
		<a href="helprequestanswer?hr_id=${hr.id}&choice=refused" class="btn btn-danger">Reject</a>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
		<b>Status:</b> request accepted. Complete the job to receive the feedback<br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info">View comments</a>
	</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
		<b>Status:</b> all work is done. Give feedback to the user you helped!<br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info">View comments</a>
		<a href="feedback?hr_id=${hr.id}&role=helper" class="btn btn-success">Leave the feedback</a>
	</c:if>
</p>