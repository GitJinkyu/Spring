package com.momo.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Spring Interceptor
 * 		HTTP 요청 처리 과정에서 요청을 가로채고 처리 전후에 추가 작업을 수행
 * 		인터셉터는 컨트롤러 에 진입하기전, 컨트롤러 실행 후
 * 		,뷰 렌더링 전 등 다양한 시점에서 동작
 * 		사용하여 요청의 처리흐름을 제어하거나 조작 할 수 있다.
 * 
 * 		인증 및 권한 체크로직을 작성해보자
 * @author user
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	
	/**서블렛 컨텍스트에도 추가해줘야함
	 * preHandle : 컨트롤러 실행전 실행
	 * return
	 * 			true: 요청컨트롤러 실행
	 * 			false: 요청컨트롤러 실행 하지 않음
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
			throws Exception{
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null && !session.getAttribute("userId").equals("")) {
			return true;
		}else {
			String msg =  URLEncoder.encode("로그인후 사용가능한 메뉴 입니다.","utf-8");
			response.sendRedirect("/member/login?msg="+msg);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	

}
