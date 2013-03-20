<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="field" required="true" description="Field name." %>
<%@ attribute name="tip" required="false" description="Field tooltip." %>
<%@ attribute name="placeholder" required="false" description="Placeholder text." %>
<%@ attribute name="code" required="false" description="Message code prefix." %>
<%@ attribute name="value" required="false" description="Input value." %>

<c:if test="${not empty code}">
	<label for="${code}.${field}">
		<spring:message code="label.${field}" />
	</label>
</c:if>
<input type="text" class="property ${field}" name="${field}" value="${value}" title="${not empty tip? tip : field}" placeholder="${placeholder}">