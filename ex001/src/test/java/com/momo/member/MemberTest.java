package com.momo.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.momo.mapper.MemberMapper;
import com.momo.service.MemberService;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberTest {
	
	@Autowired
	MemberMapper  mapper;
	
	@Autowired
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder encoder; 
	
	@Test
	public void login() {
		assertNotNull(mapper);
		
		Member member = new Member();
		member.setId("guest");
		member.setPw("1234");
		member = mapper.login(member);
		
		log.info(member);
	
	}
	
	@Test
	public void testInsert() {
		Member member =new Member();
		member.setId("admin");
		member.setPw(encoder.encode("1233"));
		member.setName("관리자");
		
		int res = service.signUp(member);
		
		assertEquals(res, 1);
		
	}
	
	
	@Test
	public void idCheck() {
		Member member =new Member();
		
		member.setId("testf31");
		
		int res = service.idCheck(member);
		
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetMemberRole() {
		//관리자인지 멤버의 권한 조회 
		
		List<String> list =  mapper.getMemberRole("admin");
									//list안에 ADMIN_ROLE 문자열이 있는지 확인후 true/false 반환
		System.out.println("관리자권한: " + list.contains("admin_role"));
		
	}
}
