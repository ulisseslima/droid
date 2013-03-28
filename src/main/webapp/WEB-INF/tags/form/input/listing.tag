<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="var" description="The Listing object." rtexprvalue="true" type="com.dvlcube.droid.bean.Listing"%>

<c:choose>
	<c:when test="${empty var}">
		<div class="transparent listing draft">
			<input:hidden field="id" />
			<input:text field="title" placeholder="listing.new.placeholder" />
			<input:textarea field="description" />
		</div>
	</c:when>
	<c:otherwise>
		<div id="listing-${var.id}" class="listing">
			<input:hidden field="id" value="${var.id}" />
			<input:text field="title" value="${var.label}" />
			<input:textarea field="description">${var.description}</input:textarea>
		</div>
	</c:otherwise>
</c:choose>
