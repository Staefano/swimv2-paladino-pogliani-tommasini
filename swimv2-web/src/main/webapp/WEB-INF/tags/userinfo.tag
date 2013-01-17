<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User"%>
<%@attribute name="width" required="true" %>


<img class="img-polaroid" width="${width};" src="${pageContext.request.contextPath}/images/profile?user=${user.id}" />
<t:progressbar user="${user}" />

<p style="font-size: 1.4em; font-weight: bold; text-align: center;">
	<span style="display: inline-block; width: 40%;">${user.experience}</span>
	<span style="display: inline-block; width: 40%;">${user.reputation}</span>
</p>