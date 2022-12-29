package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;


@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
	//회원 탈퇴 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW forward /WEB-INF/view/member/removeMember.jsp
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
		request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);		
	}
	//회원 탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  redirect -> get방식 /member/logout 컨트롤러 요청
		 * 
		 * */
		request.setCharacterEncoding("UTF-8");
		// 유효성 검사 
		if(request.getParameter("memberId") == null || request.getParameter("memberId").equals("") 
			|| request.getParameter("memberPw") == null || request.getParameter("memberPw").equals("")) {
			response.sendRedirect(request.getContextPath() + "/member/removeMember");
			System.out.println("내용을 입력하세요");
			return;
			}			
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");		
			//디버깅
			System.out.println(memberId);
			System.out.println(memberPw);
			
			//저장
			Member member = new Member();
			member.setMemberId(memberId);		
			member.setMemberPw(memberPw);
			int result = 0; 
			//모델호출 
			MemberService memberService = new MemberService();
			result = memberService.removeMember(member);
			//디버깅
			System.out.println(result);
			if(result == 1 ){//회원탈퇴성공
				response.sendRedirect(request.getContextPath()+"/member/logout");
				System.out.println("회원탈퇴성공");
			} else { //회원탈퇴실패 
				response.sendRedirect(request.getContextPath()+"/member/removeMember");
				System.out.println("회원탈퇴실패");
			}	
				
		}
}

