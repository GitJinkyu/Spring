package com.momo.member;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {
	
	/**
	 * 스프링 시큐리티 모듈에서제공하는 비밀번호 암호화 및 인증 기능
	 * (Spring Security)에서 제공하는 비밀번호를 암호화하는데 사용 할 수 있는 메서드를 가진 클래스
	 * 
	 * 메이븐 레퍼지토리 사이트에서 검색해서 5.0.7버전을 pom.xml에 Spring Security 라이브러리를 추가해야함
	 */
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void test() {
		String pw = "안녕하세요";
		
		for(int i = 0 ; i < 10; i++) {
			//암호화할때마다 새로운 문자열을 변환 하며 복호화가 불가능
			String encodePw = encoder.encode(pw);
			System.out.println("암호화된 문자열 : "+encodePw);
			
			//첫번째 매개변수는 일치여부를 확인하고자 하는 인코딩 되지않은 패스워드 변수
			//두번째 매개변수는 암호화 인코딩된 패스워드 변수를 입력한다.
			
			boolean matches = encoder.matches(pw, encodePw);
			System.out.println("암호 비교 결과"+matches);
			
		}
	}
}
