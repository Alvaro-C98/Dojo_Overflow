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
<title>New Answer</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<div class="container">
	<h2><c:out value="${question.getQuestion()}"></c:out></h2>
	<a href="../dashboard" class="float-end">Back home</a>
	<c:set var="list" value="" />
	<c:forEach items="${question.getTags()}" var="t" varStatus="loop">
		<c:set var="list" value="${list}${t.getSubject()}" />
		<c:if test="${!loop.last}">
        	<c:set var="list" value="${list}, " />
   		</c:if>
	</c:forEach>
	<h4>Tags: ${list}</h4>
	<div class="row mt-4">
		<div class="col-4">
		<p>Answers:</p>
		<c:forEach items="${question.getAnswer()}" var="a">
			<p><c:out value="${a.getAnswer()}"></c:out></p>
		</c:forEach>
		</div>
		<div class="col-4">
			<h3>Add your answer:</h3>
			<form:form action="/question/${question.getId()}" method="POST" modelAttribute="ans">
				<div class="form-group">
					<form:label path="answer" class="form-label">Answer: </form:label>
					<form:input path="answer" class="form-control"/>
					<form:errors path="answer" class="text-danger"/>
				</div>
				<button class="btn btn-success mt-2">Answer it!</button>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>