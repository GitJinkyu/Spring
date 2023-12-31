package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

@Service
public interface BookService {

	//추상메서드
	public List<BookVO> getList(Criteria cri, Model model);
	
	public int getTotalCnt(Criteria cri);
	
	public BookVO getOne(String no);

	public int delete(String delNo);
}
