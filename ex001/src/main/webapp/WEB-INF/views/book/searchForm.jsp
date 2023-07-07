<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form  action="/book/list" name="searchForm">
	<input type="text"  name="no" id="no" >
	<input type="text"  name="delNo" id="delNo" >
	<input type="text"  name="pageNo" id="pageNo" value="${pageDto.cri.pageNo }">

	<div class="form-inline text-center">
	<p></p>
	  <div class="form-group">
		<select class="form-control" name="searchField" >
		  <option value="title" ${pageDto.cri.searchField eq "title" ? "selected" : " " }>도서 제목</option>
		  <option value="author" ${pageDto.cri.searchField eq "author" ? "selected" : " " }>도서 작가</option>
		  <option value="no" ${pageDto.cri.searchField eq "no" ? "selected" : " " }>도서 번호</option>
		</select>
	  </div>
	  <div class="form-group">
	    <label for="searchWord" ></label>
	    <input  class="form-control" type="text"  name="searchWord" id="searchWord" value="${pageDto.cri.searchWord }">
	  </div>
	    <button class="btn btn-default" type="submit"  onclick='go(1)'>검색</button>
	</div>
	  
	</form>
</body>
</html>