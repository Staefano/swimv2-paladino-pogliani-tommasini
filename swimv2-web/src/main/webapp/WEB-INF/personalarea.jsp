<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Personal Area">
	<jsp:attribute name="header">
		<h1>Welcome to the user area!</h1>
	</jsp:attribute>
	<jsp:body>
	<p>
		
	
		
		
		<div class="well well-small">
		Your precious ability doesn't exist? Request it!
		<form class="form-horizontal" method="post" action="abilityrequest">
  			<div class="control-group">
  				<input type="text" placeholder="Ability Name"
						class="input-medium search-query" name="name">
  			</div>
  			<div class="control-group">
  				<input type="text" placeholder="Short Comment"
						class="input-medium search-query" name="comment">
  			</div>
  			<button type="submit" class="btn btn-small btn-primary">Send Request</button>
		</form>
	</div>
		
		<h1>Messages</h1>
		<c:if test="${empty usersWithUnread}">
			You don't have unread messages
		</c:if>
		
		<ul>
		<c:forEach var="u" items="${usersWithUnread}">
			<li><a
					href="${pageContext.servletContext.contextPath}/messages?conversation=${u.id}">${u.name} ${u.surname}</a></li>
		</c:forEach>
		</ul>
		
		<p>
			<a href="${pageContext.servletContext.contextPath}/messages">All messages...</a>
		</p>

		<h1>Notifications</h1>
		<c:forEach var="n" items="${notifications}">
		<div class="well">
			<c:if test="${n.type=='ADMIN_PROMOTION'}">
			<p>You have been promoted to Admin!</p>
				<a href="readnotification?notification_id=${n.id}"><button
					class="btn btn-success" type="button">Mark as Read</button></a>
			</c:if>
			<c:if test="${n.type=='FRIENDSHIP_ACCEPTED'}">
			<p>Your friendship request to user<a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> was approved </p>
          	<a href="readnotification?notification_id=${n.id}"><button
							class="btn btn-success" type="button">Mark as Read</button></a>			
			</c:if>
			<c:if test="${n.type=='ABILITY_ACCCEPTED'}">
			<p>Your ability request was approved now you can add ${n.ability } to your ability</p>
			                	<a href="readnotification?notification_id=${n.id}"><button
							class="btn btn-success" type="button">Mark as Read</button></a>
			</c:if>
			<c:if test="${n.type=='ABILITY_REJECTED'}">
			<p>Your ability request was rejected  ${n.ability } is not a valid ability in SWIMv2</p>
          	<a href="readnotification?notification_id=${n.id}"><button
							class="btn btn-success" type="button">Mark as Read</button></a>
			</c:if>
			<c:if test="${n.type=='FRIENDSHIP_RECEIVED'}">
					<p>Friendship Request from  <a href="profile?id=${n.srcUser.id}">${n.srcUser.name} ${n.srcUser.surname}</a> </p> 
					<a href="friendshipaccepted?notification_id=${n.id}"><button
							class="btn btn-success" type="button">Approve </button></a>
                	<a href="friendshiprefused?notification_id=${n.id}"><button
							class="btn btn-danger" type="button">Refuse </button></a>
			</c:if>
			</div>
		</c:forEach>
	</jsp:body>
</t:private-page>