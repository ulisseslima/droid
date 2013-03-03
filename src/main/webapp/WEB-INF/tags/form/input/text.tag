<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="field" required="true" description="Field name." %>
<%@ attribute name="code" required="false" description="Message code prefix." %>

<label for="${code}.${field}">
	<spring:message code="label.${field}" />
</label>
<input id="form-field-${field}" type="text" class="property" name="${field}" value="${response.content[field]}">