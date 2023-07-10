<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/ba30180671.js" crossorigin="anonymous"></script>
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
		fetch('/reply/insert/',{method : 'post'
								,headers : {'Content-Type' : 'application/json'}
								,body  : replyJson})
		
		//5. 응답처리
		.then(response => response.json())
		.then(map => replyWriteRes(map)); 
	});
	

};//onload. 끝


//1. 서버에 댓글 리스트 요청
function getList(){
	let bno = document.querySelector('input[name="bno"]').value;
	//url요청 결과를 받아옵니다.
	fetch('/reply/list/'+bno)
	// response.json() : 요청 결과를 js object형식으로 변환
	.then(response => response.json())
	//반환받은 오브젝트를 이용하여 화면에 출력한다.
	.then(list => replyView(list));		
}

//2. 리스트를 화면에 출력
function replyView(list){
	//콘솔창에 리스트 출력
	console.log(list);
	//답글을 div에 출력
	
	//div 초기화
	replyDiv.innerHTML='';
	
	//댓글 리스트로부터 댓글을 하나씩 읽어와서 div에 출력
	list.forEach((reply, index)=>{
		replyDiv.innerHTML 
		+= '<figure id="reply'+index+'">'
		+ '		<blockquote class="blockquote">'
		+ '			<p>'+reply.reply
		+ '				<i class="fa-regular fa-pen-to-square"></i>'
		+ '				<i id="btndelete" class="fa-regular fa-trash-can" onclick="replyDelete('+reply.rno+')"></i>'
		+ '			</p>'
		+ '		</blockquote>'
		
		+ '		<figcaption class="blockquote-footer">'
		+ '			'+reply.replyer+' <cite title="Source Title">'+reply.replyDate+'</cite>'
		+ '		</figcaption>'
		+ '</figure>';	
	});
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

</script>

2
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>


<h2>답글달기</h2>
<input type="text" id="bno" name="bno" value=148>

<div class="input-group mb-3">
  <input type="text" id="replyer">
  <input type="text" id="reply" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnwrite">댓글작성</span>
</div>

<div id="replyDiv"> </div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>