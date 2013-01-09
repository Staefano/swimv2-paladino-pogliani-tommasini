<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
	<form name="editprofile" method="post" action="editprofile">

		<p>
			<b>Nome:</b>
		</p>
		<input type="text" name="name">
		<p>
			<b>Cognome:</b>
		</p>

		<input type="text" name="surname">


		<p>
			<b>Website:</b>
		</p>
		<input type="text" name="website">
		<p>
		
			<p>
			<b>BirthDate</b>
		</p>
		<input type="text" name="birthdate">
		<p>
		
			<p>
			<b>location:</b>
		</p>
		<input type="text" name="location">
		<p>
		
			<p>
			<b>Minibio:</b>
		</p>
		<textarea rows="4" cols="50">
		</textarea>
		<p>
			<b>Description:</b>
		</p>
		<textarea rows="4" cols="50">
		</textarea>
		<input type="submit" value="Submit" id="Submit">
	</form>

	</jsp:body>
</t:private-page>
