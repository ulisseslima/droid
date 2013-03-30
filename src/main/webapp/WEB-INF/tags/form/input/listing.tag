<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>
<%@taglib prefix="dvl" tagdir="/WEB-INF/tags"%>

<%@ attribute name="var" description="The Listing object." rtexprvalue="true" type="com.dvlcube.droid.bean.Listing"%>

<c:choose>
	<c:when test="${empty var}">
		<div id="listing-draft" class="transparent listing draft">
			<dvl:link href="#" label="#" round="true" />
			<input:hidden field="id" />
			<input:text field="title" placeholder="listing.new.placeholder" />
			<input:textarea field="description" />
		</div>
	</c:when>
	<c:otherwise>
		<div id="listing-${var.id}" class="listing">
			<dvl:link href="${var.id}" label=">" round="true" />
			<input:hidden field="id" value="${var.id}" />
			<input:text field="title" value="${var.label}" />
			<input:textarea field="description">${var.description}</input:textarea>
		</div>
	</c:otherwise>
</c:choose>
