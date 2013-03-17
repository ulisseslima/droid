<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="test" rtexprvalue="true" description="The message will only be displayed if this condition is true."%>
<%@ attribute name="text" description="Text to display."%>
<%@ attribute name="key" description="Localized text to display."%>

<c:if test="${test}">
	<h2 class="error">
		<c:choose>
			<c:when test="${empty text}">
				<spring:message code="${key}" />
			</c:when>
			<c:otherwise>
				${text}
			</c:otherwise>
		</c:choose>
	</h2>
</c:if>