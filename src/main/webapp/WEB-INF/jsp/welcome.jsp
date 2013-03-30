<%@taglib prefix="dvl" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<dvl:page title="stuff.title">
	<dvl:body subtitle="stuff.subtitle">
		<ul>
			<li>
				<dvl:link href="./listing/" key="listing.app.title" />
			</li>
		</ul>
		
		<p></p>
		<h3>Username : ${username}</h3>	
	 	
	 	<p>
			<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
		</p>
	</dvl:body>
</dvl:page>
