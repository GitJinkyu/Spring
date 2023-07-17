package com.momo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.service.MemberService;
import com.momo.vo.Member;

@Controller
@RequestMapping("/member/*")
public class MemberController extends CommonRestController{
	
	@Autowired
	MemberService service;
	
	/**
	 * 로그인 페이지로 이동
	 * @return
	 */
	@GetMapping("login")
	public void login() {
		
	}
	
	
	@PostMapping("loginAction")
	public @ResponseBody Map<String,Object> loginAction(@RequestBody Member member, Model model,HttpSession session) {
		
		System.out.println("id : " + member.getId());
		System.out.println("pw : " + member.getPw());
		
		member = service.login(member);
		
		if(member != null) {
			session.setAttribute("member", member);
			session.setAttribute("userId", member.getId());
			
			Map<String,Object> map = responseMap(REST_SUCCESS, "로그인 되었습니다.");
			
			if(member.getRole_id() != null
					&& member.getRole_id().contains("admin_role")) {
				//관리자 로그인 -> 관리자 페이지로 이동
				map.put("url","/admin/main");
			}else {
				map.put("url","/board/list");				
			}			
				
			return map;
		}else {
			return responseMap(REST_FAIL, "아이디와 비밀번호를 확인해주세요");
			
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		
	    // 세션 무효화
	    HttpSession session = request.getSession();
	    session.invalidate();
	    
	    return "redirect:/member/login";
	}

	@PostMapping("idCheck")
	public @ResponseBody Map<String, Object> idCheck(@RequestBody Member member){
		
		System.out.println("id : " + member.getId());
		
		int res = service.idCheck(member);
		
		if(res == 0 ) {
			return responseMap(REST_SUCCESS, "아이디 중복체크 성공");
		}else {
			return responseMap(REST_FAIL, "중복된 아이디입니다.");
		}
	}
	
	@PostMapping("signUp")
	public @ResponseBody Map<String, Object> signUp(@RequestBody Member member){
		
		int res = service.signUp(member);
		try {
			if(res > 0 ) {
				return responseMap(REST_SUCCESS, "회원가입을 성공하였습니다.");
			}else {
				return responseMap(REST_FAIL, "회원가입을 실패하였습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return responseMap(REST_FAIL, "회원 등록중 에러가 발생하였습니다.");
		}
	}
	

	
	
}















