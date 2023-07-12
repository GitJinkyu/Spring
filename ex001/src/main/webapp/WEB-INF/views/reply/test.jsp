<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/ba30180671.js"
	crossorigin="anonymous"></script>
<script>
window.onload = function() {
	
	//화면이 띄워지면 바로 리스트가 보일수있도록 리스트 출력 함수를 호출
	getList();
	
	//댓글작성하기
	btnwrite.addEventListener('click',function(){
		//1.서버에 전송할 파라메터 수집
		let bno = document.querySelector('#bno').value;
		let reply= document.querySelector('#reply').value;
		let replyer = document.querySelector('#replyer').value;
		
		console.log('bno',bno);
		console.log('reply',reply);
		console.log('replyer',replyer);
		
		//2.전송할 데이터를 javascript Object객체로 생성
		let replyObj = {
			//  이름 : 값(위에 선언한 let bno, let reply...)
				bno : bno
				,reply : reply
				,replyer: replyer	
		};
		
		//3. 만든 Object객체를 json타입으로 변환
		let replyJson = JSON.stringify(replyObj);
		
		console.log('replyOjb',replyObj);
		console.log('replyJson',replyJson);
		
		//4. 서버에 요청
		//url요청 결과를 받아옵니다.
		
		fetchPost('/reply/insert/', replyObj,replyWriteRes);
		
		/* 
		이부분을 fetchPost함수로 만들어놓음
		fetch('/reply/insert/',{method : 'post'
								,headers : {'Content-Type' : 'application/json'}
								,body  : replyJson})
		
		//5. 응답처리
		.then(response => response.json())
		.then(map => replyWriteRes(map));  */
	});
	

};//onload. 끝


//1. 서버에 댓글 리스트 요청
function getList(){
	//댓글을 보여줄 게시물
	let bno = document.querySelector('#bno').value;
	let page = document.querySelector('#page').value;
	
	console.log(bno);
	
	fetchGet('/reply/list/'+ bno + '/' + page, replyView);

/* 	//url요청 결과를 받아옵니다.
	fetch('/reply/list/'+bno+'/'+ page)
	// response.json() : 요청 결과를 js object형식으로 변환
	.then(response => response.json())
	//반환받은 오브젝트를 이용하여 화면에 출력한다.
	.then(map => replyView(map)); */
	
}

function getPage(page){
	document.querySelector('#page').value = page;
	getList();
	
}
//2. 리스트를 화면에 출력
function replyView(map){
	
	let list = map.list;
	let pageDto = map.pageDto;
	
	//콘솔창에 리스트 출력
	console.log(list);
	console.log(pageDto);
	
	
	//답글을 div에 출력
	
	//div 초기화
	replyDiv.innerHTML='';
	
	//댓글 리스트로부터 댓글을 하나씩 읽어와서 div에 출력
	list.forEach((reply, index)=>{
		replyDiv.innerHTML            //data-value 답글 수정 눌렀을때 답글내용 불러오기위해 사용
			+= '<figure id="reply'+index+'" data-value="'+reply.reply+'">'
			+ '		<blockquote class="blockquote">'
			+ '			<p>'+reply.reply
			+ '				<i class="fa-regular fa-pen-to-square" onclick="replyEdit('+index+','+reply.rno+')"></i>'
			+ '				<i id="btndelete" class="fa-regular fa-trash-can" onclick="replyDelete('+reply.rno+')"></i>'
			+ '			</p>'
			+ '		</blockquote>'
			
			+ '		<figcaption class="blockquote-footer">'
			+ '			'+reply.replyer+' <cite title="Source Title">'+reply.replyDate+'</cite>'
			+ '		</figcaption>'
			+ '</figure>';	
	});
	
	let pageBlock='';
	
	pageBlock +=
	//페이지 블록 생성
			  '<nav aria-label="...">'
			+ '  <ul class="pagination">';
		
	if(pageDto.prev){
		let disableStr = (pageDto.prev) ? '':'disabled';
					//prev 버튼
			pageBlock +=		
			  '    <li class="page-item '+disableStr+'" onclick="getPage('+(pageDto.startNo-1)+')">'
			+ '      <a class="page-link" href="#">Previous</a>'
			+ '    </li>';
	}
	
			//페이지 블록
	for (i = pageDto.startNo; i <= pageDto.endNo; i++) {
		let activeStr = (pageDto.cri.pageNo == i) ? 'active':'';
		pageBlock += 
	  		  '    <li class="page-item '+activeStr+'">'
	  		 + '<a class="page-link" href="#" onclick="getPage('+i+')">'+i+'</a></li>';
	};
	
	if(pageDto.next){
		let disableStr = (pageDto.next) ? '':'disabled';
	pageBlock += //next 버튼
			 '    <li class="page-item '+disableStr+'" onclick="getPage('+(pageDto.endNo+1)+')">'
			+ '      <a class="page-link" href="#">Next</a>'
			+ '    </li>'
	}
	pageBlock +=
			 '  </ul>'
			+ '</nav>';
	
	replyDiv.innerHTML += pageBlock;
}

function replyWriteRes(map){
	if(map.result =='success'){
		//등록성공
		getList();
		
	}else{
		//등록실패
		alert(map.message);
	}
}

function replyDelete(rno){
	
	//url요청 결과를 받아옵니다.
	fetch('/reply/delete/'+rno)
	// response.json() : 요청 결과를 js object형식으로 변환
	.then(response => response.json())
	//반환받은 오브젝트를 이용하여 화면에 출력한다.
	.then(map => replyWriteRes(map));		
}

//수정화면 보여주기
function replyEdit(index,rno){
	let edit = document.querySelector('#reply'+index);
	let replyTxt = edit.dataset.value; //답글 수정 눌렀을때 기존 답글내용 접근하기
	edit.innerHTML ='' 
				   +'<div class="input-group mb-3">'
		  		   +'<input type="text" id="editReply'+rno+'" value="'+replyTxt+'" class="form-control"  aria-label="Recipients username" aria-describedby="basic-addon2">'
	  			   +'<span class="input-group-text" id="tnwrite1" onclick="replyEditAction('+rno+')">댓글 수정</span>'
				   +'</div>';
	
}

function replyEditAction(rno){
	
	//1.서버에 전송할 파라메터 수집
	let bno = document.querySelector('#bno').value;
	let reply = document.querySelector('#editReply'+rno).value;
	let replyer = document.querySelector('#replyer').value;
	
	console.log('bno',bno);
	console.log('editReply',reply);
	console.log('replyer',replyer);
	
	//2.전송할 데이터를 javascript Object객체로 생성
	let replyObj = {
		//  이름 : 값(위에 선언한 let bno, let reply...)
			 bno : bno
			,rno : rno
			,reply : reply
			,replyer: replyer	
	};
	
	//3. 만든 Object객체를 json타입으로 변환
	let replyJson = JSON.stringify(replyObj);
	
	console.log('replyOjb',replyObj);
	console.log('replyJson',replyJson);
	
 	//4. 서버에 요청
	//url요청 결과를 받아옵니다.
	fetch('/reply/update/',{method : 'post'
							,headers : {'Content-Type' : 'application/json'}
							,body  : replyJson})
	
	//5. 응답처리
	.then(response => response.json())
	.then(map => replyWriteRes(map)); 
	
}

//get방식 요청
function fetchGet(url,callback){
	console.log(url);
	console.log(callback);
	
	try {
	//url 요청
	fetch(url)
		//요청 결과json 문자열을 javascript 객체로 반환
		.then(response => response.json())
		//매개로 받은 콜백함수 실행
		.then(map => callback(map));
		
	} catch (e) {
		console.log(e);
			
	}
	
	
}

//post방식 요청
function fetchPost(url,obj,callback){
	console.log(url);
	console.log(callback);
	
	try {
		//url 요청
		fetch(url,{method : 'post'
					,headers : {'Content-Type' : 'application/json'} 
					,body  : JSON.stringify(obj)
			  })
			//요청 결과json 문자열을 javascript 객체로 반환
			.then(response => response.json())
			//매개로 받은 콜백함수 실행
			.then(map => callback(map));
			
		} catch (e) {
			console.log(e);
				
		}
	
	
}

</script>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

</head>
<body>


	<h2>댓글창</h2>
	<input type="hidden" id="bno" name="bno" value=148> 
	<input type="hidden" id="page" name="page" value=1> 
	<!-- 닉네임:<input type="text" id="replyer"> -->
	<input type="text" id="replyer" class="form-control"
			placeholder="닉네임" aria-label="Recipient's username"
			aria-describedby="basic-addon2"  style="width: 100px; text-align: center;">
	<br>
	<div class="input-group mb-3">
		<input type="text" id="reply" class="form-control"
			placeholder="댓글을 입력하세요" aria-label="Recipient's username"
			aria-describedby="basic-addon2"> <span
			class="input-group-text" id="btnwrite">댓글작성</span>
	</div>

	<div id="replyDiv"></div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>