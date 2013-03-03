<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="event-vote-div">
	<button class="event-vote vote-up">
		<spring:message code="event.priority.up" />
	</button>
	<button class="event-vote vote-down">
		<spring:message code="event.priority.down" />
	</button>
</div>