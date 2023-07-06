package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

@Service
public interface BoardService {
	
	public List<BoardVO> getListXml(Criteria cri, Model model);
	
	public int insert(BoardVO boardvo);
		
	public int insertSelectKey(BoardVO boardvo);
	
	public BoardVO getOne(int bno);
	
	public int delete(int bno);
	
	public int update(BoardVO boardvo);
	
	public int getTotalCnt(Criteria cri);
}
