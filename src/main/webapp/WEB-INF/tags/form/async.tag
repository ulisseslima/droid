<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="action" required="true" description="Action Mapping."%>
<%@ attribute name="key" required="true" description="Key to be used for the submit button."%>

<form class="async" name="${action}" id="${action}-form" action="">
	<jsp:doBody />
	<spring:message code="${key}" var="keyMsg" />
	<input type="button" class="submit" name="form-submit-${action}" value="${keyMsg}" onclick="doSubmit('${action}-form')">
</form>