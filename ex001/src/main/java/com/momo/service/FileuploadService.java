package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.FileuploadVO;

@Service
public interface FileuploadService {
	
	/**
	 * 하나의 게시물에 저장된 파일의 목록을 조회
	 * 
	 * @param bno
	 * @return
	 */
	public List<FileuploadVO> getList(int bno); 
	
	public int insert(FileuploadVO vo); 

}
