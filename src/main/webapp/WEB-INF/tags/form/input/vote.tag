<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="priority" description="The priority value." rtexprvalue="true"%>

<input type="hidden" class="hidden-priority" name="priority" value="${priority}" />
<input type="button" class="round event-vote property priority" title="priority" value="${priority}" />
