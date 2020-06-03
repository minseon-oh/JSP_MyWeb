package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class updateServiceImpl implements UserService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String) request.getParameter("id");
		String pw = (String) request.getParameter("pw");
		String name = (String) request.getParameter("name");
		String email = (String) request.getParameter("email");
		String address = (String) request.getParameter("address");
		
		UserVO vo = new UserVO(id, pw, name, email, address, null);
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.update(vo);

		return result;
	}

}
