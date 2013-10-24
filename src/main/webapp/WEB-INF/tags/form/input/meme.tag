<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@attribute name="var" description="The Meme object." rtexprvalue="true" type="com.dvlcube.droid.bean.Meme"%>
<%@attribute name="img" description="Image css." rtexprvalue="true" required="true"%>
<spring:url var="imgpath" value="/static/img" />

<c:choose>
	<c:when test="${empty var}">
		<div id="meme-draft" class="transparent meme draft">
			<input:hidden field="id" />
			<input:hidden field="caption" />
			
			<input:text field="name" placeholder="meme.field.name" />
			<input:img type="${img}" field="image" placeholder="meme.field.image"/>
			<c:if test="${not fn:contains(img, 'small')}">
				<input:span type="draggable text"><spring:message code="meme.field.caption" /></input:span>
				<input:hidden field="captionX" />
				<input:hidden field="captionY" />
			</c:if>
			<c:if test="${fn:contains(img, 'small')}">
				<input:span type="text"><spring:message code="meme.field.caption" /></input:span>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<div id="meme-${var.id}" class="meme">
			<input:hidden field="id" value="${var.id}" />
			<input:hidden field="caption" value="${var.caption}" />
			
			<input:text field="name" value="${var.label}" />
			<input:img id="${var.id}" type="${img}" field="image" src="${var.image}" placeholder="meme.field.image"/>
			<c:if test="${not fn:contains(img, 'small')}">
				<input:span type="draggable text" x="${var.captionX}" y="${var.captionY}">${var.caption}</input:span>
				<input:hidden field="captionX" value="${var.captionX}" />
				<input:hidden field="captionY" value="${var.captionY}" />
			</c:if>
			<c:if test="${fn:contains(img, 'small')}">
				<input:span type="text">${var.caption}</input:span>
			</c:if>
		</div>
	</c:otherwise>
</c:choose>
