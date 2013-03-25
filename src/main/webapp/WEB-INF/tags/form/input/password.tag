<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="field" required="false" description="Field name." %>
<%@ attribute name="tip" required="false" description="Field tooltip key." %>
<%@ attribute name="placeholder" required="false" description="Placeholder text key." %>
<%@ attribute name="key" required="false" description="Message code prefix." %>
<%@ attribute name="confirm" required="false" description="Message code prefix." %>

<c:if test="${not empty key}">
	<spring:message code="${key}" var="keyMsg" />
	<label>${keyMsg}</label>
</c:if>

<c:if test="${not empty placeholder}">
	<spring:message code="${placeholder}" var="placeholderMsg" />
</c:if>
<c:if test="${not empty tip}">
	<spring:message code="${tip}" var="tipMsg" />
</c:if>
<c:set var="passwordField" value="${empty field? 'password' : field}" />

<input type="password" id="password" class="property ${passwordField}" name="${passwordField}" title="${not empty tipMsg? tipMsg : passwordField}" placeholder="${empty placeholderMsg? keyMsg : placeholderMsg}...">
<c:if test="${confirm}">
	<br>
	<spring:message code="label.confirm.password" var="confirmMsg" />
	<input type="password" class="property ${passwordField} confirmation" name="${passwordField}-confirmation" title="${confirmMsg}" placeholder="${confirmMsg}..."
		onkeypress="checkPasswords()">
</c:if>

<script type="text/javascript">
	function checkPasswords() {
		
	}
</script>