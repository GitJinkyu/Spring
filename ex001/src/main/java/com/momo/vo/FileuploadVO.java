package com.momo.vo;

import lombok.Data;

@Data
public class FileuploadVO {
	private String uuid;
	private String uploadpath;
	private String filename;
	private String filetype;
	private int bno;
	
	//다운로드 기능에 쓰는 VO 필드 두개
	//uploadpath + uuid + "_" + filename;
	//데이터베이스 조회할때 컬럼 합쳐서 밑에 이름으로 컬럼명 붙여서 조회하기 
	private String savePath;	//저장된 파일경로
	private String s_savePath;	//저장된 썸네일파일경로
	
}
