<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="dvl" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<dvl:page title="label.login.title" js="Signup">
	<dvl:body subtitle="label.login.subtitle">
		<form:post action="newuser" key="label.signup">
			<input:text field="username" placeholder="user.name" />
			<br>
			<input:text field="email" placeholder="user.email" />
			<br>
			<input:password confirm="true" placeholder="login.j_password" />
			<br>
		</form:post>
	</dvl:body>
</dvl:page>
