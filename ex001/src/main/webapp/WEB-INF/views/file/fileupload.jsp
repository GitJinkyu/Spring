<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="/resources/js/fetch.js"></script>
<script>
window.addEventListener('load', function() {
	
	btnGetList.addEventListener('click',function(){
		getFileList();
	})
	
	//파일업로드
	btnFileupload.addEventListener('click',function(){
		//웹개발에서 HTML 폼 데이터를 Javascript로 쉽게 조작하고 전송하는 api이다		
		let formData = new FormData(FileUploadForm);
					 //[0]이름     [1]값
 		formData.append('name','momo');
		
		//Formdata값 확인
		//[0]배열은 이름
		//[1]배열은 값
		console.log("formData : ", formData);
		for(var pair of formData.entries()){
			if(typeof(pair[1]) ==  'object'){
				let fileName = pair[1].name;
				let fileSize = pair[1].size;
				
				//파일 확장자, 크기 체크
				//업로드가능한 최대 용량을 초과하지않았는지 확인
				//서버에 전송 가능한 형식인지 확인
				if(!checkExtension(fileName,fileSize)){
					return false
				} 
				
				console.log('fileName : ',fileName);				
				console.log('fileSize : ',fileSize);
			}
		}
		
		fetch('/file/fileUploadActionFetch',{method:'post',body: formData})
		.then(response => response.json())
		.then(map => fileuploadRes(map));
	});
	
	
	
	
	
	
});


function checkExtension(fileName, fileSize){
	let maxSize = 1024 * 1024 * 2;
	// .exe   .sh   .zip   .alz 로 끝나는 문자열
	// 정규표현식 : 특정 규칙을 가진 문자열을 검색 하거나 치환할때 사용
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	
	if(maxSize <= fileSize){
		alert("파일 사이즈 초과!")
		return false;		
	}
	
	//문자열에 정규식 패턴을 만족하는 값이 있으면 true, 없으면 false를 리턴한다
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 없습니다.")
		return false;
	}
	
	return true;
}

function fileuploadRes(map){
	if(map.result == 'sucess'){
		divFileuploadRes.innerHTML = map.msg;
		getFileList()
		
	}else{
		alert(map.msg);
		getFileList()
	}
}

function getFileList(){
	///file/list/{bno}
	
	let bno = document.querySelector('#bno').value;
	fetch('/file/list/'+bno)
		.then(response => response.json())
		.then(map => viewFileList(map));
}

function FileDelete(e){
	//e.data 값 가져오는법
	console.log(e.dataset.bno,e.dataset.uuid,e.dataset.aaa)
	
	let bno = e.dataset.bno
	let uuid = e.dataset.uuid
	
	//fetch요청
	//jsp 자바스크립트에서 백틱쓰려면 변수앞에 \${} 역슬래쉬 붙여줘야함
	//EL 표현식과 충돌나서 에러발생하는것
	//*주석처리해도 변수 앞에 역슬래쉬 안붙이면 에러 뜸!!*
	 fetchGet(`/file/delete/\${uuid}/\${bno}`, fileuploadRes); 
	//fetchGet('/file/delete/'+uuid+'/'+bno+'', fileuploadRes);
}

function viewFileList(map){
	console.log(map);
	
	let content = '';
	if(map.list.length > 0 ){
		map.list.forEach(function(item,index){
			let savePath = encodeURIComponent(item.savePath);

			console.log('세이브 패스 여기다 -=>',savePath)
			content +=''
					+'<a href="/file/download?filename='+savePath+'">  '
					+ item.filename
					+'</a>'
					+'<i class="fa-regular fa-trash-can" onclick="FileDelete(this)"		' 
					+'data-bno="'+item.bno+'" data-uuid="'+item.uuid+'"></i>		'
					+' <br>			';
		})
	}else{
		content = '등록된 파일이 없습니다.';
	}
	
	divFileupload.innerHTML = content;
}
</script>
</head>
<body>

	<h2>파일 업로드</h2>
	<form method="post" enctype="multipart/form-data" 
	        action="/file/fileUploadAction" name="FileUploadForm">
	        
	 <h2>파일 선택</h2>
	 <!-- 같은 name으로 여러개를 전송하기때문에 받을때 list로 처리함 -->
	 bno : <input type="text" name="bno" id="bno" value="202"><br>
	 <input type="file" name="files"><br>
	 <input type="file" name="files"><br>
	 <input type="file" name="files">
	 <br>   <br>
	 <button type="submit">파일 업로드</button>       
	 <br>
	 <button type="button" id="btnFileupload">Fetch 파일 업로드</button>
	 
	 <div id="divFileuploadRes">
	 res : ${param.msg }
	 </div> 
	        
	</form>
	
	<h2>파일 리스트 조회</h2>
	
	<button type="button" id="btnGetList">누르면 리스트가 조회되는 버튼</button>
	<div id="divFileupload"></div>
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/ba30180671.js" crossorigin="anonymous"></script>
	
</body>
</html>