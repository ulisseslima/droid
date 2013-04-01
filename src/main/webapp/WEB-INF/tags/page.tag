<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="title" required="false" description="Page title message key." %>
<%@ attribute name="titleText" required="false" description="Page title as string." %>
<%@ attribute name="js" description="Comma separated list of script files, sans extension." %>
<%@ attribute name="css" description="Comma separated list of css files, sans extension." %>

<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dvlcube -
			<c:if test="${not empty title}">
				<spring:message code="${title}" />
			</c:if> 
			${titleText}
		</title>
		<spring:url var="jspath" value="/static/script" />
		<spring:url var="csspath" value="/static/style" />
		<link rel="stylesheet" type="text/css" href="${csspath}/reset.css">
		<script type="text/javascript" src="${jspath}/jquery.js"></script>
		<script async="async" type="text/javascript" src="${jspath}/json2.js"></script>
		<script async="async" type="text/javascript" src="${jspath}/form2js.js"></script>
		<script type="text/javascript" src="${jspath}/Main.js"></script>
		<%
		if (js != null && !js.isEmpty()) {
			String[] scripts = js.split(",");
			for (String script : scripts) {
				String scriptName = script.trim() + ".js";
				%><script async="async" type="text/javascript" src="${jspath}/<% out.print(scriptName);%>"></script><% 
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