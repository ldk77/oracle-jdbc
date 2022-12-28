<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#rowPerPage').change(function() {
			$('#pageForm').submit();
			alert('change');
		})
	});
</script>
</head>
<body>
	<h1>BOARD LIST</h1>
	<div>
		<a href="${pageContext.request.contextPath}/AddBoardFormController">글등록</a>
	</div>
	<form id="pageForm" method="get" action="${pageContext.request.contextPath}/BoardListController">
		<select name="rowPerPage" id="rowPerPage">
			<c:if test="${rowPerPage == 10}">
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
			</c:if>
			<c:if test="${rowPerPage == 20}">
				<option value="10">10</option>
				<option value="20" selected="selected">20</option>
				<option value="30">30</option>
			</c:if>
			<c:if test="${rowPerPage == 30}">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30" selected="selected">30</option>
			</c:if>
		</select>
	</form>
	<table border="1">
		<tr>
			<th>boardNo</th>
			<th>boardTitle</th>
			<th>createdate</th>
		</tr>
		<c:forEach var="b" items="${boardList}">
			<tr>
				<td>${b.boardNo}</td>
				<td><a href='${pageContext.request.contextPath}/BoardListOneController?boardNo=${b.boardNo}'>${b.boardTitle}</a></td>
				<td>${b.createdate}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/BoardListController?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/BoardListController?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>
