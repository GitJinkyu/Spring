package com.momo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.momo.service.ReplyService;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;


/**
 * RestController
 * 		Controller 가 REST 방식을 처리하기 위한 것임을 명시한다.
 * @author user
 *
 */
@RestController
@Log4j
public class ReplyController extends CommonRestController{
	
	@Autowired
	ReplyService service;
	
	@GetMapping("/test")
	public String test() {
		
		
		return "test";
	}
	
	/**
	 * PathVariable
	 * 		URL 경로에 있는 값을 파라미터로 추출하려고 할때 사용
	 * 		경로의 특정 부분을 파라메터로 사용
	 * 		{ }로 묶어줘야함
	 * 
	 * @return
	 */
	@GetMapping("/reply/list/{bno}/{page}")
	public Map<String,Object> getList(@PathVariable("bno") int bno,@PathVariable("page") int page){
		
		
		log.info("bno : "+bno);
		log.info("page : "+page);
		
		Criteria cri =  new Criteria();
		cri.setPageNo(page);
		
		//페이지 처리 (시작번호~끝번호)
		List<ReplyVO> list =service.getList(bno, cri);
		int totalCnt = service.totalCnt(bno);
		
		//페이지 블럭을 생성
		PageDto pageDto = new PageDto(cri, totalCnt) ;
		
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("list", list);
//		map.put("pageDto",pageDto);
//		
//		return map;
		
		return responseListMap(list, pageDto);
	}
	
	@GetMapping("/reply/delete/{rno}")
	public Map<String,Object> delete(@PathVariable("rno")int rno) {
		
		/*
		 * Map<String,Object> map = new HashMap<String, Object>();
		 * 
		 * int res = service.delete(rno);
		 * 
		 * if(res > 0) { map.put("result", "success"); }else { map.put("result",
		 * "fail"); map.put("message", "댓글 삭제중 예외가 발생하였습니다."); }
		 * 
		 * return map;
		 */
				//위의 과정을 함수로 만든거로 처리
		return responseDeleteMap(service.delete(rno));
	}
	
	/**
	 * RequestBody
	 * 		JSON데이터를 원하는 타입으로 바인딩 처리
	 * 
	 * @param vo
	 * @return
	 */
	@PostMapping("/reply/insert/")
	public Map<String,Object> insert(@RequestBody ReplyVO vo) {
		log.info("replyVO :"+vo);
		Map<String,Object> map = new HashMap<String, Object>();
		
		
		try {
			int res = service.insert(vo);
			return map = responseWriteMap(res);
				
		}catch (Exception e) {
			map.put("result","fail");
			map.put("message",e.getMessage());
			
		}

		
		return map;
		
	}
	
	@PostMapping("/reply/update/")
	public Map<String,Object> update(@RequestBody ReplyVO vo) {
		
		log.info("replyVO :"+vo);
		
		/*
		 * int res = service.update(vo);
		 * 
		 * Map<String,Object> map = new HashMap<String, Object>();
		 * 
		 * if(res > 0) { map.put("result", "success"); }else { map.put("result",
		 * "fail"); map.put("message", "댓글 수정중 예외가 발생하였습니다."); }
		 * 
		 * return map;
		 */
		return responseEditMap(service.update(vo));
	}
	
	
	
}
