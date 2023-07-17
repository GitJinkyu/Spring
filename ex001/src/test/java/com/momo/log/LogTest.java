package com.momo.log;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.LogMapper;
import com.momo.vo.LogVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LogTest {
	
	@Autowired
	LogMapper logMapper;
	
	@Test
	public void test() {
		LogVO vo = new LogVO();
		
		vo.setClassName("className");
		vo.setErrmsg("errmsg");
		vo.setMethodName("methodName");
		vo.setParams("params");
		
		int res = logMapper.insert(vo);
		
		assertEquals(res, 1);
	}

}
