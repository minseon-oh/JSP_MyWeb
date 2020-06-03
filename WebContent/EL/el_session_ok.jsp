<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- EL태그로 세션받는 법 -->
	세션값: ${sessionScope.uuu }<br>
	세션값: ${sessionScope.vo.id }<br>
	${sessionScope.vo.name }<br>
	
</body>
</html>