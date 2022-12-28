package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.MemberService;
import vo.Board;
import vo.Member;


@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {
	// 회원정보 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW forward /WEB-INF/view/member/modifyMember.jsp
		 * 
		 * */
		//로그인정보
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");	
		// 값 받아오기			
		request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp").forward(request, response);	
		
	}
	// 회원정보 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  redirect -> get방식 /member/memberOne?key값 컨트롤러 요청
		 * 
		 * */
		// 유효성 검사 
		if(request.getParameter("memberId") == null || request.getParameter("memberId").equals("")) {
		response.sendRedirect(request.getContextPath() + "/member/modifyMember");
			System.out.println("ID를 확인하세요");	
		};
		if(request.getParameter("memberPw") == null || request.getParameter("memberPw").equals("")) {
			response.sendRedirect(request.getContextPath() + "/member/modifyMember");
			System.out.println("비밀번호을 확인하세요");
		return;
		};
		if(request.getParameter("memberName") == null || request.getParameter("memberName").equals("")) {
			response.sendRedirect(request.getContextPath() + "/member/modifyMember");
			System.out.println("이름을 확인하세요");
		return;
		};
		// 값 받아오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");		
		Member member = new Member();
		//받아온값 저장
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		int result = 0;
		MemberService memberService = new MemberService();
		result = memberService.modifyMember(member);				
		// 결과
		if(result == 1) {
		System.out.println("수정성공");
		response.sendRedirect(request.getContextPath()+"/member/logout"); 
		} else {
		System.out.println("수정실패");
		response.sendRedirect(request.getContextPath()+"/member/modifyMember");
		}
	}

}
