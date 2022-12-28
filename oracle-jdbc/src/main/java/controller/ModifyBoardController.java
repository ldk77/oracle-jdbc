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


@WebServlet("/board/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	
	// 글 수정 폼
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
		request.setCharacterEncoding("utf-8");			
		// 값 받아오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardService boardService = new BoardService();
		Board board = boardService.getBoardOne(boardNo);		
		
		request.setAttribute("board", board);		
		request.getRequestDispatcher("/WEB-INF/view/board/modifyBoardForm.jsp").forward(request, response);
	}
	// 글 액션 폼
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유효성 검사 
		if(request.getParameter("title") == null || request.getParameter("title").equals("")) {
		response.sendRedirect(request.getContextPath() + "/board/modifyBoard");
			System.out.println("제목을 입력하세요");	
		};
		if(request.getParameter("content") == null || request.getParameter("content").equals("")) {
		response.sendRedirect(request.getContextPath() + "/board/modifyBoard");
			System.out.println("내용을 입력하세요");
		return;
		};
		if(request.getParameter("boardNo") == null) {
		response.sendRedirect(request.getContextPath() + "/board/modifyBoard");
			System.out.println("보드번호를 확인하세요");
		return;
		};
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
		response.sendRedirect(request.getContextPath()+"/board/boardList"); 
		} else {
		System.out.println("수정실패");
		response.sendRedirect(request.getContextPath()+"/board/modifyBoard?boardNo="+boardNo);
		}
	}

}
