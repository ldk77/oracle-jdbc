package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW forward /WEB-INF/view/home.jsp
		 *  메뉴구성(로그인 전/후 따라 분기)
		 *  
		 *  로그인전 
		 *  1) 회원가입 ok
		 *  2) 로그인 ok
		 *  
		 *	로그인 후
		 *	1) 로그아웃 ok 
		 *	2) 회원정보 ok -> 좀더 디테일하게 수정 
		 *	3) 게시판리스트 ok    
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
		request.getRequestDispatcher("/WEB-INF/view/member/home.jsp").forward(request, response);	
		
	}
}
