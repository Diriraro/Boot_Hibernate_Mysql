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
		<h2>${board}List</h2>

		<form class="form-inline" action="./${board}List">
			<div class="input-group input-group-sm col-xs-2">
				<select class="form-control" id="sel1" name="kind">
					<option value="title">Title</option>
					<option value="writer">Writer</option>
					<option value="contents">Contents</option>
				</select>
			</div>
			<div class="input-group input-group-sm col-xs-4">
				<input type="text" class="form-control" placeholder="Search"
					name="search">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>

		</form>

		<table class="table table-hover">
			<tr>
				<td>Num</td>
				<td>Title</td>
				<td>Writer</td>
				<td>Date</td>
				<td>Hit</td>
			</tr>

			<c:forEach items="${page.content}" var="vo">
				<tr>
					<td>${vo.num}</td>
					<td>
					<c:catch>				
						<!-- for(int i=0;i<=0;i++) -->
						<c:forEach begin="1" end="${vo.depth}">
							--
						</c:forEach>
					</c:catch>
					<a href="${board}Select?num=${vo.num}">${vo.title}</a></td>
					<td>${vo.writer}</td>
					<td>${vo.regDate}</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</table>

		<div>
			<p>
			<c:if test="${not page.isFirst()}">
				<span>
					<a href="./${board}List?page=0"> &lt;&lt; </a>
				</span>
				
				<span>
					<a href="./${board}List?page=${page.number-1}"> &lt; </a>
				</span>
			</c:if>
			
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				<c:if test="${page.totalPages gt i}">
					<a href="./${board}List?page=${i}">${i+1}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${not page.isLast()}">
				<span>
					<a href="./${board}List?page=${page.number+1}"> &gt; </a>
				</span>
				
				<span>
				<a href="./${board}List?page=${page.totalPages-1}"> &gt;&gt; </a></span>
			</c:if>
			</p>
			
			<c:if test="${not page.isFirst()}">
			<a href="./${board}List?page=${page.number-1}">[이전]</a>
			</c:if>
			
			<span>${page.number+1}</span>
			
			<c:if test="${not page.isLast()}">
			<a href="./${board}List?page=${page.number+1}">[다음]</a>
			</c:if>
		
		</div>

		<a href="./${board}Write" class="btn btn-danger">Write</a>
	</div>



	<script type="text/javascript">
	/* 	var result = $
		{
			result
		};
		if (result != '') {
			if (result == '1') {
				alert('Write Success');
			} else {
				alert('Write Fail');
			}
		} */
	</script>

</body>
</html>