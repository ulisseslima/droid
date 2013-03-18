<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="field" required="true" description="Field name." %>
<%@ attribute name="value" required="false" description="Input value." %>

<input type="hidden" class="property ${field}" name="${field}" value="${value}">