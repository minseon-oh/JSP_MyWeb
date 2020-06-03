package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;

public class DeleteServiceImpl implements UserService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");

		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.login(id, pw);
		
		if(result == 1) {
			return dao.delete(id);	
		}else {
			return 0;
		}

	}

}
