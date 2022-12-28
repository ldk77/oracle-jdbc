<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HOME</h1><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
	<div><a href="${pageContext.request.contextPath}/member/memberOne">회원정보</a></div>	
	<div><a href="${pageContext.request.contextPath}/board/boardList">게시판리스트</a></div>
</body>
</html>