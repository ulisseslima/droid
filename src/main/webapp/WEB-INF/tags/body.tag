<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="subtitle" required="false" description="Body title, localized." %>
<%@ attribute name="titleText" required="false" description="Body title, as simple text." %>

<h1>
	<c:if test="${not empty subtitle}">
		<spring:message code="${subtitle}"/>
	</c:if>
	${titleText}
</h1>
<jsp:doBody />
