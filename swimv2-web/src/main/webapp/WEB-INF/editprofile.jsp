<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:private-page user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:attribute name="header">
		<h1>Edit profile</h1>
	</jsp:attribute>
	<jsp:body>
	<c:choose>
		<c:when test="${error == 'imageupload'}">
			Cannot upload your profile picture. The file should be an image (jpg or png) 
			with a maximum size of 5MB.
		</c:when>
		<c:when test="${error == 'form' }">
			Error changing profile data.
		</c:when>
	</c:choose>
	
	<form name="editprofile" method="post" action="editprofile" enctype="multipart/form-data">
		<fieldset>
		<label for="name">Name *</label>
		<input type="text" name="name" class="input-xlarge" value="${user.name}">
		
		<label for="surname">Surname *</label>
		<input type="text" name="surname" class="input-xlarge" value="${user.surname}">
		
		<label for="website">Website</label>
		<input type="text" name="website" class="input-xlarge" value="${user.website}">
		
		<label for="birthdate">Date of birth</label>
		<input type="text" name="birthdate" class="input-xlarge" value="${user.birthdate}">
		
		<label for="location">Where do you live?</label>
		<input type="text" name="location" class="input-xlarge" value="${user.location}">
		
		<label for="minibio">Type a line describing yourself</label> 
		<textarea name="minibio" class="input-xlarge" rows="3">${user.minibio}</textarea>
		
		<label for="description">Extended description</label>
		<textarea name="description" class="input-xlarge" rows="6">${user.description}</textarea>
		
		<label for="file">Profile image (max. 5MB, will be resized and cropped)</label>
		<input type="file" id="file" name="file">
		
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
		</fieldset>
	</form>
	</jsp:body>
</t:private-page>
