<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Fixed navbar</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarCollapse">
	      <ul class="navbar-nav me-auto mb-2 mb-md-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Link</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link disabled">Disabled</a>
	        </li>
	      </ul>
	      <form class="d-flex" role="search">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
	
	
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
</body>
</html>