<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="field" required="true" description="Field name." %>
<%@ attribute name="tip" required="false" description="Field tooltip." %>
<%@ attribute name="code" required="false" description="Message code prefix." %>

<c:if test="${not empty code}">
	<label for="${code}.${field}">
		<spring:message code="label.${field}" />
	</label>
</c:if>
<textarea class="property ${field}" name="${field}" title="${not empty tip? tip : field}"><jsp:doBody /></textarea>
