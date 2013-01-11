<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="providedList" required="true" type="java.util.List" %>
<%@attribute name="receivedList" required="true" type="java.util.List" %>

<c:if test="${ !(empty providedList) || !(empty receivedList) }">
	<div class="tabbable">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">Provided
					Help</a></li>
			<li><a href="#tab2" data-toggle="tab">Received Help</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<c:if test="${empty providedList}">
								Empty
							</c:if>
				<c:forEach var="hrp" items="${providedList}">
					<p>
						<c:if test="${hrp.receiverFeedback.evaluation == '0'}">
							<i class="icon-minus"></i>${hrp.subject}
								</c:if>
						<c:if test="${hrp.receiverFeedback.evaluation == '1'}">
							<i class="icon-asterisk"></i>${hrp.subject}
								</c:if>
						<c:if test="${hrp.receiverFeedback.evaluation == '2'}">
							<i class="icon-plus"></i>${hrp.subject}
								</c:if>
					</p>
				</c:forEach>
			</div>
			<div class="tab-pane" id="tab2">
				<c:if test="${empty receivedList }">
								Empty
							</c:if>
				<c:forEach var="hrr" items="${receivedList}">
					<p>
						<c:if test="${hrr.askerFeedback.evaluation == '0'}">
							<i class="icon-minus"></i>${hrr.subject}
								</c:if>
						<c:if test="${hrr.askerFeedback.evaluation == '1'}">
							<i class="icon-asterisk"></i>${hrr.subject}
								</c:if>
						<c:if test="${hrr.askerFeedback.evaluation == '2'}">
							<i class="icon-plus"></i>${hrr.subject}
								</c:if>
					</p>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>