package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.ReplyMapper;
import com.momo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public List<ReplyVO> getList(int bno) {
	
		return replyMapper.getList(bno);
	}

	@Override
	public int insert(ReplyVO vo) {
		
		return replyMapper.insert(vo);
	}

	@Override
	public int delete(int rno) {
		
		return replyMapper.delete(rno);
	}

	@Override
	public int edit(ReplyVO vo) {

		return replyMapper.edit(vo);
	}
	

}
