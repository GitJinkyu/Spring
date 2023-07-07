<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<script>

	function deleteBook(){
		//체크박스가 선택된 요소의 값을 ,로 연결
		delNoList = document.querySelectorAll("[name=delNo]:checked");
		
		//체크된 박스의 값 뽑아내기
		let delNo = "";
		delNoList.forEach((e)=> {
			delNo += e.value + ',';
		});
		
		//마지막요소일때 ',' 제거
		delNo = delNo.substr(0,delNo.length-1);
		
		console.log("삭제할번호 : " + delNo);
		//삭제요청
		searchForm.action = "/book/delete";
		
		//서치폼 delNo 인풋 박스의 벨류 값을 위에서 구한 delNo를 대입
		searchForm.delNo.value=delNo;
		searchForm.submit();
		
	}
	
	function allCheck() {
		  // 체크박스의 상태를 확인하고 원하는 동작을 수행합니다.
		  if (document.getElementsByName("allCheck")[0].checked) {
		    // 체크박스가 체크된 상태일 때 실행할 코드
		    // 함수 호출 또는 원하는 동작을 수행하세요.
		    delNoBox = document.querySelectorAll("[name=delNo]");
		    delNoBox.forEach(function(checkbox) {
		        checkbox.checked = checkbox.checked = true});
		
		    console.log("체크박스가 체크되었습니다.");
		  } else {
		    // 체크박스가 체크 해제된 상태일 때 실행할 코드
		    // 함수 호출 또는 원하는 동작을 수행하세요.
		    delNoBox = document.querySelectorAll("[name=delNo]");
		    delNoBox.forEach(function(checkbox) {
		        checkbox.checked = checkbox.checked = false});
		    
		    console.log("체크박스가 체크 해제되었습니다.");
		  }
	}
	
	function requestAction(url, no){
		// 폼이름을 확인!
		searchForm.action = url;
		searchForm.no.value = no;
		searchForm.submit();
	}
	

</script>
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
	/* if(msg != null && msg != ''){
		alert(msg);
		history.go(-1);
	} */
window.onload = function(){
	if(msg != null && msg != ''){
		alert(msg);
	};
	
	
	
}
</script>




<%@include file = "./header.jsp" %>

        <!-- 게시판 -->
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">도서 목록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	총 건수 : ${totalCnt }
                        </div>
						<!-- 검색폼 -->
						<%@include file = "./searchForm.jsp" %>
						<!-- 검색폼끝 -->
						<div class="form-inline text-left">
						<button class="form-control" onclick="deleteBook()" style="margin-left: 15px; display: inline;">도서 삭제</button>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">							
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" name="allCheck" onclick="allCheck()"></th>
                                        <th>도서 제목</th>
                                        <th>도서 작가</th>
                                        <th>도서 번호</th>
                                        <th>대여 여부</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="book" items="${list}">
                                    <tr class="odd gradeX">
                                        <td><input type="checkbox" name="delNo" value=${book.no }></td>
                                        <td><a onclick="requestAction('/book/view', ${book.no })" href="#">${book.title }</a></td>
                                        <td>${book.author}</td>
                                        <td class="center">${book.no }</td>
                                        <td class="center">${book.rentStr }</td>
                                    </tr>
                                </c:forEach> 
                                </tbody>
                            </table>

                            <%@include file = "./pageNavi.jsp" %>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            </div>
        <!-- 게시판 끝 -->
            
<%@include file = "./footer.jsp" %>
 