<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="js" description="Comma separated list of script files, sans extension." %>
<%@ attribute name="css" description="Comma separated list of css files, sans extension." %>
<%@ attribute name="title" description="Page title message key." required="false" %>
<%@ attribute name="titleText" description="Page title as string." required="false" %>

<spring:url var="jspath" value="/static/script" />
<spring:url var="csspath" value="/static/style" />

<!doctype html>
<html>
	<head profile="http://www.w3.org/2005/10/profile">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="icon" 
		      type="image/png" 
		      href="${csspath}/favicon.ico">
		<title>Dvlcube -
			<c:if test="${not empty title}">
				<spring:message code="${title}" />
			</c:if> 
			${titleText}
		</title>
		<link rel="stylesheet" type="text/css" href="${csspath}/reset.css">
		<script type="text/javascript" src="${jspath}/jquery.js"></script>
		<script type="text/javascript" src="${jspath}/jquery-ui.js"></script>
		<script async type="text/javascript" src="${jspath}/json2.js"></script>
		<script async type="text/javascript" src="${jspath}/form2js.js"></script>
		<script type="text/javascript" src="${jspath}/Main.js"></script>
		<script defer type="text/javascript" src="${jspath}/Draggable.js"></script>
		<%
		if (js != null && !js.isEmpty()) {
			String[] scripts = js.split(",");
			for (String script : scripts) {
				String scriptName = script.trim() + ".js";
				%><script defer type="text/javascript" src="${jspath}/<% out.print(scriptName);%>"></script><% 
			}
		}
		%>
		<style type="text/css">@import url("${csspath}/Main.css");
			<%
			if (css != null && !css.isEmpty()) {
				String[] styles = css.split(",");
				for (String style : styles) {
					String cssName = style.trim() + ".css";
					%>@import url("${csspath}/<% out.print(cssName);%>");<% 
				}
			}
			%>
		</style>
	</head>
	<body>
		<input type="hidden" id="url-context" value="${pageContext.request.contextPath}">
		<section id="main-content">
			<span id="response-message">
				<c:if test="${not empty response.message}">
					<spring:message code="${response.message}" />
				</c:if>
			</span>
			<jsp:doBody />
		</section>
		<footer>
			<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
		</footer>
	</body>
</html>