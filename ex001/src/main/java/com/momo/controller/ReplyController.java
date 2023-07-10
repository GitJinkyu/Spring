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
public class ReplyController {
	
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
	 * @return
	 */
	@GetMapping("/reply/list/{bno}")
	public List<ReplyVO> getList(@PathVariable("bno") int bno){
		
		log.info("bno : "+bno);
		
		return service.getList(bno);
	}
	
	@GetMapping("/reply/delete/{rno}")
	public Map<String,Object> delete(@PathVariable("rno")int rno) {
		
		int res = service.delete(rno);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(res > 0) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
			map.put("message", "댓글 삭제중 예외가 발생하였습니다.");
		}
		
		return map;
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
		
		int res = service.insert(vo);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(res > 0) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
			map.put("message", "댓글 등록중 예외가 발생하였습니다.");
		}
		
		return map;
		
	}
	
}
