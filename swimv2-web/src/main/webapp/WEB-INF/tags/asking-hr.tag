<%@tag description="closed-hr" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true" type="it.polimi.swimv2.entity.HelpRequest"%>

<p>
	<b>You asked <a href="profile?id=${hr.receiver.id}">${hr.receiver.name} ${hr.receiver.surname }</a> 
	   for help about: ${hr.subject}<br></b>
	<c:if test="${hr.status == 'WAITING'}">
		<b>Status:</b> waiting for a reply... <br>
		<a href="comment?hr_id=${hr.id}">
		<button class="btn btn-info" type="button">Expand all comments</button></a>
	</c:if>
	<c:if test="${hr.status == 'ACCEPTED'}">
		<b>Status:</b> the hr was accepted <br>
		<a href="comment?hr_id=${hr.id}">
		<button class="btn btn-info" type="button">Expand all comments</button></a>
		<a href="feedback?hr_id=${hr.id}&role=asker">
		<button class="btn btn-success" type="button">Close and give a Feedback</button></a>
	</c:if>
	<c:if test="${hr.status == 'ZOMBIE'}">
		<b>Status:</b> the user is waiting for your feedback <br>
		<a href="comment?hr_id=${hr.id}">
		<button class="btn btn-info" type="button">Expand all comments</button></a>
	</c:if>
</p>