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
		<form:form action="/questions/new" method="Post" modelAttribute="quest">
			<div class="form-group">
				<form:label path="question" class="form-label">Question: </form:label>
				<form:input path="question" class="form-control"/>
				<form:errors path="question" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="subject" class="form-label">Tags: </form:label>
				<form:input path="subject" class="form-control"/>
				<form:errors path="subject" class="text-danger"/>
			</div>
			<button class="btn btn-success mt-2">Created</button>
		</form:form>
	</div>
</body>
</html>