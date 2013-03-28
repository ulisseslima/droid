<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<dvl:page title="stuff.title" js="Entities, Listing">
	<dvl:body subtitle="stuff.subtitle">
		<div id="listing-collection">
			<c:if test="${not empty response.contents}">
				<c:forEach var="listing" items="${response.contents}">
					<input:listing var="${listing}" />
				</c:forEach>
			</c:if>
			<input:listing />
		</div>
	</dvl:body>
</dvl:page>
