package com.jinkyu.ex;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jinkyu.mapper.sampleMapper;

//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
	@Autowired
	sampleMapper samplemapper;
	
	@Test  //테스트 하기위해 테스트 어노베이션 지정해줘야함
	public void test() {
		assertNotNull(samplemapper); //테스트용 메소드
		
		System.out.println("getTime() = "+samplemapper.getTime());
		
		System.out.println("getTime2() = "+samplemapper.getTime2());
		
		System.out.println("getmember = "+samplemapper.getmember());
		
		System.out.println("deletemember = "+samplemapper.deletemember());
		
	
	}
}
