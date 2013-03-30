<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="listing" value="${response.contents[0].parent}"/>
<dvl:page title="event.events" titleText="- ${listing.title}" js="Entities, Event">
	<dvl:body titleText="${listing.title}"
		href="${listing.id}">
		
		<input:hidden field="parentId" value="${listing.id}" />
		<div id="event-collection">
			<c:if test="${not empty response.contents}">
				<c:forEach var="event" items="${response.contents}">
					<input:event var="${event}" />
				</c:forEach>
			</c:if>
			<input:event />
		</div>
	</dvl:body>
</dvl:page>
