package com.momo.book;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.momo.mapper.BookMapper;
import com.momo.service.BookService;
import com.momo.vo.BoardVO;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	@Test
	public void getList(Criteria cri,Model model) {
		assertNotNull(bookService);
		
		List<BookVO> list = bookService.getList(cri, model);
		log.info("list====================");
		log.info(list);
		
		
		list.forEach(book->{
			log.info("Book================");
			log.info(book);
		});
		
	}

}


