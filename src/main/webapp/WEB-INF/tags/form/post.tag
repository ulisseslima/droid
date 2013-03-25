<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="action" required="true" description="Action Mapping."%>
<%@ attribute name="key" required="true" description="Key to be used for the submit button."%>

<form class="post" name="${action}" id="${action}-form" action="./${action}" method="POST">
	<jsp:doBody />
	<spring:message code="${key}" var="keyMsg" />	
	<input type="submit" class="submit" name="form-submit-${action}" value="${keyMsg}">
</form>
