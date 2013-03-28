<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="dvl" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<dvl:page title="label.login.title">
	<dvl:body subtitle="label.login.subtitle">
		<dvl:msg type="info" key="label.welcome" args="${username}"
			test="${not empty username}" />
		
		<dvl:link href="${pageContext.request.contextPath}/signup" key="label.signup" />
		
		<form:post action="${pageContext.request.contextPath}/j_spring_security_check" 
			key="label.login">
			<input:text field="j_username" placeholder="user.name" />
			<br>
			<input:password field="j_password" placeholder="login.j_password" />
			<br>
		</form:post>

		<dvl:msg type="error" key="AbstractUserDetailsAuthenticationProvider.badCredentials" 
			test="${error}" />
	</dvl:body>
</dvl:page>
