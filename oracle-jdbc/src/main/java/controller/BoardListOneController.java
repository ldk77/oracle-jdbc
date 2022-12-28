package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/BoardListOneController")
public class BoardListOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 유효성 검사 
	if(request.getParameter("boardNo") == null) {
		response.sendRedirect(request.getContextPath() + "/BoardListController");
		System.out.println("no확인");
		return;
	}		
	// 값 받아오기
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));			
			BoardService boardService = new BoardService();
			Board board = boardService.getBoardOne(boardNo);
			
			// view에서 필요
			request.setAttribute("board", board);		
			request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);	
	}	
}
