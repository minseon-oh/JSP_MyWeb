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
	<%-- choose는 다수의 조건문이 들어간다 switch와 if문의 혼합형 --%>
	<c:choose>
		<c:when test="${param.name eq '홍길동' }"><!-- if -->
			홍길동입니다
		</c:when>
		<c:when test="${param.age >= 20 }"><!-- else if -->
			20세 이상입니다
		</c:when>
		<c:otherwise>
			홍길동도 아니고, 20세 이하입니다 <!-- else -->
		</c:otherwise>
	</c:choose>

</body>
</html>