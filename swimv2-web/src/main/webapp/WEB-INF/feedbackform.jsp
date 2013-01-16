<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template user="${user}" title="SWIMv2 - Edit Profile">
	<jsp:body>
		<form name="feedbackform" method="post" action="feedback">
					
					
					<input type="hidden" name="hr_id" value="${hr}">
					<input type="hidden" name="role" value="${role}">
					

					<label class="radio inline">
						<input type="radio" name="evaluation" id="optionsRadios1" value="0" checked>
							<i class="icon-minus"></i>
					</label>
					<label class="radio inline">
						<input type="radio" name="evaluation" id="optionsRadios2" value="1">
							<i class="icon-asterisk"></i>
					</label>
					<label class="radio inline">
						<input type="radio" name="evaluation" id="optionsRadios2" value="2">
							<i class="icon-plus"></i>
					</label>

					<p><b>Comment:</b></p>
					<textarea name="comment" rows="4" cols="50"></textarea><br>
					<input type="submit" value="Submit" id="Submit">
		</form>
	</jsp:body>
</t:template>