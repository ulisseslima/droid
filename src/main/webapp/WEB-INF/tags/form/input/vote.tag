<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="priority" description="The priority value." rtexprvalue="true"%>

<input type="button" class="event-vote property priority" name="priority" title="priority" value="${priority}" />
