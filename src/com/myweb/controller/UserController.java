package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.myweb.board.service.BoardService;
import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;
import com.myweb.user.service.DeleteServiceImpl;
import com.myweb.user.service.GetInfoServiceImpl;
import com.myweb.user.service.JoinServiceImpl;
import com.myweb.user.service.LoginServiceImpl;
import com.myweb.user.service.UserService;
import com.myweb.user.service.updateServiceImpl;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		UserService service = null;
		int result = 0;
		
		if(command.equals("/user/join.user")) { //조인 화면이동
			
			//조인 기능처리
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
			
		}else if(command.equals("/user/joinForm.user")) { //회원가입처리메서드
			
			service = new JoinServiceImpl();
			result = service.execute(request, response);
			
			if(result == 1) { //이미회원이 존재한 경우
				
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이미 존재하는 회원입니다')");
				out.println("location.href='user_join.jsp'");
				out.println("</script>");
				
			}else { //회원가입성공
				response.sendRedirect("user_login.jsp");
			}
		}else if(command.equals("/user/login.user")) {
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		
		}else if(command.equals("/user/loginForm.user")) {
			/*
			 * 1.LoginServiceImpl()생성 후 실행
			 * 2.서비스에서는 폼값을 받아서 DAO의 로그인 메서드를 사용해서 로그인처리
			 * 3. 로그인에 성공하면 user_id이름으로 세션에 id를 저장하고 user_mypage로 리다이렉트
			 * 4. 로그인 실패시 out.println을 이용해서 "아이디 비밀번호를 확인하세요"전송
			 * 
			 */
			
			service = new LoginServiceImpl();
			result = service.execute(request, response);
			
			if(result == 1) { //로그인 성공
				
				String id = request.getParameter("id");
				session.setAttribute("user_id", id);
				
				response.sendRedirect("user_mypage.jsp");

			}else { //로그인 실패
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인하세요')");
				out.println("history.go(-1)");
				out.println("</script>");
				
			}
			
		}else if(command.equals("/user/mypage.user")) {
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		
		}else if(command.equals("/user/modify.user")) {//정보변경페이지
			
			/*
			 * 게시판에 진입할 떄 회원에 대한 모든 정보를 가져온다.
			 * 1.GetInfoServiceImpl서비스를 생성하고, DAO의 getInfo()메서드로 회원아이디에 따른 회원정보를 얻어온다.
			 * 2.가져온 회원정보를 request에 저장
			 * 3.화면에서는 얻은 정보를 태그에 출력.
			 */
			
			service = new GetInfoServiceImpl();
			service.execute(request, response);

			request.getRequestDispatcher("user_update.jsp").forward(request, response);
		
		}else if(command.equals("/user/updateForm.user")) {
			
			service = new updateServiceImpl();
			result = service.execute(request, response);
			
			if(result == 1) {
				response.sendRedirect("mypage.user");
			}else {
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보수정에 실패하였습니다')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
			
		}else if(command.equals("/user/delete.user")) {

			request.getRequestDispatcher("user_delete.jsp").forward(request, response);
		
		}else if(command.equals("/user/deleteForm.user")) {
			
			service = new DeleteServiceImpl();
			result = service.execute(request, response);
			
			if(result == 1) {
				session.invalidate();
				response.sendRedirect("/MyWeb/main.do");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원비밀번호를 확인하세요')");
				out.println("location.href='delete.user'");
				out.println("</script>");
			}
			
		}else if(command.equals("/user/logout.user")) {
			
			session.invalidate();
			response.sendRedirect("../main.do");
		
		}
	}

}
