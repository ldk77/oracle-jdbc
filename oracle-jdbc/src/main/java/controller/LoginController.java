package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW -> /WEB-INF/view/member/login.jsp
		 * */
	}
	
	//로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 	로그인 세션 정보 : session.setArrtibute("loginMember", "Member타입")
		 *  redirect -> get방식 /home 컨트롤러 요청
		 * 
		 * */
	}

}
