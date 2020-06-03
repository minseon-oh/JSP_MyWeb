<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//인코딩은 그대로 해줘야함
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- EL태그로 바로 가져올 수 있다 (request.getParameter("name"); 필요없음) -->
	<!-- EL태그는 값이 안들어와도 null로 표현되지않고 공백으로 표현된다-->
	<input type="text" value="${param.name }"><br>
	${param.name }<br>
	${param.id }<br>
	${param.pw }<br>

</body>
</html>