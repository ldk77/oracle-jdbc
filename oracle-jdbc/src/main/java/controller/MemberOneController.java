package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;


@WebServlet("/member/memberOne")
public class MemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW -> /WEB-INF/view/member/memberOne.jsp 
		 *  메뉴구성 
		 *  1) 회원정보수정 
		 *  2) 회원탈퇴 
		 *  
		 * 
		 * */
		HttpSession session = request.getSession();
		// 로그인 전 : loginMember => null
		// 로그인 후 : loginMember => not null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) { //이미 로그인 상태 
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);	
		
	}

}
