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


@WebServlet("/board/boardOne")
public class BoardOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * VIEW 메뉴구성 
		 * 1) 글수정(로그인멤버 == 글쓴멤버) 
		 * 2) 글사계(로그인멤버 == 글쓴멤버) 
		 *  
		 * */
		// 로그인 후에만 진입가능 
		HttpSession session = request.getSession();
		// 로그인 전 : loginMember => null
		// 로그인 후 : loginMember => not null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) { //이미 로그인 상태 
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		// 유효성 검사 		
		if(request.getParameter("boardNo") == null) {
			response.sendRedirect(request.getContextPath() + "/board/boardList");
			System.out.println("no확인");
			return;
		}		
		// 값 받아오기
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));			
				BoardService boardService = new BoardService();
				Board board = boardService.getBoardOne(boardNo);
				
				// view에서 필요
				request.setAttribute("board", board);		
				request.getRequestDispatcher("/WEB-INF/view/board/boardOne.jsp").forward(request, response);
	}
}
