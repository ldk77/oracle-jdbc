package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Board;
import vo.Member;


@WebServlet("/board/addBoard")
public class AddBoardController extends HttpServlet {
	//글쓰기 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능 
		HttpSession session = request.getSession();
		// 로그인 전 : loginMember => null
		// 로그인 후 : loginMember => not null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) { //이미 로그인 상태 
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/board/boardAddForm.jsp").forward(request, response);
	}
	//글쓰기 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 유효성 검사 
		if(request.getParameter("title") == null || request.getParameter("title").equals("") || request.getParameter("content") == null || request.getParameter("content").equals("") ) {
			response.sendRedirect(request.getContextPath() + "/board/addBoard");
			System.out.println("내용을 입력하세요");
			return;
		}
		// 로그인 후에만 진입가능 
		HttpSession session = request.getSession();
		// 로그인 전 : loginMember => null
		// 로그인 후 : loginMember => not null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) { //이미 로그인 상태 
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setBoardContent(content);
	   	board.setBoardTitle(title);
	   	board.setMemberId(loginMember.getMemberId());
	   	
		int result = 0; 
		//모델호출 
		BoardService boardService = new BoardService();
		result = boardService.insertBoard(board);
		
		   if(result == 1 ){//성공 		 
		         response.sendRedirect(request.getContextPath()+"/board/boardList?msg="+2);
		   } else {//실패
			   response.sendRedirect(request.getContextPath()+"/board/addBoard");
		   }	
	}

}
