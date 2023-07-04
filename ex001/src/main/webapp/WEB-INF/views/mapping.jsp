<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
반갑습니다 <br>

model. name : ${name } <br>
model. age : ${age } <br>

RequestParam name : ${param.name } <br>
RequestParam age : ${param.age } <br>
========================================
<h3>VO 출력</h3>
member. name : ${member.name } <br>
member. age : ${member.age } <br>
member. dueDate : ${member.dueDate } <br>
${message } <br>
========================================
<H3>배열 출력</H3>
${ids } <br>





<a href="/mapping/getMapping?name=흑구&age=14">getMapping 호출 </a> <br>

<a href="/mapping/getMappingVO?name=흑구&age=14">getMappingVO 호출 </a> <br>

<a href="/mapping/getMappingVO?name=흑구&age=14&dueDate=2023/07/03">getMappingDate 호출 </a> <br>

<a href="/mapping/getMappingArr?ids=id1&ids=id120&ids=id33">getMappingArr 호출 </a> <br>

<a href="/mapping/getMappingList?ids=id1&ids=id120&ids=id33">getMappingList 호출 </a>콘솔출력 <br>

<a href="/mapping/requestMapping">리퀘스트 매핑 호출</a>

<h3>객체 리스트를 파라메터로 전달 해봅시다</h3>
	파라메터 전달방법 : 
	list[0].name=momo&list[0].age=123&list[1].name=admin&list[1].age=120 <br>
	


<script>
	function voList(){
		let url = "getMappingMemberList"
				+ "?list[0].name=momo&list[0].age=123"
				+ "&list[1].name=admin&list[1].age=120";
		url = encodeURI(url);
		alert(url);
		location.href=url;
	}

</script>

<a href="#" onclick="voList()">getMappingMemberList 객체리스트 전달 </a> 콘솔 호출 <br>

<form action="/mapping/requestMapping" method="post">
	<input type="submit" value="버튼">
</form>

</body>
</html>