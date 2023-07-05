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

<script type="text/javascript">

// 메세지 처리
/*
   		부트스트랩을 이용한 모달창 띄우기
 		
 		1. css,js 링크 추가하기
 		2. 모달요소 복사
 			타이틀,메세지 수정
 		3. 모달창 열기(message.jsp가 호출되자마자 버튼 뜨게)
 			자바스크립트를 이용해서 모달 객체 생성 후 show()메서드 호출해서 바로 보여줌
 		4. 모달창 닫기 (닫기 버튼 and 배경화면 아무곳 누르기)
 			자바스크립트를 이용해서 모달차을 닫을 경우
 			닫는 이벤트 발생(hidden.bs.modal)->
			그후 뒤로가기(history(-1)) 추가
 */
 

let msg = '${msg}';

window.onload = function(){
	if(msg != ''){
		document.querySelector(".modal-body").innerHTML = msg;
		
		let myModal = new bootstrap.Modal('#myModal', {keyboard: false});
		myModal.show();
	
	}
}
</script>
</head>
<body>
	    
	<%@include file = "../common/header.jsp" %>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<main class="container">
	
	  <div class="bg-light p-5 rounded">
	    <h1>게시판</h1>
	    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
	    <a class="btn btn-lg btn-primary" href="/board/write" role="button">글쓰기 &raquo;</a>
	  </div>
	  
	  <p></p>
	
	 
	  <c:forEach var="board" items="${list}">
		  <div class="list-group w-auto">
		    <a href="/board/view?bno=${board.bno }" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
		      <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
		      <div class="d-flex gap-2 w-100 justify-content-between">
		        <div>
		          <h6 class="mb-0">${board.title }</h6>
		          <p class="mb-0 opacity-75">작성자 : ${board.writer }</p>
		        </div>
		        <small class="opacity-50 text-nowrap">등록일 : ${board.regDate }</small>
		      </div>
		    </a>
		  </div>
	  </c:forEach>
	  
	</main> 
 	<table width="100%">
		<tr>
			<td >
			<%@include file = "../common/pageNavi.jsp" %>
			</td>
		</tr>
	</table>
	
      
      
      
      
	<!-- cdn방식의 css불러오기 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <!-- <script src="../assets/dist/js/bootstrap.bundle.min.js"></script> --> 
    
    
    
	
  </body>
</html>
