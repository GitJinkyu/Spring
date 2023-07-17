package com.momo.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.momo.vo.Member;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();

		if(session.getAttribute("member") != null) {
			Member member = (Member)session.getAttribute("member");
			List<String> role = member.getRole_id();
			if(role.contains("admin_role")) {
				return true;
			}
		}
		
		//로그인 페이지로 이동
		String msg =  URLEncoder.encode("로그인후 사용가능한 메뉴 입니다.","utf-8");
		response.sendRedirect("/member/login?msg="+msg);
		return false;
				
	}
}
