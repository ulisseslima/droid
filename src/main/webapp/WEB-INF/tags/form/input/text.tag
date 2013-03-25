<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="field" required="true" description="Field name." %>
<%@ attribute name="tip" required="false" description="Field tooltip key." %>
<%@ attribute name="placeholder" required="false" description="Placeholder text key." %>
<%@ attribute name="key" required="false" description="Message code prefix." %>
<%@ attribute name="value" required="false" description="Input value." %>

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
<input type="text" class="property ${field}" name="${field}" value="${value}" title="${not empty tipMsg? tipMsg : field}" placeholder="${empty placeholderMsg? keyMsg : placeholderMsg}...">
