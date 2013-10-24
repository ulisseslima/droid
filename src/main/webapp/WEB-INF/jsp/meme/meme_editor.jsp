<%@include file="/WEB-INF/tags/custom.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<dvl:page titleText="${response.content.label}" js="Entities, Meme" css="Meme">
	<dvl:body titleText="${response.content.label}" href="${pageContext.request.contextPath}/meme/" tip="${response.content.caption}">
		<input:meme var="${response.content}" img="thumbnail"/>
	</dvl:body>
</dvl:page>
