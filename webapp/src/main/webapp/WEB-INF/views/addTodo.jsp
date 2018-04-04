<%@ include file="common/header.jspf"%>
<%@ include file="common/navigate.jspf"%>

<div class="container">
<form:form method="post" commandName="todo">
<form:hidden path="id"/>
<form:hidden path="user"/>
<fieldset class="form-group">
<form:label path="desc">Event Name</form:label>
<form:input path="desc" type="text" class="form-control" required="required" />
<form:errors path="desc" cssClass="text-warning" />
</fieldset>

<fieldset class="form-group">
<form:label path="targetDate">Target Date</form:label>
<form:input path="targetDate" type="text" class="form-control" required="required" />
<form:errors path="targetDate" cssClass="text-warning" />
</fieldset>
<input type="submit" class="btn btn-success" value="Add" />

</form:form>
</div>

<%-- <div class="container">
		<form:form method="post" commandName="todo">
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label>
				<form:input path="desc" type="text" class="form-control"
					required="required"/>

			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
	</div> --%>
<%@ include file="common/footer.jspf"%>