<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD ONE</title>
</head>	
	<body>
		<h1>게시글 보기</h1>		
		<div>
			<a href="${pageContext.request.contextPath}/BoardListController">홈</a>
		</div>
		<table border="1">
			<tr>
				<th>제목</th>
				<td>
					${board.boardTitle}
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					${board.boardContent}
				</td>
			</tr>
		</table>
		<div>
			<a href='${pageContext.request.contextPath}/DeleteBoardActionController?boardNo=${board.boardNo}'>삭제</a>
			<a href='${pageContext.request.contextPath}/ModifyBoardFormController?boardNo=${board.boardNo}'>수정</a>
		</div>		
	</body>
</html>