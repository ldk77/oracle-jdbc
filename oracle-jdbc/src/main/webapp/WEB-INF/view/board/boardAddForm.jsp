<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
	<body>
		<form action="${pageContext.request.contextPath}/AddBoardActionController" method="post">
			<table border="1">
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="content"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit">add</button>
		</form>
	</body>
</html>
