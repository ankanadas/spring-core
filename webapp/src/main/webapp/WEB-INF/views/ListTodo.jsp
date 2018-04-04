<%@ include file="common/header.jspf"%>
<%@ include file="common/navigate.jspf"%>
<h3>Hello ${displayName}</h3>
<div class="container">
<table class="table table-striped">
	<caption>Your List of Events are</caption>
	<thead>
		<tr>
			<th>Date</th>
			<th>Event</th>
			<th>Done</th>
			<th></th>
			<th>Remove</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${todos }" var ="todo"><br />
		<tr>
			<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${todo.targetDate}" /></td>
			<td>${todo.desc }</td>
			<td> ${todo.done }</td>
			<td><a class="btn btn-info" href="/update-todo?id=${todo.id }">Update Todo</a></td>
			<td><a class="btn btn-danger" href="/delete-todo?id=${todo.id }">Delete Todo</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div>
<a class="btn btn-success" href="/add-todo">Add Todo</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>