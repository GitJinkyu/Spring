package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.BookMapper;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<BookVO> getList(Criteria cri, Model model) {
		
		/*
		 * 	1.리스트 조회
		 * 	2. 총건수 조회
		 * 	3. 페이지Dto 생성 (페이지블럭을 생성)
		 * 
		 */
		List<BookVO> list = bookMapper.getList(cri);
		
		int totalCnt = bookMapper.getTotalCnt(cri);
		
		PageDto pageDto = new PageDto(cri,totalCnt);
		
		model.addAttribute("list",list);
		model.addAttribute("totalCnt",totalCnt);
		model.addAttribute("pageDto",pageDto);
		
		log.info("cri: "+cri);
		log.info("list: "+list);
		
		return list;

	}

	@Override
	public int getTotalCnt(Criteria cri) {
		
		return bookMapper.getTotalCnt(cri);
	}

	@Override
	public BookVO getOne(String no) {
		
		return bookMapper.getOne(no);
	}

	@Override
	public int delete(String delNo) {
		
		return bookMapper.delete(delNo);
	}

}
