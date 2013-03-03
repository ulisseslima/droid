<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="id" required="true" description="Component id."%>
<%@ attribute name="scroll" required="false" description="Whether a scroll button should be available."%>

<div class="collection" id="${id}">
	<jsp:doBody />
</div>

<button class="scroller" onclick="scroll('${id}')">
	<spring:message code="label.load.more" />
	...
</button>