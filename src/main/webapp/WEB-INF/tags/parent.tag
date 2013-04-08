<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="id" required="true" description="The id representing this parent."%>
<%@ attribute name="var" required="true" description="The var representing this parent."%>

<div id="${id}" class="parent">
	<input type="hidden" class="id" name="id" value="${var.id}">
	<input type="hidden" class="name" name="name" value="${var.name}">
	<input type="hidden" class="description" name="description" value="${var.description}">
</div>