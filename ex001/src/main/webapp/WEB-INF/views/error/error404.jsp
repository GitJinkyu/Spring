<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>🎉404 예외 발생 🎉</h1>
페이지를 찾을 수 없습니다 !
예외 : ${exception.message }

	<ul>
	   <c:forEach items="${exception.getStackTrace() }" var="stack">
	     <li><c:out value="${stack}"></c:out></li>
	   </c:forEach>
    </ul>
   
</body>
</html>