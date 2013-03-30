<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<dvl:page title="event.events" titleText="- ${response.contents[0].parent.title}" js="Entities, Event">
	<dvl:body titleText="${response.contents[0].parent.title}">
		<input:hidden field="parentId" value="${response.contents[0].parent.id}" />
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
