<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<dvl:page title="event.events" titleText="- ${listing.name}" js="Entities, Graphics, Participants, Event">
	<dvl:body titleText="${listing.name}" href="${pageContext.request.contextPath}/listing/" tip="${listing.description}">
		<input:hidden field="parentId" value="${listing.id}" />
		
		<div id="event-collection" class="sortable collection">
			<c:if test="${not empty response.contents}">
				<c:forEach var="event" items="${response.contents}">
					<input:event var="${event}" />
				</c:forEach>
			</c:if>
			<input:event />
		</div>
		
		<div id="online-user-collection">
			<out:online-user id="me" var="${me}" />
		</div>
	</dvl:body>
</dvl:page>
