package com.jinkyu.mapper;

import org.apache.ibatis.annotations.Select;

public interface sampleMapper {

	@Select("select sysdate from dual")
	String getTime();

	String getTime2();
	
	String getmember();
	
	int deletemember();
}
