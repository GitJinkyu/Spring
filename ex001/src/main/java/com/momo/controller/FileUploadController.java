package com.momo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.momo.vo.FileuploadVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
public class FileUploadController extends CommonRestController{
	
	@Autowired
	FileuploadService service;
	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
											// \\가 두개인건 경로에 문자열이 있을경우 문자열 인식을위해
	private static final String ATTACHES_DIR = "c:\\upload\\";
	
	/**
	 * 파일 업로드 오류가 나오는 경우
	 * -전달된 파일이 없는 경우 
	 * -form에 enctype이 오타가 났을 경우
	 * -설정이 안되었을때
	 * 		commons-fileupload 라이브러리추가 (pom.xml)
	 * 		bean객체 생성 (rootContext.xml)
	 * @param files
	 * @return
	 */
	@PostMapping("/file/fileUploadAction")
	public  String fileUploadAction(List<MultipartFile> files, int bno ,RedirectAttributes rttr) {
		
		
		int insertRes = fileupload(files,bno);
		
		String msg = insertRes + "건 저장 되었습니다.";
		rttr.addAttribute("msg",msg);
		
		return "redirect:/file/fileupload";
		
	}
	
	@PostMapping("/file/fileUploadActionFetch")
	public @ResponseBody Map<String,Object> fileUploadActionFetch(List<MultipartFile> files, int bno) {
		
		log.info("fileUploadActionFetch");
		int insertRes = fileupload(files,bno);
		log.info("업로드 건수 : " + insertRes);

		return responseMap("sucess", insertRes + "건 저장되었습니다.");

	}
	
	
	
	@GetMapping("/file/list/{bno}")
	public @ResponseBody Map<String,Object> fileuploadList(@PathVariable("bno")int bno) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("list",service.getList(bno));
		
		return  map;
	}
	
	//중복 방지를 위한 날자별 업로드 폴더 만들기
	//			업로드 날자를 폴더 이름으로 사용
	public String getFolder() {
		//현재 시간 가져오기
		LocalDate currentDate = LocalDate.now() ;
		//날자를 2023\07\18\ 형태로 변환해서 경로처럼 만들어주기
		String uploadPath =currentDate.toString().replace("-", File.separator)+File.separator;
		
		log.info("currentDate = "+currentDate);
		log.info("경로  = "+uploadPath);
		
		//폴더 생성(없을때만)
		File saveDir = new File(ATTACHES_DIR + uploadPath);
		if(!saveDir.exists()) {
			//경로를 여러개 만들때는 mkdirs  s가 붙은거로
			if(saveDir.mkdirs()) {
				log.info("폴더 생성!");
			}else {
				log.info("폴더 생성 실패!");
			}
		}

		return uploadPath;
	}
	
	
	public int fileupload(List<MultipartFile> files, int bno ) {
		int insertRes = 0;
		for(MultipartFile file : files) {
					
					//continue를 걸어주는 이유는
					//jsp에서 input type:file 요소가 여러개일 경우
					//선택된 파일이 없는 경우 다음 파일로 이동
					if(file.isEmpty()) {
						continue;
					}
					log.info("getOriginalFilename: "+file.getOriginalFilename());
					log.info("getName: "+file.getName()); //파일 이름이 아닌 jsp에서 input요소의 name
					log.info("getSize: "+file.getSize());
					
					try {
						//UUID
						/**
						 * 소프트웨어 구축에 쓰이는 식별자 표준 
						 * 식별자:중복되지않는 값
						 * 파일이름이 중복되어 파일이 소실되지 않도록 파일이름에 uuid를 붙여서 저장
						 */
						UUID uuid = UUID.randomUUID();
						String saveFileName = uuid + "_" +file.getOriginalFilename();
						
						//dir
						//c:/upload/2023/07/18/
						// 			년/월/일
						File sFile = new File(ATTACHES_DIR
														+getFolder() //경로 2023\07\18\
														+saveFileName);
						
						//file(원본파일)을 sFile(저장 대상 파일)에 저장
						file.transferTo(sFile);
						
						FileuploadVO vo = new FileuploadVO();
		
						//주어진 파일의 Mime 유형을 확인
						String contentType =
											Files.probeContentType(sFile.toPath());
						
						//Mime타입을 확인하여 이미지인 경우 썸네일을 생성
						if(contentType != null && contentType.startsWith("image")) {
							//파일타입 변경
							vo.setFiletype("I");
							//썸네일  저장 경로
							String thumbnail = ATTACHES_DIR+getFolder()+"s_"+saveFileName;
							
							//썸네일 생성
											  //원본파일, 크기 ,저장될 경로
							Thumbnails.of(sFile).size(100, 100).toFile(thumbnail);
							
						}else {
							vo.setFiletype("F");
						}
						vo.setBno(bno);
						vo.setFilename(file.getOriginalFilename());
						vo.setUploadpath(getFolder());
						vo.setUuid(uuid.toString());
						
						int res = service.insert(vo);
						
						if(res>0) {
							insertRes++;
						}
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	
				}
		return insertRes;
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
				header.add("Content-Dispositon"
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
