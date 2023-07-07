package com.momo.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	//테스트 메서드에는 매개변수가 있으면 안된다.밑에는 임시로 넣어놓은것
	@Test
	public void getListXml(Criteria cri,Model model) {
		List<BoardVO> list = boardService.getListXml(cri, model);
		
		list.forEach(board->{
			log.info(board);
		});
	}
	
}
