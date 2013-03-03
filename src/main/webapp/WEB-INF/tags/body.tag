<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="subtitle" required="false" description="Body title." %>

<c:if test="${not empty subtitle}">
	<h1><spring:message code="${subtitle}"/></h1>
</c:if>
<jsp:doBody />
