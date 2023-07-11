package com.momo.reply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.book.BookMapperTest;
import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {

	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void test() {
		assertNotNull(mapper);
		Criteria cri = new Criteria();
		cri.setAmount(5);
		cri.setPageNo(1);
		
		List<ReplyVO> list = mapper.getList(148,cri);
		
		log.info(list);
	}
	
	@Test
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(148);
		vo.setReply("댓글추가");
		vo.setReplyer("추가작성자");
		
		int res = mapper.insert(vo);
		
		log.info(res);
		
		assertEquals(res, 1);
	}
	
	@Test
	public void delete() {
		int rno = 17;
		int res = mapper.delete(rno);
		
		assertEquals(res, 1);
	}
	
	@Test
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		
		vo.setRno(24);
		vo.setReply("댓글수정");
		
		int res = mapper.update(vo);
		
		assertEquals(res, 1);
		
	}
	
	@Test
	public void totalCnt() {
		int res = mapper.totalCnt(148);
		
		System.out.println(res);
	}
}
