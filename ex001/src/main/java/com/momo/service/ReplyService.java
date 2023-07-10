package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.ReplyVO;

@Service
public interface ReplyService {
	
	public List<ReplyVO> getList(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int edit(ReplyVO vo);
	
}
