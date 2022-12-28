package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Member;


@WebServlet("/board/removeBoard")
public class RemoveBoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		// 유효성 검사 
		if(request.getParameter("boardNo") == null) {
			response.sendRedirect(request.getContextPath() + "/board/boardList");
			System.out.println("boardNo확인");
			return;
		}
		HttpSession session = request.getSession();
		// 로그인 전 : loginMember => null
		// 로그인 후 : loginMember => not null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) { //이미 로그인 상태 
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}		
		// 값 받아오기		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));		
		BoardService boardService = new BoardService();
		int result = boardService.deleteBoard(boardNo, loginMember);	
		// 결과
		if(result == 1) {			
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/board/boardList"); 
		} else {			
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/board/boardOne?boardNo="+boardNo);
		}
	}
}
