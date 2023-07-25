package com.momo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BCryptPasswordEncoder encoder; 

	@Override
	public Member login(Member paramMember) {
		//암호화되어 저장된 DB의 암호를 가져오기 위해 id만으로 사용자 조회한다
		Member member = memberMapper.login(paramMember);
		if(member != null) {
			//사용자로부터 화면에서 입력받은 패스워드, DB에 저장된 암호화된 패스워드를 비교
			boolean res = encoder.matches(paramMember.getPw(), member.getPw());
			
			//비밀번호가 일치할 경우 member객체를 반환
			if(res) {
				//비밀번호까지 일치하는 인증된 유저일 경우 권한조회후 role을 멤버객체에 저장해줌
				member.setRole_id(memberMapper.getMemberRole(member.getId()));
				return member;
			}
		}
		
		
		return null ;
	}

	@Override
	public int signUp(Member member) {
		
		member.setPw(encoder.encode(member.getPw()));
		return memberMapper.signUp(member);
	}

	@Override
	public int idCheck(Member member) {
		return memberMapper.idCheck(member);
	}

	@Override
	public List<String> getMemberRole(String id) {
	
		return memberMapper.getMemberRole(id);
	}

	
	@Autowired
	ApiExamMemberProfile apiExam;
	@Override
	public void naverLogin(HttpServletRequest request, Model model) {
		try {
			//callback 처리 -> access_token을 얻기 위함
			Map<String,String> callbackRes = callback(request);
			String access_token = callbackRes.get("access_token");
			
			//access_token -> 사용자 프로필 조회
			Map<String,Object> responseBody =  apiExam.getMemberProfile(access_token);
			
			Map<String, String> response
					= (Map<String,String>) responseBody.get("response");
			System.out.println("===============네이버 로그인=============");
			System.out.println(response.get("name"));
			System.out.println(response.get("id"));
			System.out.println(response.get("gender"));
			System.out.println("======================================");
			
			//세션저장
			
			
			model.addAttribute("id" , response.get("id"));
			model.addAttribute("name" , response.get("name"));
			model.addAttribute("gender" , response.get("gender"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
	}
	
	public Map<String,String>callback(HttpServletRequest request) throws Exception{
		
	    String clientId = "FsZ14NXN0vgDCtagrIVf";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "_VA3TKQq0c";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    try {
	    String redirectURI = URLEncoder.encode("http://localhost:8080/login/naver_callback", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        System.out.println("토큰 요청"+res.toString());
	        //--------------map으로 반환받기------------------
	        Map<String, String> map = new HashMap<String, String>();
	        
	        //jackson라이브러리 사용
	        ObjectMapper objectMapper = new ObjectMapper();
	        
	        //json문자열을 Map으로 변환
	        map = objectMapper.readValue(res.toString(), Map.class);
    
	        return map;
	      }else {
	    	  throw new Exception("callback  반환코드 "+responseCode);  
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	      throw new Exception("callback 처리중 예외 발생하였습니다.");
	    }
	}

}
