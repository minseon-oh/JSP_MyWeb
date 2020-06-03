<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-idth, initial-scale=1">
   

    <title>Welcome to MyWorld</title>
	
	<!-- 외부 CSS파일을 임포트 -->
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
	
	 <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<meta charset="UTF-8">
</head>
<body>
<%@include file="../include/header.jsp" %>
<div align="center" class="div_center">
	<h3>게시판 글 수정 페이지</h3>
	<hr>
	
	<form action="update.board" method="post" name="regForm">
		
		<!-- hidden -->
		<!-- 반드시 넘어가야하지만 화면에 보여줄 필요가 없을 때 쓰는 hidden -->
		<!-- disabled는 값이 넘어가지 않음 -->
		<input type="hidden" name="bno" value="${vo.bno}">
		
		<table border="1" width="500">
			
			<tr>
				<td>글 번호</td>
				<td><input type="text" name="bno" value="${vo.bno}" disabled></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="${vo.writer}" disabled></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="title" value="${vo.title}"></td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content">${vo.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정 하기" onclick="check()">&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='list.board'">        
				</td>
			</tr>
			
		</table>
	</form>
	
</div>
<%@include file="../include/footer.jsp" %>

<script>
	function check(){
		if(document.regForm.title.value == ''){
			alert("제목은 필수입니다")
			return false;
		}else if(confirm("수정하시겠습니까?")){
			document.regForm.submit();
		}
	}
</script>

</body>
</html>