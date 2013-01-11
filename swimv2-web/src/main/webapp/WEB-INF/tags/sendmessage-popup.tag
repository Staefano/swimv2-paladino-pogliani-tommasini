<%@tag description="Send Message Popup" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="to" required="true" type="it.polimi.swimv2.entity.User"%>

<div id="sendMessage" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">x</button>
		<h3 id="myModalLabel">Send message</h3>
	</div>
	<form method="post" action="messages">
		<div class="modal-body">
			<textarea name="text" class="input-block-level" style="height: 8em;"></textarea>
			<input type="hidden" name="to" value="${to.id}">
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary">Send</button>
		</div>
	</form>
</div>