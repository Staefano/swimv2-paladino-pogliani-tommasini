<%@tag description="Feedback Form" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="hr" required="true" type="it.polimi.swimv2.entity.HelpRequest"%>
<%@attribute name="role" required="true" type="String"%>

<form name="feedbackform" method="post" action="feedback">
	<legend>Close request</legend>
	<label class="radio inline">
		<input type="radio" name="evaluation" id="optionsRadios1" value="0">
			<i class="icon-minus"></i>
	</label>
	<label class="radio inline">
		<input type="radio" name="evaluation" id="optionsRadios2" value="1" checked>
			<i class="icon-asterisk"></i>
	</label>
	<label class="radio inline">
		<input type="radio" name="evaluation" id="optionsRadios2" value="2">
			<i class="icon-plus"></i>
	</label>
	
	<textarea name="comment" rows="4" cols="50" style="margin-top: 0.5em;" 
		placeholder="Write here a comment..." maxlength="255"></textarea>
	
	<input type="hidden" name="hr_id" value="${hr.id}">
	<input type="hidden" name="role" value="${role}">
	<input type="submit" class="btn btn-primary" value="Leave feedback" id="Submit">
</form>