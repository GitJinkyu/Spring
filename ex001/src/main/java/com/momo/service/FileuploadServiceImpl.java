package com.momo.service;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.FileuploadMapper;
import com.momo.vo.FileuploadVO;

@Service
public class FileuploadServiceImpl implements FileuploadService {

	@Autowired
	FileuploadMapper fileuploadMapper;
	
	@Override
	public List<FileuploadVO> getList(int bno) {
		
		return fileuploadMapper.getList(bno);
	}
	
	@Override
	public int insert(FileuploadVO vo) {
		
		return fileuploadMapper.insert(vo);
	}

	@Override
	public int delete(int bno,String uuid) {
		
		return fileuploadMapper.delete(bno, uuid);
	}

	

}
