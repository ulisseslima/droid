<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="input" tagdir="/WEB-INF/tags/form/input"%>

<%@attribute name="var" description="The Meme object." rtexprvalue="true" type="com.dvlcube.droid.bean.Meme"%>
<spring:url var="imgpath" value="/static/img" />

<div id="meme-${var.id}" class="meme-full">
	<input:hidden field="id" value="${var.id}" />
	<input:img id="${var.id}" type="thumbnail" src="${var.image}"/>
	<input:span type="immutable-text" x="${var.captionX}" y="${var.captionY}">${var.caption}</input:span>
</div>
