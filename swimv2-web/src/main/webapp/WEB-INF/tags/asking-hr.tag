<%@tag description="closed-hr" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true" type="it.polimi.swimv2.entity.HelpRequest"%>

<p>
	<c:if test="${hr.status == 'WAITING'}">
		<b>Status:</b> waiting for a reply... <br>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
		<b>Status:</b> request accepted. Remember to close it when ${hr.receiver.name} completes the work!<br>
		<a href="feedback?hr_id=${hr.id}&role=asker" class="btn btn-success">Close and leave feedback</a>
	</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
		<b>Status:</b> request closed. Waiting for ${hr.receiver.name} to leave you a feedback.<br>
	</c:if>
</p>