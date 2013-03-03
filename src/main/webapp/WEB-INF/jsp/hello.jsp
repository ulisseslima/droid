<%@taglib prefix="dvl" tagdir="/WEB-INF/tags" %>

<dvl:page title="stuff.title">
	<dvl:body subtitle="stuff.subtitle">
		<ul>
			<li><a href="./event/">Things to do</a></li>
		</ul>
		
		<h3>Message : ${message}</h3>	
		<h3>Username : ${username}</h3>	
	 
		<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
	</dvl:body>
</dvl:page>
