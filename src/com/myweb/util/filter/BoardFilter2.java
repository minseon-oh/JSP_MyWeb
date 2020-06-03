package com.myweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//수정,변경,삭제의 경우 세션과 작성자가 동일한 경우만 삭제
@WebFilter({"/board/modify.board","/board/update.board","/board/delete.board"})
public class BoardFilter2 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("user_id");//세션아이디
		String writer = request.getParameter("writer");//화면에서 넘어오는 작성자
		
		if(writer == null || !id.equals(writer)){
			res.setContentType("text/html; charset=utf-8"); //응답에대한 내용선언
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다')");
			out.println("location.href='/MyWeb/main.do'");
			out.println("</script>");
			
			return;
		}
		chain.doFilter(request, response);
	}

}
