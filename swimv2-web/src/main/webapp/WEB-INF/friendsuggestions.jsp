<%@ page language="java"%>		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>		 
		 
<t:template user="${user}" title="People Search Results">		 
	<jsp:attribute name="header">		 
		<h1>Search results</h1>		 
	</jsp:attribute>		 
	<jsp:body>		 
		<c:choose>		 
		<c:when test="${outcome == 'emptyField'}">		 
			<div class="alert alert-block">		 
  				Insert, into the above field, the name of the user you are looking for!		 
			</div>		 
		</c:when>		 
		<c:when test="${outcome == 'noUserFound'}">		 
			<div class="alert alert-block">		 
  				No suggestions! 
			</div>		 
		</c:when>		 
		<c:otherwise>
		<c:if test="${outcome == 'cannotSend'}">		 
			<div class="alert alert-block">		 
  				Can't send the friendship request. Make sure you don't have any other pending request with the same user! 
			</div>
		</c:if>
		<c:if test="${outcome == 'sent'}">		 
			<div class="alert alert-block alert-success">		 
  				Friendship request sent successfully!
			</div>		 
		</c:if>	 
		<c:forEach items="${usersList}" var="curUser">		 
			<t:suggestion profile="${curUser}" />		 
		</c:forEach>		 
		</c:otherwise>		 
		</c:choose>		 
		 
	</jsp:body>		 
</t:template>