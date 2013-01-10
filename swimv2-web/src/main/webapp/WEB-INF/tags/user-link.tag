<%@tag description="User area navigation bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="user" required="true" type="it.polimi.swimv2.entity.User" %>

  <!-- TODO image and the rest... -->
    <p><a href="profile?id=${user.id}">${user.name} ${user.surname}</a></p>
