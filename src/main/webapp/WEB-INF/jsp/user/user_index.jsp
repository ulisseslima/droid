<%@include file="/WEB-INF/tags/custom.jsp"%>

<dvl:page title="stuff.title">
	<dvl:body subtitle="stuff.subtitle">
		<form:async action="add" key="label.add">
			<input:text key="user" field="name" />
			<input:text key="user" field="email" />
		</form:async>

		<dvl:list id="user-collection">
			<c:forEach var="user" items="${response.contents}">
				<div>${user}</div>
			</c:forEach>
		</dvl:list>
	</dvl:body>
</dvl:page>
