package com.momo.mapper;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;

import com.momo.vo.Criteria;
import com.momo.vo.FileuploadVO;

public interface FileuploadMapper {

	public List<FileuploadVO> getList(int bno); 
	
	public int insert(FileuploadVO vo);
	
	//쿼리문에서 #{}변수를 두개이상 받아야할 경우엔 vo객체를 쓰던가 아니면 @Param 이용해야함
	public int delete(@Param(value="bno") int bno,@Param(value="uuid")String uuid);
}
