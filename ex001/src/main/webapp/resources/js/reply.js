console.log("js파일 연결 완료")

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

//덧글 조회 및 출력
function getReplyList(page){
	let bno = document.querySelector('#bno').value;
	//let page = document.querySelector('#page').value;
	
	/**
	 * falsey : false, 0, "", NaN, undefined, null
	 * falsey한 값 이외의 값이 들어 있으면 true를 반환
	 * 
	 *  page에 입력된 값이없으면 1로 세팅
	 * 	 * @param map
	 * @returns
	 */
	if(!page){
		page = 1;
	}
	
	console.log("bno : "+bno);
	console.log("page : "+page);
	
	//백팃 쓰는법
	console.log(`/reply/list/${bno}/${page}`);
	
	fetchGet(`/reply/list/${bno}/${page}`, replyView);
}

function replyView(map){
	let list = map.list;
	let pageDto = map.pageDto;
	
	console.log("리스트 받고있니? -> ",list);
	console.log("pageDto 받고있니? -> ",pageDto);
	
	if(list.length > 0 ){
		let replyDivStr = '' 
						+  '<table class="table text-break text-center">                       '
						+ '  <thead>                                   '
						+ '    <tr>                                    '
						+ '      <th scope="col" class="col-1">#</th>                '
						+ '      <th scope="col" class="col-5">댓글</th>            '
						+ '      <th scope="col"class="col-1">작성자</th>             '
						+ '    </tr>                                   '
						+ '  </thead>                                  '
						+ '  <tbody>                                   ';					
							
	list.forEach((reply, index)=>{
		replyDivStr 	+='' 					
						+ '    <tr id="tr'+reply.rno+'" data-value="'+reply.reply+'">                                    '
						+ '      <th scope="row">'+reply.rno+'</th>                '
						+ '      <td class="text-start">'+reply.reply
if(userId == reply.replyer){
		replyDivStr		+='			<i class="fa-regular fa-pen-to-square"  onclick="replyEdit('+reply.rno+')"></i>	'
						+ ' 		<i class="fa-regular fa-trash-can" onclick="replyDelete('+reply.rno+')"></i>		'
							};
		replyDivStr		+=''
						+ '		 </td>        										'
						+ '      <td>'+reply.replyer
						+ '        <br>'
						+ '           '+reply.replyDate         
						+ '      </td>                         '
						+ '    </tr>                                   ';
	});
	
		replyDivStr 	+='' 
						+ '  </tbody>                                  '
						+ '</table>                                    ';
							replyDiv.innerHTML = replyDivStr;
							
	//페이지 블록 붙이기
	let pageBlock 		=``						
						+` <nav aria-label="...">                                                `
						+`   <ul class="pagination justify-content-center">                      `
						if(pageDto.prev){		
			 pageBlock +=``
						+`     <li class="page-item" onclick="getReplyList(${(pageDto.startNo-1)})">                                   `
						+`       <a class="page-link disabled">Previous</a>                               `
						+`     </li>                                                             `;
						}
	
						for(i=pageDto.startNo; i <= pageDto.endNo; i++){
							let active = pageDto.cri.pageNo == i ? 'active' : '';
			 pageBlock +=``
				 		+`     <li class="page-item"><a class="page-link ${active}" href="#" onclick="getReplyList(${i})">${i}</a></li>    `
						};
						
						if(pageDto.next){
			 pageBlock +=``
				 		+`     <li class="page-item" onclick="getReplyList(${(pageDto.endNo+1)})">                                            `
						+`       <a class="page-link" href="#">Next</a>                          `
						+`     </li>                                                             `
						+`   </ul>                                                               `
						+` </nav>                                                                `;
						}
							replyDiv.innerHTML += pageBlock;
	
	}else{
		replyDiv.innerHTML='댓글이 없어용';
	}
						
	
}

function replyWrite(){
	//bno,reply,replyer(나중엔 세션에서 가져올거임)
	let bno = document.querySelector('#bno').value;
	let reply = document.querySelector('#reply').value;
	let replyer = userId; //jsp내에서 변수로 선언해놓고 js에 가져와서 사용

	
	//전달할 객체로 생성
	let obj = {bno : bno
			,  reply : reply
			,  replyer : replyer}
	
	//url : /reply/insert/
	//ojb : JSON형식으로 전달할 데이터
	//callback : 처리할때 호출할 콜백함수
	//			*메서드로 짜놓은 fetch 메서드에선 콜백함수를 넣을때 함수의 이름만 넣는다 
	//			 	뒤에 ()를 포함하면 함수가 바로실행이 되어버림
	fetchPost('/reply/insert/',obj,replyRes)
}

//답글 등록, 수정, 삭제의 결과를  처리
function replyRes(map){
	console.log(map);
	//성공 : 리스트 조회 및 출력
	//실패 : 메세지 출력
	
	if(map.result =='success'){
		//등록성공
		getReplyList();
		
	}else{
		//등록실패
		alert(map.message);
	}
}

function replyDelete(rno){
	console.log("매개변수 rno를 잘 받고 있니? :"+rno)
	fetchGet(`/reply/delete/${rno}`, replyRes);
}

function replyEdit(rno){
										//rno매개변수를 줄때는 function replyEdit(rno)의 rno를 그대로 줘야함. 
										//reply.rno 아님
	let tr = document.querySelector('#tr'+rno);
	let replyTxt = tr.dataset.value; //답글 수정 눌렀을때 기존 답글내용 유지 접근하기
	tr.innerHTML = ''
		+'<th colspan="3">                                '
		+'<div class="input-group">																								'
		+'  <span class="input-group-text">답글 수정</span>                                                                       	'
		+'  <input type="text" aria-label="First name" class="form-control" id="editReply'+rno+'" value="'+replyTxt+'">                                         '
		+'  <input type="text" aria-label="Last name" class="input-group-text" id="btnReplyWrite" onclick="replyEditAction('+rno+')" value="수정하기">               '
	  	+'</div>                                                                                                                '
        +'</th>'                                                                                                                        
}

function replyEditAction(rno){

	let reply = document.querySelector('#editReply'+rno).value;

	
	//전달할 객체로 생성
	let obj = {rno : rno
			,  reply : reply}
	
	//url : /reply/insert/
	//ojb : JSON형식으로 전달할 데이터
	//callback : 처리할때 호출할 콜백함수
	//			*콜백함수를 넣을땐 함수의 이름만 넣는다 
	//			 	뒤에 ()를 포함하면 함수가 바로실행이 되어버림
	fetchPost('/reply/update/',obj,replyRes)
	
}

