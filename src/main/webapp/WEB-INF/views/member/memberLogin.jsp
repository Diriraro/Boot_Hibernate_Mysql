<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>

</head>
<body>
<c:import url="../template/nav.jsp"></c:import>

<div class="container">
	<h1>Login Page</h1>
	<form:form modelAttribute="memberVO" action="./memberLogin" method="post" enctype="multipart/form-data">

	<div class="form-group">
		<label for="id">id:</label> <form:input type="text" class="form-control" id="id" path="id"/>
		<form:errors path="id"></form:errors>
	</div>
	<div class="form-group">
		<label for="pw">pw:</label> <form:input type="password" class="form-control" id="pw" path="pw"/>
		<form:errors path="pw"></form:errors>
	</div>		
		
	<button type="submit" class="btn btn-default">Join</button>
</form:form>
	
</div>
</body>
</html>