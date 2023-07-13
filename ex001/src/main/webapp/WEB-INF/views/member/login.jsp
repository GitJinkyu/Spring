<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>로그인</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      html,
      body {
        height: 100%;
      }

      body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 330px;
        padding: 15px;
      }

      .form-signin .form-floating:focus-within {
        z-index: 2;
      }

	  #middle{
	    border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        margin-bottom: -1px;
        
	  }
      #start  {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
      }
	  
      #end  {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
      }

    </style>

    
    
    
<script type="text/javascript" src="/resources/js/fetch.js"></script>    

<script>
window.addEventListener('load',function(){
	
	btlnLogin.addEventListener('click',function(e){
		//기본이벤트 제거
		e.preventDefault();
		// 파라메터 수집
		let obj={
				id	: document.querySelector('#id').value
				,pw	: document.querySelector('#pw').value
		}
		
		console.log(obj);
		//요청
		fetchPost('/member/loginAction',obj,loginCheck)
	})
	
	btlnSigninView.addEventListener('click',function(){
		signupForm.style.display='none';
		signinForm.style.display='';
		
	})
	btlnSignupView.addEventListener('click',function(){
		signupForm.style.display='';
		signinForm.style.display='none';
	})
	
	
});

function loginCheck(map){
	
	if(map.result =='success'){
		//로그인 성공 -> 게시판 리스트 화면으로 이동
		location.href = "/board/list";
			
	}else{
		//로그인 실패 -> 메세지 처리
		msg.innerHTML = map.msg;
	}
}


</script>

  </head>
  <body class="text-center">
	    
	<main class="form-signin w-100 m-auto">
	
	  <form name="signinForm" method="post" action="/member/loginAction">
	    <img class="mb-4" src="/resources/img/bootstrap-logo.svg" alt="" width="72" height="57">
	    <h1 class="h3 mb-3 fw-normal">로그인 해주세요!</h1>
		
	    <div class="form-floating">
	      <input type="text" class="form-control" name="id" id="id" >
	      <label for="id">ID</label>
	    </div>
	    <div class="form-floating">
	      <input type="password" class="form-control" name="pw" id="pw" >
	      <label for="pw">Password</label>
	    </div>
	
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="remember-me"> Remember me
	      </label>
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit" id="btlnLogin">로그인</button>
	    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
	  </form>
	  
	  
	  <form name="signupForm" style='display:none'>
	    <img class="mb-4" src="/resources/img/bootstrap-logo.svg" alt="" width="72" height="57">
	    <h1 class="h3 mb-3 fw-normal">로그인 해주세요!</h1>
		
	    <div class="form-floating">
	      <input  type="text" class="form-control" name="id"  id="start" >
	      <label for="start">ID</label>
	    </div>
	    <div class="form-floating">
	      <input type="password" class="form-control" name="pw" id="middle" >
	      <label for="middle">Password</label>
	    </div>
	    <div class="form-floating">
	      <input  type="password" class="form-control" name="pwCheck"  id="end" >
	      <label for="end">PasswordCheck</label>
	    </div>
	
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="remember-me"> Remember me
	      </label>
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit" id="btlnSignUp">회원가입</button>
	    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
	  </form>
	  
	    <div class="d-grid gap-2 d-md-block">
	    	<button class="btn btn-lg btn-primary btn-sm" type="button" id="btlnSigninView" >로그인</button>
		    <button class="btn btn-lg btn-primary btn-sm" type="button" id="btlnSignupView" >회원가입</button>
		</div>
	  

	</main>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>  
  </body>
</html>
