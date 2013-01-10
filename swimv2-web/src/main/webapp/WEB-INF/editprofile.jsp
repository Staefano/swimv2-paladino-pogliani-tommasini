<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
	<form name="editprofile" method="post" action="editprofile" enctype="multipart/form-data">

		<p>
			<b>Name:</b>
		</p>
		<input type="text" name="name">
		<p>
			<b>Surname:</b>
		</p>

		<input type="text" name="surname">


		<p>
			<b>Website:</b>
		</p>
		<input type="text" name="website">
		<p>
		
			<p>
			<b>Birth date:</b>
		</p>
		<input type="text" name="birthdate">
		<p>
		
			<p>
			<b>Location:</b>
		</p>
		<input type="text" name="location">
		<p>
		
			<p>
			<b>Minibio:</b>
		</p>
		<textarea name="minibio" rows="4" cols="50">
		</textarea>
		<p>
			<b>Description:</b>
		</p>
		<textarea name="description" rows="4" cols="50">
		</textarea>
		
		<input type="file" id="file" name="file">
		<input type="submit" value="Submit" id="Submit">
	</form>

	</jsp:body>
</t:private-page>
