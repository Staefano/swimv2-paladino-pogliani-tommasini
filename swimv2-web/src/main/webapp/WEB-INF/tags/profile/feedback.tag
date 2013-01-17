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
					<p>
						<c:if test="${hrp.receiverFeedback.evaluation == '0'}">
							<dl>
								<dt><i class="icon-minus"></i> ${hrp.subject}</dt>
								<dd><small>${hrp.receiverFeedback.comment}</small></dd>
							</dl>
						</c:if>
						<c:if test="${hrp.receiverFeedback.evaluation == '1'}">
							<dl>
								<dt><i class="icon-asterisk"></i> ${hrp.subject}</dt>
								<dd><small>${hrp.receiverFeedback.comment}</small></dd>
							</dl>
						</c:if>
						<c:if test="${hrp.receiverFeedback.evaluation == '2'}">
							<dl>
								<dt><i class="icon-plus"></i> ${hrp.subject}</dt>
								<dd><small>${hrp.receiverFeedback.comment}</small></dd>
							</dl>
						</c:if>
					</p>
					</c:if>
				</c:forEach>
			</div>
			<div class="tab-pane" id="tab2">
				<c:if test="${empty receivedList }">
					You don't have any feedback.
				</c:if>
				<c:forEach var="hrr" items="${receivedList}">
					<c:if test="${not empty hrr.askerFeedback}">
					<p>
						<c:if test="${hrr.askerFeedback.evaluation == '0'}">
							<dl>
								<dt><i class="icon-minus"></i> ${hrr.subject}</dt>
								<dd><small>${hrr.askerFeedback.comment}</small></dd>
							</dl>
						</c:if>
						<c:if test="${hrr.askerFeedback.evaluation == '1'}">
							<dl>
								<dt><i class="icon-asterisk"></i> ${hrr.subject}</dt>
								<dd><small>${hrr.askerFeedback.comment}</small></dd>
							</dl>
						</c:if>
						<c:if test="${hrr.askerFeedback.evaluation == '2'}">
							<dl>
								<dt><i class="icon-plus"></i> ${hrr.subject}</dt>
								<dd><small>${hrr.askerFeedback.comment}</small></dd>
							</dl>
						</c:if>
					</p>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>