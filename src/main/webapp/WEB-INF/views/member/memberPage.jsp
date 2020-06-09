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
	

	<div class="form-group">
		<label for="id">id:</label> <input type="text" class="form-control" id="id" value="${member.id}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pw">pw:</label> <input type="text" class="form-control" id="pw" value="${member.pw}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="name">name:</label> <input type="text" class="form-control" id="name" value="${member.name}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="phone">phone:</label> <input type="text" class="form-control" id="phone" value="${member.phone}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="email">email:</label> <input type="email" class="form-control" id="email" value="${member.email}" readonly="readonly">
	</div>
	<a href="./memberUpdate">
	<button type="submit" class="btn btn-success">Update</button>
	</a>	

	<div class="form-group">
	<img src="../upload/memberFile/${memberFile.fileName}" style="width: 500px;height: 500px;">
	<a href="./fileDelete">
	<button type="submit" class="btn btn-danger">FileDelete</button>
	</a>
	</div>
		
	
</div>
</body>
</html>