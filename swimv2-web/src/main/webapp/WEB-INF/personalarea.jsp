<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h2>Search for help!</h2>
		<p>What kind of ability are you looking for?</p>
		<form class="input-prepend input-append" action="search" method="get">
			<span class="add-on"><i class="icon-search"></i></span> 
			<input name="abilities" class="span5" type="text"
				placeholder="cooker, plumber">
			<label class="inline add-on" >
				<input type="radio" name="scope" value="all" checked="checked"> all the users 
			</label>
			<label class="inline add-on" >
				<input type="radio" name="scope" value="friends"> friends only 
			</label>
			<button type="submit" class="btn btn-primary">Find help!</button>
		</form>
	</jsp:attribute>
	<jsp:body>
		<div class="container">
			<div class="row">
				<div class="span9">
					<h3>Help requests</h3>
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1" data-toggle="tab">Providing Help</a></li>
						<li><a href="#tab2" data-toggle="tab">Receiving Help</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<c:if test="${empty openProvidingHR}">
								No open help request.
							</c:if>
							<c:forEach var="hr" items="${openProvidingHR}">
								<t:providing-hr hr="${hr}"/>
						</c:forEach>
						</div>
						<div class="tab-pane" id="tab2">
							<c:if test="${empty openReceivingHR}">
								No open help request.
							</c:if>
							<c:forEach var="hr" items="${openReceivingHR}">
								<t:asking-hr hr="${hr}"/>						
						</c:forEach>
						</div>
					</div>
					
					<hr>
					
					<div class="row-fluid">
					<div class="span6">
						<h3>Messages</h3>
							<c:choose>
								<c:when test="${empty usersWithUnread}">							
									You don't have unread messages.
								</c:when>
								<c:otherwise>
									<table class="table table-striped">
									<c:forEach var="u" items="${usersWithUnread}" begin="0" end="4">
									<tr>
										<td><a href="${pageContext.servletContext.contextPath}/messages?conversation=${u.id}">${u.name} ${u.surname}</a></td>
									</tr>
									</c:forEach>
									</table>
								</c:otherwise>
							</c:choose>
						<p><a href="${pageContext.servletContext.contextPath}/messages">All messages...</a></p>
					</div>
					
					
					<div class="span6">
						<h3>Notifications</h3>
						<c:if test="${empty notifications}">
							You don't have notifications.
						</c:if>
						<c:forEach var="n" items="${notifications}" begin="0" end="2">
						<div class="well">
							<p>
							<c:if test="${n.type=='HELP_REJECTED'}">
								Your Help Request to <a href="profile?id=${n.srcUser.id}">${n.srcUser.name}  ${n.srcUser.surname}</a> was refused
							</c:if>
							<c:if test="${n.type=='ADMIN_PROMOTION'}">
								You have been promoted to Admin!
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_ACCEPTED' || n.type=='FRIENDSHIP_ACCEPTED_DIRECT' }">
								Your friendship request to user <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> was approved <br>
								<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a>
							</c:if>
							<c:if test="${n.type=='ABILITY_ACCCEPTED'}">
								Your ability request was approved now you can add ${n.ability } to your ability
							</c:if>
							<c:if test="${n.type=='ABILITY_REJECTED'}">
								Your ability request was rejected  ${n.ability } is not a valid ability in SWIMv2
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_RECEIVED'}">
								Friendship NOT DIRECT Request from  <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> 
									<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a><br>
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_RECEIVED_DIRECT'}">
								Friendship DIRECT Request from  <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a>
									<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a><br>
							</c:if>
							</p>
							<%-- BUTTONS --%>
							<%-- Mark as read button for all notifications but friendship received --%>
							<c:choose>
							<c:when test="${n.type == FRIENDSHIP_RECEIVED || n.type == FRIENDSHIP_RECEIVED_DIRECT }">
								<a class="btn btn-success" href="friendship?notification_id=${n.id}&answer=accepted">Approve</a>
				                <a class="btn btn-danger" href="friendship?notification_id=${n.id}&answer=refused">Refuse</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-success" href="readnotification?notification_id=${n.id}">Mark as Read</a>
							</c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
					</div>
					</div>
				</div>
		
				<div class="span3">
					<div style="width: 160px; margin: 0 auto;">
					<t:userinfo user="${user}" width="150px"/>
					<p>${user.minibio}</p>
					</div>
				</div>
			
			</div>
		</div>
</jsp:body>
</t:template>