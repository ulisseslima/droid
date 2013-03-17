<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="dvl" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/reset/reset-min.css">
		<style type="text/css">
		body {
			font-family: "Helvetica Neue",Helvetica,"Liberation Sans", sans-serif;	
			margin: 0;	
			text-align: center;
		}
		h1 {
			font-size: xx-large;
			border-bottom: 2px solid black;
			margin-bottom: 0.5em;
			font-weight: bold;
		}
		h2 {
		    border-top: 2px solid red;
		    border-bottom: 2px solid red;
		    color: red;
		}
		.main-content, textarea {
			margin: 0 auto;
			text-align: left;
		}
		input, button {
			padding: 0.3em;
			margin: 0.3em;
			border: 1px solid gray;
			border-radius: 4px;
		}
		</style>
	</head>
	<body>
		<section id="main-content">
			<h1>Enter your login information</h1>
			
			<form name='f' action="<%=request.getContextPath()%>/j_spring_security_check" method='POST'>
				<input type='text' name='j_username' value='' placeholder="Username..."> 
				<br> 
				<input type='password' name='j_password' placeholder="Password..."> 
				<br> 
				<input name="submit" type="submit" value="submit">
			</form>
						
			<dvl:error key="AbstractUserDetailsAuthenticationProvider.badCredentials" test="${error}" />
		</section>
	</body>
</html>


