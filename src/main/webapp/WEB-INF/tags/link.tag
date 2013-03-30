<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="href" required="true" description="The address."%>
<%@ attribute name="key" required="false" description="i18n key."%>
<%@ attribute name="label" required="false" description="Text to display."%>
<%@ attribute name="css" required="false" description="CSS style."%>

<a href="${href}" class="${css}">
	<c:if test="${not empty key}">
		<spring:message code="${key}" />
	</c:if>
	${label}
	<jsp:doBody />
</a>