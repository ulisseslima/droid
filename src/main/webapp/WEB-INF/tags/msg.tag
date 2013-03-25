<%@ tag language="java" pageEncoding="UTF-8" description="Displays a message. A key for i18n can be provided, its args are separated by tildes (~)."%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="test" rtexprvalue="true" description="The message will only be displayed if this condition is true."%>
<%@ attribute name="text" description="Text to display."%>
<%@ attribute name="key" description="Localized text to display."%>
<%@ attribute name="type" required="true" description="Type of the message."%>
<%@ attribute name="args" description="Arguments to the localized message, separated by '~' (tilde)."%>

<c:if test="${test}">
	<h2 class="message ${type}">
		<c:if test="${empty text}">
			<spring:message code="${key}" arguments="${args}" argumentSeparator="~" />
		</c:if>
		${text}
		<jsp:doBody />
	</h2>
</c:if>