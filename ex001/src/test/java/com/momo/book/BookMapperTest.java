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
import com.momo.vo.BoardVO;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

//스프링 테스트 하기위해선 아래 두줄이 있어야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookMapperTest {
	
	@Autowired
	BookMapper bookMapper;
	
	//테스트 메서드에는 매개변수가 있으면 안된다.
	@Test
	public void getList() {
		//assertNotNull(bookMapper);
		Criteria cri = new Criteria();
		cri.setSearchField("title");
		cri.setSearchWord("드래");
		
		
		List<BookVO> list = bookMapper.getList(cri);
		
		list.forEach(book->{
			log.info("Book================");
			log.info(book);
		});
		
	}
	
	@Test
	public void getTotalCnt() {
		int res =  bookMapper.getTotalCnt(new Criteria());
		System.out.println("총 건수 : "+res);
		
	}

}


