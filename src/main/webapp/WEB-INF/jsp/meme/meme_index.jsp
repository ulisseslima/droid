<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<dvl:page title="meme.title" js="Entities, Meme" css="Meme">
	<dvl:body subtitle="meme.title">
		<div id="meme-collection" class="collection">
			<c:if test="${not empty response.contents}">
				<c:forEach var="meme" items="${response.contents}">
					<input:meme var="${meme}" img="small thumbnail"/>
				</c:forEach>
			</c:if>
			<input:meme img="small thumbnail"/>
		</div>
	</dvl:body>
</dvl:page>
