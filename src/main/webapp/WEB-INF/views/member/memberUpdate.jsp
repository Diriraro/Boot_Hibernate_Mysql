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
	<form action="./memberUpdate" method="post" enctype="multipart/form-data">

	<div class="form-group">
		<label for="id">id:</label> <input type="text" class="form-control" id="id" readonly="readonly" value="${member.id}" name="id">
	</div>	
	
	<input type="hidden" class="form-control" id="pw" value="${member.pw}" name="pw">	
	
	<div class="form-group">
		<label for="name">name:</label> <input type="text" class="form-control" id="name" name="name"value="${member.name}" name="name">
	</div>
	<div class="form-group">
		<label for="phone">phone:</label> <input type="text" class="form-control" id="phone" name="phone" value="${member.phone}" name="phone">
	</div>
	<div class="form-group">
		<label for="email">email:</label> <input type="email" class="form-control" id="email" name="email" value="${member.email}" name="email">
	</div>
	<input type="button" class="btn btn-info" id="add" value="FileAdd">
	<div class="form-group" id="f">
		
	</div>		
		
	<button type="submit" class="btn btn-default">Update</button>
</form>
	
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