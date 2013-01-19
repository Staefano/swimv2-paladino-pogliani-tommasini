<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h1>${hr.subject}</h1>
		<c:if test="${user == hr.sender }">
			<h3> between you and ${hr.receiver.name} ${hr.receiver.surname}</h3>
		</c:if>
		<c:if test="${user == hr.receiver }">
			<h3> between you and ${hr.sender.name} ${hr.sender.surname}</h3>
		</c:if>
	</jsp:attribute>
	<jsp:body>
	
	<div class="container">
		<div class="row">
			<div class="span9">
				<div class="row">
				<c:forEach var="c" items="${comments}">
					<c:choose>
						<c:when test="${user == c.sender}">
							<div class="span6 pull-right"><t:comment comment="${c}" /></div>
						</c:when>
						<c:otherwise>
							<div class="span6 pull-left"><t:comment comment="${c}" /></div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</div>
		
				<form class="form-horizontal"  name="newcomment" method="post" action="comment">
					<input type="hidden" name="hr_id" value="${hr.id }">
					<p><b>Comment:</b></p>
					<textarea name="comment" rows="4" cols="50"></textarea>
					<input type="submit"  value="Comment">
				</form>	
			</div>
			<div class="span3">
				<c:if test="${user == hr.receiver}">
					<c:if test="${hr.status == 'WAITING'}">
						<b>Status:</b> you should answer this hr <br>
						<a	href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> 
						<a href="helprequestanswer?hr_id=${hr.id}&choice=approved">
							<button class="btn btn-success" type="button">Accept </button></a>
					    
					    <a href="helprequestanswer?hr_id=${hr.id}&choice=refused">
					    	<button class="btn btn-danger" type="button">Refuse </button></a>
					</c:if>
					
					<c:if test="${hr.status == 'ACCEPTED'}">
						<b>Status:</b> complete the work to receive the feedback<br>
					</c:if>
					
					<c:if test="${hr.status == 'ZOMBIE'}">
						<b>Status:</b>all work is done, you should give the feedback to the user you help
						<a href="feedback?hr_id=${hr.id}&role=helper">
							<button class="btn btn-success" type="button">Leave the Feedback</button></a>
					</c:if>
				</c:if>
				<c:if test="${user == hr.sender}">
					<c:if test="${hr.status == 'WAITING'}">
						<b>Status:</b> waiting for a reply... <br>
					</c:if>
					
					<c:if test="${hr.status == 'ACCEPTED'}">
						<b>Status:</b> the hr was accepted <br>
						<a href="feedback?hr_id=${hr.id}&role=asker">
							<button class="btn btn-success" type="button">Close and give a Feedback</button></a>
					</c:if>
					
					<c:if test="${hr.status == 'ZOMBIE'}">
						<b>Status:</b> the user is waiting for your feedback <br>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
	
	</jsp:body>
</t:template>