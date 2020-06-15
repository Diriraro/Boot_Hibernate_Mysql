<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<div class="panel panel-default">
			<div class="panel-heading">Title</div>
			<div class="panel-body">${boardVO.title}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Writer</div>
			<div class="panel-body">${boardVO.writer}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Date</div>
			<div class="panel-body">${boardVO.regDate}</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Contents</div>
			<div class="panel-body">${boardVO.contents}</div>
		</div>
		<c:forEach items="${boardVO.boadFiles}" var="fileVO">
			<h2>${fileVO.oriName}</h2>
			<img src="../upload/${board}/${fileVO.fileName}"/>
		</c:forEach>
		<a href="${board}Update?num=${boardVO.num}" class="btn btn-primary">Update</a>
		<a href="${board}Delete?num=${boardVO.num}" class="btn btn-danger">Delete</a>
		<c:if test="${board eq 'qna'}">
		<a href="${board}Reply?num=${boardVO.num}" class="btn btn-success">Reply</a>
		</c:if>
	</div>

</body>
</html>