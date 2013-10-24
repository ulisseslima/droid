<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="field" required="false" description="Field name." %>
<%@ attribute name="tip" required="false" description="Field tooltip key." %>
<%@ attribute name="src" required="false" description="Image source." %>
<%@ attribute name="placeholder" required="false" description="Placeholder text key." %>
<%@ attribute name="id" required="false" description="Parent id." %>
<%@ attribute name="type" required="true" description="Additional css." %>

<spring:url var="imgpath" value="/static/img" />

<c:if test="${not empty tip}">
	<spring:message code="${tip}" var="tipMsg" />
</c:if>

<c:set var="imgSrc" value="${empty src? imgpath : ''}${not empty src? src : '/img-placeholder.png'}"/>
<c:if test="${not empty field}">
	<input:text field="${field}" value="${imgSrc}" tip="${tip}" placeholder="${placeholder}"/>
</c:if>
<img class="${type}" src="${imgSrc}" alt="..." title="${tipMsg}"/>

<script>
	var id = "${id}";
	if (id) {
		img = $("#meme-${id}").children("img");
		if (document.location.href.indexOf("edit") != -1) {
			img.click(function () {
				document.location.href = "${pageContext.request.contextPath}/meme/view/0x0/${id}";
			});
		} else {
			img.click(function () {
				document.location.href = "${pageContext.request.contextPath}/meme/edit/${id}";
			});
		}
	}
</script>