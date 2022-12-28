package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BoardOneController")
public class BoardOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * VIEW 메뉴구성 
		 * 1) 글수정(로그인멤버 == 글쓴멤버) 
		 * 2) 글사계(로그인멤버 == 글쓴멤버) 
		 *  
		 * */
	}
}
