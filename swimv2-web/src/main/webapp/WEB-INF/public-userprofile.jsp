<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags/profile"%>

<t:template title="SWIMv2 - ${profile.name} ${profile.surname}">
	<jsp:attribute name="header">
		<h1>${profile.name} ${profile.surname}'s profile</h1>
	</jsp:attribute>
	<jsp:body>
				
	<div class="container">
	<div class="row">
		<div class="span3">
			<p:left-badge profile="${profile}" />
			<p:friendlist profile="${profile}" friends="${friendsList}" />
		</div>
		<div class="span9">
			<c:if test="${not empty profile.minibio}">
			<div class="well" style="margin-left: 1.5em;">
				<p><i>${profile.minibio}</i></p>
			</div>
			</c:if>
		</div>
		<div class="row">
			<p:central-profile profile="${profile}" user="${user}"></p:central-profile>
			<div class="span3" style="float: left" >
				<p:feedback providedList="${providedList}" receivedList="${receivedList}" />
			</div>
		</div>
	</div>
	</div>
	
	</jsp:body>
</t:template>
