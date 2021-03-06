<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="subtitle" required="false" description="Body title, localized." %>
<%@ attribute name="titleText" required="false" description="Body title, as simple text." %>
<%@ attribute name="href" required="false" description="Link." %>
<%@ attribute name="tip" required="false" description="Body title, localized." %>
<%@ attribute name="i18nTip" required="false" description="Body title, localized." %>

<c:if test="${not empty i18nTip}">
	<spring:message code="${i18nTip}" var="i18nTipText" />
</c:if>
<h1 title="${i18nTipText}${tip}">
	<a href="${href}" class="h1">
		<c:if test="${not empty subtitle}">
			<spring:message code="${subtitle}"/>
		</c:if>
		${titleText}
	</a>
</h1>
<jsp:doBody />