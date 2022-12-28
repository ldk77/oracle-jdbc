<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%
	//1) controller 
	int currentPage = 1; 
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10;
	//2)Model
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gdj58","java1234");
	System.out.println("오라클 접속 성공!");

	String sql = "select t2.rnum rnum, t2.name from (select rownum rnum, t.member_name name from (select member_name from member order by member_name asc) t) t2 where rnum between ? and ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	
	int beginRow = (currentPage - 1) * rowPerPage +1; 
	int endRow = beginRow + rowPerPage - 1;
	stmt.setInt(1, beginRow);
	stmt.setInt(2, endRow);
	ResultSet rs = stmt.executeQuery();
%>
<!-- view -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>멤버 이름 목록(페이징)</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
		</tr>
		<%
			while(rs.next()){
		%>
				<tr>
					<td><%=rs.getInt("rnum") %></td>
					<td><%=rs.getString("name") %></td>
				</tr>
		<%		
			}
		%>
	</table>
	<a href="<%=request.getContextPath()%>/memberList.jsp?currentPage=<%=currentPage-1%>">이전</a>
	<a href="<%=request.getContextPath()%>/memberList.jsp?currentPage=<%=currentPage-1%>">다음</a>
</body>
</html>