<%@ page language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <title>SWIM version 2</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        
        <!-- TODO to be moved in a separate .css file -->
        <style type="text/css">
            #swim-general-header {
                margin-top: 2em;
                margin-bottom: 1em;
            }
        </style>
    </head>
    <body>
        <div class="container">
        
        	
            <div id="swim-general-header">
				<h3 class="muted">SWIMv2</h3>	
            </div>

	<c:if test="${!wrongToken}">
		<h3 class="muted">Wrong token! Please paste the complete link you received by email, or request another token by registering again to the website.</h3>			
	</c:if>
	<c:if test="${!wrongToken}">
		<h3 class="muted">Fill out the form to compelete the registration, thank you (TBD)</h3>				
	</c:if>

        </div>
        <!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
