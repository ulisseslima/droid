<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="var" description="The Event object." rtexprvalue="true" type="com.dvlcube.droid.bean.Event"%>

<c:choose>
	<c:when test="${empty var}">
		<div class="transparent event draft">
			<input:vote priority="0" />
			<input:hidden field="id" />
			<input:text field="title" placeholder="event.new.placeholder" />
			<input:textarea field="description" />
		</div>
	</c:when>
	<c:otherwise>
		<div id="event-${var.id}" class="event">
			<input:vote priority="${var.priority}" />
			<input:hidden field="id" value="${var.id}" />
			<input:text field="title" value="${var.titleHtml}" />
			<input:textarea field="description">${var.description}</input:textarea>
		</div>
	</c:otherwise>
</c:choose>
