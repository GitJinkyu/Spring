package com.momo.fileupload;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.log.LogTest;
import com.momo.mapper.FileuploadMapper;
import com.momo.vo.FileuploadVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileuploadTest {
	
	@Autowired
	FileuploadMapper mapper;
	
	@Test
	public void test() {
		log.info("insert()");
		FileuploadVO vo =  new FileuploadVO();
		UUID uuid = UUID.randomUUID() ;
		
		vo.setBno(202);
		vo.setFilename("filename");
		vo.setFiletype("I");
		vo.setUploadpath("uploadpath");
		vo.setUuid(uuid.toString());
		int res = mapper.insert(vo); 
		
		assertEquals(res, 1);
		
		
	}

	@Test
	public void testGetList() {
		log.info("getList()");
	
		mapper.getList(202);
	}
	
	@Test
	public void delete() {

		int bno = 202;
		String uuid = "20cc0294-b0e7-4ef1-adaf-f6020c2514fb";

		
		mapper.delete(bno,uuid);
	}
	
}
