<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="providedList" required="true" type="java.util.List" %>
<%@attribute name="receivedList" required="true" type="java.util.List" %>

<c:if test="${ !(empty providedList) || !(empty receivedList) }">
	<div class="tabbable">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">Provided</a></li>
			<li><a href="#tab2" data-toggle="tab">Received</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<c:if test="${empty providedList}">
								You don't have any feedback.
							</c:if>
				<c:forEach var="hrp" items="${providedList}">
					<c:if test="${ not empty hrp.receiverFeedback }">
					<dl>
						<dt><c:choose>
							<c:when test="${hrp.receiverFeedback.evaluation == 'NEGATIVE'}">
								<i class="icon-minus"></i>
							</c:when>
							<c:when test="${hrp.receiverFeedback.evaluation == 'NEUTRAL'}">
								<i class="icon-asterisk"></i>
							</c:when>
							<c:when test="${hrp.receiverFeedback.evaluation == 'POSITIVE'}"> 
								<i class="icon-plus"></i>
							</c:when>
						</c:choose>	
						${hrp.subject}</dt>
						<c:forEach var="ab" items="${hrp.abilities}">
							<dd><span class="badge badge-info">${ab.name}</span></dd>
						</c:forEach>
						<dd><small>${hrp.receiverFeedback.comment}</small></dd>
					</dl>
					</c:if>
				</c:forEach>
			</div>
			<div class="tab-pane" id="tab2">
				<c:if test="${empty receivedList }">
					You don't have any feedback.
				</c:if>
				<c:forEach var="hrr" items="${receivedList}">
					<c:if test="${not empty hrr.askerFeedback}">
					<dl>
						<dt><c:choose>
							<c:when test="${hrr.askerFeedback.evaluation == 'NEGATIVE'}">
								<i class="icon-minus"></i>
							</c:when>
							<c:when test="${hrr.askerFeedback.evaluation == 'NEUTRAL'}">
								<i class="icon-asterisk"></i>
							</c:when>
							<c:when test="${hrr.askerFeedback.evaluation == 'POSITIVE'}"> 
								<i class="icon-plus"></i>
							</c:when>
						</c:choose>
						${hrr.subject}</dt>
						<dd><small>${hrr.askerFeedback.comment}</small></dd>
					</dl>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>