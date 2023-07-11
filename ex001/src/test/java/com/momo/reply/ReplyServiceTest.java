package com.momo.reply;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.ReplyMapper;
import com.momo.service.ReplyService;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;


//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest {

	@Autowired
	ReplyService service;
	
	@Test
	public void test() {
		assertNotNull(service);
		
		Criteria cri = new Criteria();
		cri.setAmount(5);
		cri.setPageNo(1);
		
		List<ReplyVO> list = service.getList(148,cri);
		
		log.info(list);
	}
	


}
