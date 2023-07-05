package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.momo.vo.BoardVO;

public interface BoardMapper {
	
	@Select("select * from tbl_board")
	public List<BoardVO> getList();

	public List<BoardVO> getListXml();
	
	public int insert(BoardVO boardvo);
		
	public int insertSelectKey(BoardVO boardvo);
	
	public BoardVO getOne(int bno);
	
	public int delete(int bno);
	
	public int update(BoardVO boardvo);
	
	public int getTotalCnt();
}