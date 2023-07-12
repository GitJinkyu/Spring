<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form class="row g-3" action="/board/list" name="searchForm">
	
	<input type="hidden" class="form-control" name="bno" id="bno" >
	<input type="hidden" class="form-control" name="pageNo" id="pageNo" value="${pageDto.cri.pageNo }">
	<div class="row g-3 justify-content-center">
	  <div class="col-auto">
	  
		<select name="searchField" class="form-select" aria-label="Default select example">
		  <option value="title" ${pageDto.cri.searchField eq "title" ? "selected" : " " }>제목</option>
		  <option value="content" ${pageDto.cri.searchField eq "content" ? "selected" : " " }>내용</option>
		  <option value="writer" ${pageDto.cri.searchField eq "writer" ? "selected" : " " }>작성자</option>
		</select>
	  </div>
	  
	  <!--검색바 사이즈 조절 -> col-sm-숫자  -->
	  <div class="col-sm-6">
	    <label for="searchWord" class="visually-hidden"></label>
	    <input  type="text" class="form-control" name="searchWord" id="searchWord" value="${pageDto.cri.searchWord }">
	  </div>
	  
	  <div class="col-auto">
	    <button type="submit" class="btn btn-primary mb-3" onclick='go(1)'>검색</button>
	  </div>
	</div>
	  
	</form>
</body>
</html>