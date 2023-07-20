<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    
     <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
    
    <!-- cdn방식으로 css 불러오기 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<!-- 파일형식으로 css 불러오기  -->	
	<!-- <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->
    
	<link href="/resources/css/style.css" rel="stylesheet">


    
<title>글 작성</title>
</head>

<body>
<%@include file = "../common/header.jsp" %>

<script type="text/javascript">

function requestAction(url){
	writeForm.action=url;
	writeForm.submit(); 
}
window.addEventListener('load', function() {
	btnList.addEventListener('click',function(){
		writeForm.action='/board/list';
		writeForm.method ='get';
		writeForm.submit();
	});
});

</script>


 
 	<div class="bg-light p-5 rounded">
	    <h1>게시판</h1>
	    <p class="lead">글쓰기 페이지</p>
	    <a class="btn btn-lg btn-primary" href="#" id="btnList" role="button">리스트 &raquo;</a>
	</div>
	  
	  <p></p>
	  
<div class="mb-3" style="margin: 3rem;">
	  <!-- 글쓰기 -->
	<form method="post" name="writeForm">
			<input type="text" name="pageNo" value="${param.pageNo }">
			<input type="text" name="searchField" value="${param.searchField }">
			<input type="text" name="searchWord" value="${param.searchWord }">

	
	<c:if test="${not empty board.bno}">
  		<input type="text" name="bno" value="${board.bno}">
	</c:if>
	
		<div class="mb-3">
		  <label for="title" class="form-label">제목</label>
		  <input name="title" type="text" class="form-control" id="title" value="${board.title }">
		</div>
		
		<div class="mb-3">
		  <label for="content" class="form-label">내용</label>
		  <textarea name="content" class="form-control" id="content" rows="3">${board.content }</textarea>
		</div>
		
		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label>
		    <input name="writer" type="text" class="form-control" id="writer" value="${sessionScope.userId }">
		</div>

		<div class="mb-3">
			<label for="files" class="form-label">첨부파일</label>
		    <input name="files" type="file" class="form-control" id="files" >
		</div>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
		
		<c:choose>
		  <c:when test="${empty board.bno}">
			<button type="button" class="btn btn-primary btn-lg" onclick="requestAction('/board/write')">글쓰기</button>			
		  </c:when>
		  <c:otherwise>
			<button type="button" class="btn btn-primary btn-lg" onclick="requestAction('/board/edit')">수정완료</button>
		  </c:otherwise>
		</c:choose>
			<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
		</div>
		
	</form>

</div>

	



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>