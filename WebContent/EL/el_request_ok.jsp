<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${requestScope.vo.id }<br>
	${requestScope.vo.name }<br>
	
	<!-- requestScope는 생략해서 사용가능. 세션같은경우는 웬만해선 생략안하는게좋다. 남이 몰라보기때문 -->
	${vo.id}<br>
	${vo.name }<br>
</body>
</html>