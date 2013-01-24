<%@ page language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:attribute name="header">
		<h1>Edit profile</h1>
	</jsp:attribute>
	<jsp:attribute name="scripts">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js" charset="UTF-8"></script>
		<script type="text/javascript">
			$('#birthdate').datepicker({
				format : 'dd/mm/yyyy'
			});
		</script>
	</jsp:attribute>
	<jsp:body>
	<c:choose>
		<c:when test="${error == 'imageupload'}">
		<div class="alert alert-error"> Cannot upload your profile picture. The file should be an image (jpg or png) 
			with a maximum size of 7MB. </div>
		</c:when>
		<c:when test="${error == 'form' }">
			<div class="alert alert-error"> Error changing profile data. </div>
		</c:when>
	</c:choose>
	
	<form name="editprofile" method="post" action="editprofile" enctype="multipart/form-data">
	<div class="row">
		<div class="span5">
		
		<label for="name">Name *</label>
		<input type="text" name="name" class="input-block-level" value="${user.name}" required>
		
		<label for="surname">Surname *</label>
		<input type="text" name="surname" class="input-block-level" value="${user.surname}" required>
		
		<label for="website">Website</label>
		<div class="input-prepend">
  			<span class="add-on">http://</span>
  			<input type="text" name="website" value="${user.website}">
		</div>
		
		<label for="birthdate">Date of birth</label>
		<input type="text" name="birthdate" id="birthdate" class="input-block-level" value="<fmt:formatDate value="${user.birthdate}" pattern="dd/MM/yyyy" />">
		
		<label for="location">Where do you live?</label>
		<input type="text" name="location" class="input-block-level" value="${user.location}">
		
		<label for="minibio">Type a line describing yourself</label> 
		<textarea name="minibio" class="input-block-level" rows="5">${user.minibio}</textarea>
		
		</div>

		<div class="span5 offset1">
		
		<label for="file">Profile image (max. 5MB, will be resized and cropped)</label>
		<input type="file" id="file" name="file">
		<label class="checkbox"><input type="checkbox" name="removeImage" value="yes">Remove my image</label>
		<img style="display: block; margin: 0.7em auto;"class="img-polaroid" src="${pageContext.request.contextPath}/images/profile?user=${user.id}" />
		
		<label for="description">Extended description</label>
		<textarea name="description" class="input-block-level" rows="7">${user.description}</textarea>
		</div>
		
		
	</div>
	<div class="form-actions">
			<button type="submit" class="btn btn-primary">Submit</button>
	</div>
	</form>
	</jsp:body>
</t:template>
