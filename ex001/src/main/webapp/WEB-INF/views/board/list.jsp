<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>게시판</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
    
    <!-- cdn방식으로 css 불러오기 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<!-- 파일형식으로 css 불러오기  -->	
	<!-- <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->
    
	<link href="/resources/css/style.css" rel="stylesheet">


</head>
<body>
	    
	<%@include file = "../common/header.jsp" %>
	
<script type="text/javascript">

	function requestAction(url, bno){
		// 폼이름을 확인!
		searchForm.action = url;
		searchForm.bno.value = bno;
		searchForm.submit();
	}
	
</script>

	<main class="container">
	
	  <div class="bg-light p-5 rounded">
	    <h1>게시판</h1>
	    <p class="lead"><b>${sessionScope.userId }</b>님 환영합니다👋👋 </p>
	    <a class="btn btn-lg btn-primary" href="/board/write?pageNo=${pageDto.cri.pageNo }" role="button">글쓰기 &raquo;</a>
	  </div>
	  
	  <p></p>
	
	<%@include file = "../common/searchForm.jsp" %>
	 
	  <c:forEach var="board" items="${list}">
		  <div class="list-group w-auto">
		  
<%-- 		    <a href="/board/view?bno=${board.bno }&pageNo=${pageDto.cri.pageNo }&searchField=${pageDto.cri.searchField }&searchWord=${pageDto.cri.searchWord }" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true"> --%>
		    <a onclick="requestAction('/board/view', ${board.bno })" href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
		      <img src="https://i.namu.wiki/i/Kv4XGIiFUBtg3Ewl7iDR2gSsSuObZWwSu2igXJVNixUdkhW79upJ4XyWOpFgDEiyk8y_sCe9CLpN-qr9R1qsNg.webp" alt="twbs" width="42" height="42" class="rounded-circle flex-shrink-0">
		      <div class="d-flex gap-2 w-100 justify-content-between">
		        <div>
		          <p class="mb-0 opacity-75">작성자 : ${board.writer }</p>
		          <h6 class="mb-0">${board.title }</h6>
		        </div>
		        <small class="opacity-50 text-nowrap">등록일 : ${board.regDate }</small>
		      </div>
		    </a>
		  </div>
	  </c:forEach>
	  
	  		<!-- 페이지 불러오기 -->
			<%@include file = "../common/pageNavi.jsp" %>
	</main> 

	
      
      
      
      
	<!-- cdn방식의 css불러오기 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <!-- <script src="../assets/dist/js/bootstrap.bundle.min.js"></script> --> 
    
    
    
	
  </body>
</html>
