<%@tag description="closed-hr" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true" type="it.polimi.swimv2.entity.HelpRequest"%>

<p>
	<b>You asked <a href="profile?id=${hr.receiver.id}">${hr.receiver.name} ${hr.receiver.surname}</a> help about ${hr.subject}<br></b>
	<c:if test="${hr.status == 'WAITING'}">
		<b>Status:</b> waiting for a reply... <br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info">View comments</a>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
		<b>Status:</b> request accepted. If ${hr.receiver.name} completed the work, you should close it.<br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info">View comments</a>
		<a href="feedback?hr_id=${hr.id}&role=asker" class="btn btn-success">Close and leave feedback</a>
	</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
		<b>Status:</b> request closed. Waiting for ${hr.receiver.name} to leave you a feedback.<br>
		<a href="comment?hr_id=${hr.id}" class="btn btn-info">View comments</a>
	</c:if>
</p>