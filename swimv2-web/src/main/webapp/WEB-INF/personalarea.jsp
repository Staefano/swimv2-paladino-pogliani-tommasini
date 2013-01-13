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
							<c:forEach var="hr" items="${openProvidingHR}" begin="0" end="2">
								<t:providing-hr hr="${hr}"/>
						</c:forEach>
						</div>
						<div class="tab-pane" id="tab2">
							<c:if test="${empty openReceivingHR}">
								No open help request.
							</c:if>
							<c:forEach var="hr" items="${openReceivingHR}" begin="0" end="2">
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
							<c:if test="${n.type=='HELP_REJECTED'}">
							<p>Your Help Request to <a href="profile?id=${n.srcUser.id}">${n.srcUser.name}  ${n.srcUser.surname}</a> was refused </p>
							<a href="readnotification?notification_id=${n.id}">
								<button class="btn btn-success" type="button">Mark as Read</button>
							</a>
							</c:if>
							<c:if test="${n.type=='ADMIN_PROMOTION'}">
							<p>You have been promoted to Admin!</p>
								<a href="readnotification?notification_id=${n.id}">
									<button class="btn btn-success" type="button">Mark as Read</button>
							</a>
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_ACCEPTED' || n.type=='FRIENDSHIP_ACCEPTED_DIRECT' }">
							<p>Your friendship request to user <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> was approved </p>
							<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a><br>
				          	<a href="readnotification?notification_id=${n.id}">
				          		<button class="btn btn-success" type="button">Mark as Read</button>
				          	</a>			
							</c:if>
							<c:if test="${n.type=='ABILITY_ACCCEPTED'}">
							<p>Your ability request was approved now you can add ${n.ability } to your ability</p>
							<a href="readnotification?notification_id=${n.id}">
								<button class="btn btn-success" type="button">Mark as Read</button>
							</a>
							</c:if>
							<c:if test="${n.type=='ABILITY_REJECTED'}">
							<p>Your ability request was rejected  ${n.ability } is not a valid ability in SWIMv2</p>
				          	<a href="readnotification?notification_id=${n.id}">
				          		<button class="btn btn-success" type="button">Mark as Read</button>
				          	</a>
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_RECEIVED'}">
								<p>Friendship NOT DIRECT Request from  <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> 
									<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a><br>
								</p> 
								<a href="friendship?notification_id=${n.id}&answer=accepted">
									<button class="btn btn-success" type="button">Approve </button>
								</a>
				                <a href="friendship?notification_id=${n.id}&answer=refused">
				                	<button class="btn btn-danger" type="button">Refuse </button>
				                </a>
							</c:if>
							<c:if test="${n.type=='FRIENDSHIP_RECEIVED_DIRECT'}">
								<p>Friendship DIRECT Request from  <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a>
									<a href="friendsuggestions?id=${n.srcUser.id}">See friends suggestions...</a><br>
								</p> 
								<a href="friendship?notification_id=${n.id}&answer=accepted">
									<button class="btn btn-success" type="button">Approve </button>
								</a>
				                <a href="friendship?notification_id=${n.id}&answer=refused">
				                	<button class="btn btn-danger" type="button">Refuse </button>
				                </a>
							</c:if>
						</div>
						</c:forEach>
					</div>
					</div>
				</div>
		
				<div class="span1"></div>
			
				<t:userinfo user="${user}" />
			
			</div>
		</div>
</jsp:body>
</t:template>