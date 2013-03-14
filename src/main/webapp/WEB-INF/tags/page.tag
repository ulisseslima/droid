<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="title" required="true" description="Page title message key." %>
<%@ attribute name="titleBody" required="false" description="Page title as string." %>

<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dvlcube -
		<c:choose>
			<c:when test="${not empty title}">
				<spring:message code="${title}" />
			</c:when> 
			<c:otherwise>
				${title}
			</c:otherwise>
		</c:choose>
		</title>
		<spring:url var="jspath" value="/static/script" />
		<spring:url var="csspath" value="/static/style" />
		<link rel="stylesheet" type="text/css" href="${csspath}/reset.css">
		<script type="text/javascript" src="${jspath}/jquery.js"></script>
		<script async="async" type="text/javascript" src="${jspath}/Main.js"></script>
		<script type="text/javascript" src="${jspath}/Event.js"></script>
		<style type="text/css">@import url("${csspath}/Main.css");</style>
	</head>
	<body>
		<section id="main-content">
			<span id="response-message">
				<span id="response-message">
					<c:if test="${not empty response.message}">
						<spring:message code="${response.message}" />
					</c:if>
				</span>
			</span>
			<jsp:doBody />
		</section>
	</body>
</html>