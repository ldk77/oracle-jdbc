package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {
	// 회원정보 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  VIEW forward /WEB-INF/view/member/modifyMember.jsp
		 * 
		 * */
	}
	// 회원정보 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  redirect -> get방식 /member/memberOne?key값 컨트롤러 요청
		 * 
		 * */	
	}

}
