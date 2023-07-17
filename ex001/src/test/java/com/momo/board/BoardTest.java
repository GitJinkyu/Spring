package com.momo.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardTest {
	
	@Autowired
	BoardMapper  boardMapper;
	
	@Test
	public void getList() {
		
		assumeNotNull(boardMapper); //매퍼가 제대로 주입되었는지 테스트
		
		List<BoardVO> list = boardMapper.getList();
		
		list.forEach(board->{
			log.info("boardVO================");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
		
	}
	
	@Test
	public void getListXml(Model model,Criteria cri) {
		List<BoardVO> list = boardMapper.getListXml(cri);
		
		list.forEach(board->{
			log.info("boardVO================");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	@Test
	public void insert() {
		BoardVO board =  new BoardVO();
		board.setTitle("제목");
		board.setContent("내용");
		board.setWriter("글쓴이");
		
		
		int res = boardMapper.insert(board);
		
		
		assertEquals(res, 1); //테스트 확인용
	}
	
	@Test
	public void insertSelectKey() {
		BoardVO board =  new BoardVO();
		board.setTitle("insertSelectKey제목");
		board.setContent("내용");
		board.setWriter("글쓴이");
		
		int res = boardMapper.insertSelectKey(board);
		
		log.info("=======================bno =============================");
		log.info("bno : "+board.getBno());
		
		assertEquals(res, 1); //테스트 확인용
	}
	
	@Test
	  public void getOne() {
	
	    // 존재하는 게시물 번호로 테스트
		BoardVO board =  boardMapper.getOne(10);
	 
	    log.info(board);
	 
	  }
	
	@Test
	public void delete() {
		
		int res = boardMapper.delete(10);
		assertEquals(res, 1);
	}
	
	@Test
	public void update() {
		int bno = 11;
		
		BoardVO board =  new BoardVO();
		board.setBno(bno);
		board.setTitle("제목 수정함");
		board.setContent("내용 수정함");
		board.setWriter("글쓴이 수정함");
		
		boardMapper.update(board);

		BoardVO getboard = boardMapper.getOne(bno);
		
		assertEquals("제목 수정함", getboard.getTitle());
	}
	
	@Test
	public void getTotalCnt(Criteria cri) {
		int res = boardMapper.getTotalCnt(cri);
		
		log.info("TotalCnt = "+res);
	}
	
	
	@Test
	public void updateReplyCnt() {
		int res = boardMapper.updateReplyCnt(181, 1);
		
		assertEquals(1, res);
	}
}
