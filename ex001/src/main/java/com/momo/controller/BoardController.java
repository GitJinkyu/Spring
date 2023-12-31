package com.momo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@GetMapping("/reply/test")
	public String test() {
		
		return "/reply/test";
		
	}


	@GetMapping("message")
	public void message(Model model) {
		
	}
	
	@GetMapping("message2")
	public void message2(Model model) {
		
	}
	
	
	@Autowired
	BoardService boardService;
	
	
	/**
	 * 파라메터의 자동수집
	 * 기본생성자를 이용해서 객체를 생성
	 * ->넘어온 값들을 자동으로 setter 메서드를 이용해서 세팅한다
	 * 
	 * @param model
	 * @param cri
	 */
	@GetMapping("list")
	public void getList(Model model, Criteria cri) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		boardService.getListXml(cri,model);

		stopWatch.stop();
		log.info("수행시간 : " +stopWatch.getTotalTimeMillis()+"(ms)초");
	}
	
	
	@GetMapping("view")
	public void getOne(Model model,BoardVO paramVO) {
	
		log.info("------------------------------------------");
		BoardVO board = boardService.getOne(paramVO.getBno());
		model.addAttribute("board",board);
	}
	
	@GetMapping("write")
	public void write(Model model,BoardVO paramVO) {
		model.addAttribute("board",boardService.getOne(paramVO.getBno()));
	}
	
	/**
	 *  RedirectAttributes
	 *  
	 *  리다이렉트 URL의 화면까지 데이터를 전달 (모델은 안됨)
	 *  
	 *  Model과 같이 매개변수로 받아 사용
	 *  addFlashAttribute : 세션에 저장후 페이지 전환
	 */
	@PostMapping("write")
	public String writeAction(RedirectAttributes rttr ,List<MultipartFile> files,BoardVO board,Model model) {
		
		log.info(board);
		
		int res;
		try {
			//board.getBno로 값을 가져오기 위해 insertSelectKey를 써야함
			//시퀀스를 먼저 조회후 시퀀스 번호를 bno에 저장 하고 난 후에 실행함
			//게시물 등록 및 파일 첨부
			res = boardService.insertSelectKey(board,files);
			
			System.out.println(res);
			String msg = "";
			
			if(res>0) {
				
				msg = board.getBno()+"번 등록되었습니다.";
			
				//rttr.addAttribute는 
				//url?msg=등록되었습니다 (쿼리스트링으로 전환됨. 화면에서 받을때 param.으로 받아야함)
				//rttr.addAttribute("msg",msg);
				
				//세션영역에 잠시 저장 -> param. 안붙이고 msg로 호출 가능
				//잠깐 쓰고 사라지기때문에 새로고침시 유지되지않음
				rttr.addFlashAttribute("msg",msg);
				
				return "redirect:/board/list";
				
			}else {
				msg="등록중 오류가 발생하였습니다.";
				model.addAttribute("msg",msg);
				return "/board/message";
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
			if(e.getMessage().indexOf("첨부파일")> -1) {
				model.addAttribute("msg",e.getMessage());
			}else {
				model.addAttribute("msg","등록중 예외사항이 발생하였습니다.");	
			}
			e.printStackTrace();
			return "/board/message";
		}
				
	}
	
	@GetMapping("edit")
	public String edit(Model model,BoardVO paramVO) {
		model.addAttribute("board",boardService.getOne(paramVO.getBno()));
		
		return "/board/write";
	}
	
	@PostMapping("edit")
	public String editAction(Criteria cri,RedirectAttributes rttr ,List<MultipartFile> files,BoardVO board,Model model) {
		System.out.println("==========================================");
		System.out.println("================포스트 에딧 진입==============");
		System.out.println("==========================================");
		
		/* 값 주고 받는 종류 차이를 알아야함
		 * request.getParam("pageNo"); 
		 * request.setAttr(""); 
		 * ${param.pageNo}
		 * 
		 * request.getAttr(""); 
		 * session.setAttr(""); 
		 * ${pageNo}
		 */
		
		
		log.info(board);

		int res;
		try {
			res = boardService.update(board,files);
			System.out.println(res);
			String msg = "";
			
			if(res>0) {
				
				msg = board.getBno()+"번 수정되었습니다.";
				
				//rttr.addAttribute는 
				//url?msg=등록되었습니다 (쿼리스트링으로 전환됨. 화면에서 받을때 param.으로 받아야함)
				
				//세션영역에 잠시 저장 -> param. 안붙이고 msg로 호출 가능
				//잠깐 쓰고 사라지기때문에 새로고침시 유지되지않음
				rttr.addFlashAttribute("msg",msg);
				//rttr.addFlashAttribute("cri",cri);
				
				//검색키워드 페이지 유지하고 돌아가기 구현
				//유지하기위해선 jsp에서 보낸 검색 및 페이지 정보 파라미터를 매개변수 cri로 받고 
				//cri로 받은 파라미터를 다시 addAttribute에 저장해서 화면으로 보내줄때 사용
				rttr.addAttribute("pageNo",cri.getPageNo());
				rttr.addAttribute("searchField",cri.getSearchField());
				rttr.addAttribute("searchWord",cri.getSearchWord());
				
				return "redirect:/board/view?bno="+board.getBno();
				//return "redirect:/board/view?bno="+board.getBno()+"&pageNo="+cri.getPageNo()+"&searchField="+cri.getSearchField()+"&searchWord="+cri.getSearchWord();
				
				//리턴 그냥 경로를 적으면 컨트롤을 거치지않고 해당 경로내의 .jsp를 바로 호출함
				//return "/board/view";
				
			}else {
				msg="수정중 오류가 발생하였습니다.";
				model.addAttribute("msg",msg);
				return "/board/message";
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			if(e.getMessage().indexOf("첨부파일")> -1) {
				model.addAttribute("msg",e.getMessage());
			}else {
				model.addAttribute("msg","등록중 예외사항이 발생하였습니다.");	
			}
			e.printStackTrace();
			return "/board/message";
		}
	}
	
	@GetMapping("delete")
	public String delete(RedirectAttributes rttr ,BoardVO board,Model model) {
		
		int res = boardService.delete(board.getBno());
		
		if(res > 0) {
			
			rttr.addFlashAttribute("msg","삭제 되었습니다.");
			return "redirect:/board/list";
		}else {
			model.addAttribute("msg","삭제중 예외가 발생하였습니다.");
			return "/board/message";
		}
	}
}
