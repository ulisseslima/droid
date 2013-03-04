<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="var" description="The Event object." rtexprvalue="true" type="com.dvlcube.droid.bean.Event"%>

<c:choose>
	<c:when test="${empty var}">
		<div class="transparent event">
			<input:vote />
			<input type="hidden" class="event-input property id" name="id">
			<input type="text" class="event-draft event-input property title" name="title" placeholder="event name...">
			<input type="text" class="event-draft event-input property priority" name="priority" value="0">
			<textarea class="event-input property description" name="description"></textarea>
		</div>
	</c:when>
	<c:otherwise>
		<div id="event-${var.id}" class="event">
			<input:vote />
			<input type="hidden" class="event-input property id" name="id" value="${var.id}">
			<input type="text" class="event-input property title" name="title" value="${var.title}">
			<input type="text" class="event-input property priority" name="priority" value="${var.priority}">
			<textarea class="event-input property description" name="description">${var.description}</textarea>
		</div>
	</c:otherwise>
</c:choose>
