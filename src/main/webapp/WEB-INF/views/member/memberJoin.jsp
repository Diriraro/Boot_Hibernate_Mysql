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
	<form:form modelAttribute="memberVO" action="./memberJoin" method="post" enctype="multipart/form-data">

	<div class="form-group">
		<label for="id">id:</label> <form:input type="text" class="form-control" id="id" path="id"/>
		<form:errors path="id"></form:errors>
	</div>
	<div class="form-group">
		<label for="pw">pw:</label> <form:input type="password" class="form-control" id="pw" path="pw"/>
		<form:errors path="pw"></form:errors>
	</div>
	<div class="form-group">
		<label for="pwCheck">pwCheck:</label> <form:input type="password" class="form-control" id="pwCheck" path="pwCheck" />
		<form:errors path="pwCheck"></form:errors>
	</div>
	<div class="form-group">
		<label for="name">name:</label> <form:input type="text" class="form-control" id="name" path="name"/>
		<form:errors path="name"></form:errors>
	</div>
	<div class="form-group">
		<label for="phone">phone:</label> <form:input type="text" class="form-control" id="phone" path="phone"/>
		<form:errors path="phone"></form:errors>
	</div>
	<div class="form-group">
		<label for="email">email:</label> <form:input type="email" class="form-control" id="email" path="email"/>
		<form:errors path="email"></form:errors>
	</div>
	
	<input type="button" class="btn btn-info" id="add" value="FileAdd">
	<div class="form-group" id="f">
		
	</div>		
		
	<button type="submit" class="btn btn-default">Join</button>
</form:form>
	
</div>

<script type="text/javascript">
		var board = '${path}';
		if(board=='Write'){
			$("#num").remove();
		}

		$("#add").click(function(){
			$("#f").append('<input type="file" name="files">');
		});
	</script>

</body>
</html>