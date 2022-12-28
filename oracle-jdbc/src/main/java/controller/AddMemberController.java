package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.MemberService;
import vo.Board;
import vo.Member;

/**
 * Servlet implementation class AddMemberController
 */
@WebServlet("/member/addMember")
public class AddMemberController extends HttpServlet {
	// 회원가입폼 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 *  VIEW forward /WEB-INF/view/member/addMember.jsp
	 * 
	 * */
	request.getRequestDispatcher("/WEB-INF/view/member/addMemberForm.jsp").forward(request, response);	
	}	
	//회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 *  redirect -> get방식 /member/login 컨트롤러 요청
	 * 
	 * */	
	request.setCharacterEncoding("UTF-8");
	// 유효성 검사 
	if(request.getParameter("memberId") == null || request.getParameter("memberId").equals("") 
		|| request.getParameter("memberPw") == null || request.getParameter("memberPw").equals("")
		|| request.getParameter("memberName") == null || request.getParameter("memberName").equals("")) {
		response.sendRedirect(request.getContextPath() + "/member/addMeber");
		System.out.println("내용을 입력하세요");
		return;
		}			
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setMemberPw(memberPw);
		int result = 0; 
		//모델호출 
		MemberService memberService = new MemberService();
		result = memberService.insertMember(member);
		if(result == 1 ){//회원가입성공
			response.sendRedirect(request.getContextPath()+"/member/login");
			System.out.println("회원가입성공");
		} else { //회원가입실패 
			response.sendRedirect(request.getContextPath()+"/member/addMeber");
			System.out.println("회원가입실패");
		}	
			
	}

}
