<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="listing" description="The Listing object." rtexprvalue="true" type="com.dvlcube.droid.bean.Listing"%>
<%@ attribute name="user" description="The Listing object." rtexprvalue="true" type="com.dvlcube.droid.bean.User"%>

<div id="participant-${empty user? 'draft-' : ''}${not empty user? user.id : '0'}" class="participant${empty user? ' draft' : ''}">
	<input type="text" class="auto-width property participant-name" name="name" value="${not empty user? user.name : ''}" placeholder="share..."/>
	<input type="hidden" class="property listingId" name="listingId" value="${not empty listing? listing.id : ''}"/>
</div>