<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.addEventListener('load', function() {
	
	btnGetList.addEventListener('click',function(){
		getFileList();
	})
	
	
	
	
	
	
});

function getFileList(){
	///file/list/{bno}
	
	let bno = document.querySelector('#bno').value;
	fetch('/file/list/'+bno)
		.then(response => response.json())
		.then(map => viewFileList(map));
}
function viewFileList(map){
	console.log(map);
	
	let content = '';
	if(map.list.length > 0 ){
		map.list.forEach(function(item,index){
			content += item.filename + "/" + item.s_savePath + '<br>'
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
	 res : ${param.msg } 
	        
	</form>
	
	<h2>파일 리스트 조회</h2>
	
	<button type="button" id="btnGetList">누르면 리스트가 조회되는 버튼</button>
	<div id="divFileupload"></div>
</body>
</html>