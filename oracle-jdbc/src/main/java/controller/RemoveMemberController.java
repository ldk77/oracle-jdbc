package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RemoveMemberController")
public class RemoveMemberController extends HttpServlet {
	//회원 탈퇴 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW forward /WEB-INF/view/member/removeMember.jsp
		 * 
		 * */
	}
	//회원 탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  redirect -> get방식 /member/logout 컨트롤러 요청
		 * 
		 * */	
	}

}
