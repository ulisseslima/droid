<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@ attribute name="id" description="Custom user id, defaults to the user id." rtexprvalue="true" required="false" %>
<%@ attribute name="var" description="The User object." rtexprvalue="true" type="com.dvlcube.droid.bean.User" required="true" %>

<div id="${empty id? 'user-' : ''}${empty id? var.id : id}" 
	class="${empty id? var.active? 'active' : 'idle' : 'active'} online-user"
	data-focus="${empty var.element? 'nowhere-0' : var.element}"
	data-id="${var.id}"
	data-name="${var.name}">
	<img src="${var.image}" alt="${var.name}">
</div>