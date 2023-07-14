package com.momo.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		member.setId("testf31");
		member.setPw(encoder.encode("1233"));
		member.setName("name");
		
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
}
