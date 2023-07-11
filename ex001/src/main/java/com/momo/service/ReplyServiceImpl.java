package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public List<ReplyVO> getList(@Param(value="bno") int bno,@Param(value="cri")Criteria cri) {
	
		return replyMapper.getList(bno,cri);
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
	public int update(ReplyVO vo) {

		return replyMapper.update(vo);
	}

	@Override
	public int totalCnt(int bno) {
		
		return replyMapper.totalCnt(bno);
	}
	

}
