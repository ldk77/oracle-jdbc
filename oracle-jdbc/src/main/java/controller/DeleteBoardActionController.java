package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/DeleteBoardActionController")
public class DeleteBoardActionController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		// 유효성 검사 
		if(request.getParameter("boardNo") == null) {
			response.sendRedirect(request.getContextPath() + "/BoardListController");
			System.out.println("boardNo확인");
			return;
		}		
		// 값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));		
		BoardService boardService = new BoardService();
		int result = boardService.deleteBoard(boardNo);		
		// 결과
		if(result == 1) {			
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/BoardListController"); 
		} else {			
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/BoardListOneController?boardNo="+boardNo);
		}
	}	
}
