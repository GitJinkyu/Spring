package com.momo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.FileuploadService;


import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class FileUploadController extends CommonRestController{
	public static final String ATTACHES_DIR = "c:\\upload\\";
	
	@Autowired
	FileuploadService service;
	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
											// \\가 두개인건 경로에 문자열이 있을경우 문자열 인식을위해
	
	/**
	 * 파일 업로드 오류가 나오는 경우
	 * -전달된 파일이 없는 경우 
	 * -form에 enctype이 오타가 났을 경우
	 * -설정이 안되었을때
	 * 		commons-fileupload 라이브러리추가 (pom.xml)
	 * 		bean객체 생성 (rootContext.xml)
	 * @param files
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/file/fileUploadAction")
	public  String fileUploadAction(List<MultipartFile> files, int bno ,RedirectAttributes rttr) throws Exception {
		
		
		int insertRes = service.fileupload(files,bno);
		
		String msg = insertRes + "건 저장 되었습니다.";
		rttr.addAttribute("msg",msg);
		
		return "redirect:/file/fileupload";
		
	}
	
	@PostMapping("/file/fileUploadActionFetch")
	public @ResponseBody Map<String,Object> fileUploadActionFetch(List<MultipartFile> files, int bno) throws Exception {
		
		log.info("fileUploadActionFetch");
		int insertRes = service.fileupload(files,bno);
		log.info("업로드 건수 : " + insertRes);

		return responseMap("sucess", insertRes + "건 저장되었습니다.");

	}
	
	
	
	@GetMapping("/file/list/{bno}")
	public @ResponseBody Map<String,Object> fileuploadList(@PathVariable("bno")int bno) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("list",service.getList(bno));
		
		return  map;
	}
	
	
	
	
	
	@GetMapping("/file/delete/{uuid}/{bno}")
	public @ResponseBody Map<String,Object> delete(@PathVariable("uuid")String uuid,@PathVariable("bno")int bno){
			
		int res = service.delete(bno, uuid);
		if(res > 0) {
			
			return responseDeleteMap(res);
		}else {
			
			return responseDeleteMap(res);
		}
	}
	
	/**
	 * 파일 다운로드
	 * 컨텐츠 타입을 다운로드 받을수 있는 형식으로 지정하여 
	 * 브라우저에서 파일을 다운로드 할수있게 처리
	 * @param filename
	 * @return
	 */
	@GetMapping("/file/download")
	public ResponseEntity<byte[]> download(String filename){
	
		log.info("download file : " + filename);
		HttpHeaders header = new HttpHeaders();
		
		File file = new File(ATTACHES_DIR + filename);
		
		System.out.println();
		
		if(file.exists()) {
			//컨텐츠 타입을 지정
			//APLLICATION_OCTET_STREAM : 이전 파일의 컨텐츠 유형
			header.add("contentType", MediaType.APPLICATION_OCTET_STREAM.toString());
			
			//컨텐츠에 대한 추가설명 및 파일 이름 한글처리
			try {
				header.add("Content-Disposition"
								, "attachment; filename=\""
								+ new String(filename.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);

				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				
			} catch (IOException e) {
				e.printStackTrace();
				
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
