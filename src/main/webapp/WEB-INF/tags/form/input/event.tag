<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="var" description="The Event object." rtexprvalue="true" type="com.dvlcube.droid.bean.Event"%>

<c:choose>
	<c:when test="${empty var}">
		<div class="transparent event">
			<input:vote priority="0" />
			<input type="hidden" class="event-input property id" name="id">
			<input type="text" class="event-draft event-input property title" name="title" placeholder="new event...">
			<textarea class="event-input property description" name="description" style="border-right: none"></textarea>
		</div>
	</c:when>
	<c:otherwise>
		<div id="event-${var.id}" class="event">
			<input:vote priority="${var.priority}" />
			<input type="hidden" class="event-input property id" name="id" value="${var.id}">
			<input type="text" class="event-input property title" name="title" value="${var.title}">
			<textarea class="event-input property description" name="description" style="border-right: none">${var.description}</textarea>
		</div>
	</c:otherwise>
</c:choose>
