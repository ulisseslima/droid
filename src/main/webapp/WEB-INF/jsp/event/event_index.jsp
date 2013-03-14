<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<dvl:page title="stuff.title">
	<dvl:body subtitle="stuff.subtitle">
		<div id="events">
			<c:if test="${not empty response.contents}">
				<c:forEach var="event" items="${response.contents}">
					<input:event var="${event}" />
				</c:forEach>
			</c:if>
			<input:event />
		</div>
	</dvl:body>
</dvl:page>
