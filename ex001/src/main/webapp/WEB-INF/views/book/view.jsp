<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    


<%@include file="./header.jsp" %>
	<!-- 게시판 -->
	<div id="page-wrapper">
	<div class="row">
		<p></p>
        <div class="col-lg-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                <b>도서 제목 </b><br>
                ${book.title }
                </div>
                <div class="panel-body">
                    <img src="https://i.namu.wiki/i/Kv4XGIiFUBtg3Ewl7iDR2gSsSuObZWwSu2igXJVNixUdkhW79upJ4XyWOpFgDEiyk8y_sCe9CLpN-qr9R1qsNg.webp" alt="twbs" width="200" height="200" class="rounded-circle flex-shrink-0">
                </div>
                <div class="panel-footer">
                <b>도서 작가 </b><br>
                ${book.author }
                </div>
          
            </div>
        </div>
	</div>    
	</div>
	<!-- 게시판 끝-->
	
<%@include file="./footer.jsp" %>