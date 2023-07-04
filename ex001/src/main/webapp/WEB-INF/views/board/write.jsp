<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 글쓰기</h2>
<!-- <script type="text/javascript">
	let msg = '${msg}';
	if(msg != null && msg != ''){
		alert(msg);
	}
</script> -->

<form method="post" action="/board/write">
	<input type="text" name="title" value="제목">
	<input type="text" name="content" value="내용">
	<input type="text" name="writer" value="작성자">
	<input type="submit" >
</form>

</body>
</html>