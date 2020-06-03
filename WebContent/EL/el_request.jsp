<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO vo = new UserVO();
	vo.setId("abc123");
	vo.setName("이순신");
	
	//request에 강제저장
	request.setAttribute("vo", vo);
	//포워드이동
	RequestDispatcher dp = request.getRequestDispatcher("el_request_ok.jsp");
	dp.forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>