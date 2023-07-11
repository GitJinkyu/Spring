<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


<title>Insert title here</title>
</head>


<body>

<%@include file = "../common/header.jsp" %>

<script type="text/javascript">

function requestAction(url){
	viewForm.action=url;
	viewForm.submit();
}
</script>
<main class="container">
	 	<div class="bg-light p-5 rounded">
		    <h1>게시판 상세보기</h1>
		    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>	    												   
		    <a class="btn btn-lg btn-primary" href="/board/list?pageNo=${param.pageNo }&searchField=${param.searchField }&searchWord=${param.searchWord }" role="button">리스트 &raquo;</a>
		</div>
		  
		  <p></p>
		  
	<div class="mb-3" style="margin: 3rem;">
		  <!-- 글쓰기 -->
		<form method="get" name="viewForm">
			
			<div class="list-group w-auto">
				<img src="https://i.namu.wiki/i/Kv4XGIiFUBtg3Ewl7iDR2gSsSuObZWwSu2igXJVNixUdkhW79upJ4XyWOpFgDEiyk8y_sCe9CLpN-qr9R1qsNg.webp" alt="twbs" width="45" height="45" class="rounded-circle flex-shrink-0">
			</div>
			<br>
			<input type="hidden" name="bno" id="bno" value="${board.bno }">
			<div class="mb-3">
			  <label for="title" class="form-label">제목</label>
			  <input name="title" type="text" class="form-control" id="title" value="${board.title }" readonly>
			</div>
			
			<div class="mb-3">
			  <label for="content" class="form-label">내용</label>
			  <textarea name="content" class="form-control" id="content" rows="3" readonly>${board.content }</textarea>
			</div>
			
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label>
			    <input name="writer" type="text" class="form-control" id="writer" value="${board.writer }" readonly>
				</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-center">
				<button type="submit" class="btn btn-primary btn-lg" onclick="requestAction('/board/write')">수정</button>
				<button type="submit" class="btn btn-primary btn-lg" onclick="requestAction('/board/delete')">삭제</button>
			</div>
			
		</form>
	</div>
	
	<%@include file = "../reply/test.jsp" %>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>