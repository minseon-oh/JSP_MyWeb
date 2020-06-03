<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- TEST에는 조건식이 들어간다 --%>
	<c:if test="true">
		<b>무조건들어가는문장</b>
	</c:if>
	<hr>
<%-- 자바와 비교
	<% if(request.getParameter("name").equals("이순신")){ %>
		<b>이름이 이순신 입니다</b>
	<% } %> 
--%>
	<c:if test="${param.name == '이순신' }">
		<b>이름이 이순신 입니다</b>
	</c:if>
	<c:if test="${param.name eq '홍길동' }">
		<b>이름이 홍길동 입니다</b>
	</c:if>
	
	<hr>
	<!-- age파라미터값이 20 이상이면 성인출력 미만이라면 미성년자출력 -->
	<c:if test="${param.age >= 20 }">
		<b>성인입니다</b>
	</c:if>
	<c:if test="${param.age < 20 }">
		<b>미성년자입니다</b>
	</c:if>
	
</body>
</html>