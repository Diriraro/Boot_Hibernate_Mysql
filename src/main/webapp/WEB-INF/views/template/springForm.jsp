<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="boardVO" action="${board}${path}" method="post" enctype="multipart/form-data">
	<form:input path="num" type="text" name="num" id="num" />
	<div class="form-group">
		<label for="title">Title:</label> 
		<form:input path="title" type="text" class="form-control" id="title"  />
		<form:errors path="title" cssClass="r"></form:errors>
	</div>
	<div class="form-group">
		<label for="writer">Writer:</label> 
		<form:input type="text" path="writer" class="form-control" id="writer" value="${member.id}" readonly="true"/>
		<form:errors path="writer"></form:errors>
	</div>
	<div class="form-group">
		<label for="contents">Contents:</label>
		<form:textarea path="contents" rows="" cols="" class="form-control" id="summernote"></form:textarea>
		<form:errors path="contents"></form:errors>
	</div>
	
	<input type="button" class="btn btn-info" id="add" value="FileAdd">
	<div class="form-group" id="f">
		
	</div>
	
	<button type="submit" class="btn btn-default">Submit</button>
</form:form>