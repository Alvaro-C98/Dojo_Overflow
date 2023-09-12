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
<title>Questions</title>

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
	<h3>Questions Dashboard</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Question</th>
					<th>Tags</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${question}" var="q">
						<tr>
							<td><a href="question/${q.getId()}">${q.getQuestion()}</a></td>
							<c:set var="list" value="" />
							<c:forEach items="${q.getTags()}" var="t" varStatus="loop">
								<c:set var="list" value="${list}${t.getSubject()}" />
								    <c:if test="${!loop.last}">
        								<c:set var="list" value="${list}, " />
   									</c:if>
							</c:forEach>
							<td>${list}</td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="../questions/new" class="float-end"><button>Create Question</button></a>
	</div>
</body>
</html>