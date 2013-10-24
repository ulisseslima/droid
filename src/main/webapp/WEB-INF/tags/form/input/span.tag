<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="x" required="false" description="X coords." %>
<%@ attribute name="y" required="false" description="Y coords." %>
<%@ attribute name="type" required="true" description="Y coords." %>

<span class="${type}" style="left: ${empty x? '0' : x}px; top: ${empty y? '0' : y}px"><jsp:doBody /></span>
