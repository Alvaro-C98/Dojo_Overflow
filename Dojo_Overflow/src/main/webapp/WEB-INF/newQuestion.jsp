<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Question</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
	<h3>New Question</h3>
		<form:form action="/questions/new" method="POST" modelAttribute="quest">
		<p class="text-danger"><c:out value="${error}"></c:out></p>
			<div class="form-group">
				<form:label path="question.question" class="form-label">Question: </form:label>
				<form:input path="question.question" class="form-control"/>
				<form:errors path="question.question" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="tag.subject" class="form-label">Tags: </form:label>
				<form:input path="tag.subject" class="form-control"/>
				<form:errors path="tag.subject" class="text-danger"/>
			</div>
			<button class="btn btn-success mt-2">Created</button>
		</form:form>
	</div>
</body>
</html>