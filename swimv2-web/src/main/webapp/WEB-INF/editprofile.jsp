<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
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

</body>
</html>