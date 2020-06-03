<%@page import="java.util.ArrayList"%>
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
	<h3>1~100까지 홀수의 합</h3>
	<% 
	int sum = 0;
	for(int i=1; i<=100; i+=2){ 
		sum += i;
	}
	%>
	
	결과: <%=sum %>
	
	<hr>
	<h3>foreach구문</h3>
	<c:set var="sum" value="0" scope="page"></c:set>
	<c:forEach var="i" begin="1" end="100" step="2">
		<c:set var="sum" value="${sum+i }"></c:set>
	</c:forEach>
	결과:${sum }
	
	<hr>
	<h3>3단 foreach구문</h3>
	<c:forEach var="i" begin="1" end="9" step="1" >
		3 x ${i } = ${3*i } <br>
	</c:forEach>

	<hr>
	<h3>구구단 foreach구문</h3>
	<c:forEach var="i" begin="2" end="9" step="1">
		<c:forEach var="j" begin="1" end="9" step="1">
			${i } x ${j } = ${i*j }<br>
		</c:forEach>
		<br>
	</c:forEach>
	
	<h3>향상된 포문</h3>
	<%
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		session.setAttribute("list", list);
	%>
	<c:forEach var="i" items="${sessionScope.list }">
		${i }
	</c:forEach>
	
	
	
	
	
</body>
</html>