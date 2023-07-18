package com.momo.mapper;

import java.util.List;

import com.momo.vo.FileuploadVO;

public interface FileuploadMapper {

	public List<FileuploadVO> getList(int bno); 
	
	public int insert(FileuploadVO vo); 
}
