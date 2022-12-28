package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;


@WebServlet("/ModifyBoardFormController")
public class ModifyBoardFormController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");			
		// 값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardService boardService = new BoardService();
		Board board = boardService.getBoardOne(boardNo);		
		
		request.setAttribute("board", board);		
		request.getRequestDispatcher("/WEB-INF/view/modifyBoardForm.jsp").forward(request, response);
	}
}
