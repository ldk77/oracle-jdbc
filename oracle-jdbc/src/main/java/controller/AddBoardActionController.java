package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;


@WebServlet("/AddBoardActionController")
public class AddBoardActionController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 유효성 검사 
		if(request.getParameter("title") == null || request.getParameter("title").equals("") || request.getParameter("content") == null || request.getParameter("content").equals("") ) {
			response.sendRedirect(request.getContextPath() + "/AddBoardFormController");
			System.out.println("내용을 입력하세요");
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setBoardContent(content);
	   	board.setBoardTitle(title);
	   	
		int result = 0; 
		//모델호출 
		BoardService boardService = new BoardService();
		result = boardService.insertBoard(board);
		
		   if(result == 1 ){ 		 
		         response.sendRedirect(request.getContextPath()+"/BoardListController");
		   } else {
			   response.sendRedirect(request.getContextPath()+"/AddBoardFormController");
		   }		  
		}
}
