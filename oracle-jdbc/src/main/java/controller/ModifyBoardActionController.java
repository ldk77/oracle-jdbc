package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

/**
 * Servlet implementation class ModifyBoardActionController
 */
@WebServlet("/ModifyBoardActionController")
public class ModifyBoardActionController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사 
		if(request.getParameter("title") == null || request.getParameter("title").equals("")
		|| request.getParameter("content") == null || request.getParameter("content").equals("")
		|| request.getParameter("no") == null) {
		response.sendRedirect(request.getContextPath() + "/ModifyBoardFormController");
		System.out.println("내용을 입력하세요");
		return;
		}				
		// 값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");		
		Board board = new Board();
		//받아온값 저장
		board.setBoardContent(content);
	   	board.setBoardTitle(title);	
	   	board.setBoardNo(boardNo);
		int result = 0;
		BoardService boardService = new BoardService();
		result = boardService.updateBoard(board);				
		// 결과
		if(result == 1) {
		System.out.println("수정성공");
		response.sendRedirect(request.getContextPath()+"/BoardListController"); 
		} else {
		System.out.println("수정실패");
		response.sendRedirect(request.getContextPath()+"/ModifyBoardFormController?boardNo="+boardNo);
		}
	}
}
